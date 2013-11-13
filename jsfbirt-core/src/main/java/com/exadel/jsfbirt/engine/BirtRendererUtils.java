package com.exadel.jsfbirt.engine;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Locale;

import javax.faces.FacesException;
import javax.faces.component.UIComponent;
import javax.faces.component.UIParameter;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.eclipse.birt.report.engine.api.EngineException;
import org.eclipse.birt.report.engine.api.HTMLRenderOption;
import org.eclipse.birt.report.engine.api.HTMLServerImageHandler;
import org.eclipse.birt.report.engine.api.IEngineTask;
import org.eclipse.birt.report.engine.api.IRenderTask;
import org.eclipse.birt.report.engine.api.IReportDocument;
import org.eclipse.birt.report.engine.api.IReportEngine;
import org.eclipse.birt.report.engine.api.IReportRunnable;
import org.eclipse.birt.report.engine.api.IRunAndRenderTask;
import org.eclipse.birt.report.engine.api.IRunTask;

import com.exadel.jsfbirt.component.html.BirtWrapper;
import com.exadel.jsfbirt.utils.BirtUtils;

/**
 * Birt renderer util class.
 * Responsible for BIRt runtime call, generating of report HTML and writing it in response.
 * @author Andrey Markavtsov
 *
 */
public class BirtRendererUtils {
	
	
	/** Parameter name requested for special report format */
	public static final String EXPORT_PARAMETER_SUFFIX = "_format";
	
	/**
	 * Enumeration of available formats for BIRT report
	 */
	public static enum OutputFormat {
		
		/** HTML format */
		HTML(HTMLRenderOption.OUTPUT_FORMAT_HTML, "text/html"),
		
		/** PDF format */
		PDF(HTMLRenderOption.OUTPUT_FORMAT_PDF, "application/pdf"),
		
		/** Excel format */
		XLS("xls", "application/vnd.ms-excel"),
		
		/** MS word format */
		DOC ("doc", "application/msword");
		
		// Default format is HTML
		static final OutputFormat DEFAULT = HTML;
		
		/** Format */
		private String format;
		
		/** Appropriate content type */
		private String contentType; 
		
		/**
		 * Output format constructor
		 * @param format
		 * @param contentType
		 */
		private OutputFormat(String format, String contentType) {
			this.format = format;
			this.contentType = contentType;
		}
		
		/**
		 * @return format
		 */
		public String getFormat() {
			return format;
		}
		
		/**
		 * @return content type
		 */
		public String getContentType() {
			return contentType;
		}
		
		/**
		 * Gets output format enumeration instance from specified request parameter value
		 * @param formatStr format
		 * @return
		 */
		public static OutputFormat getFormat(String formatStr) {
			if (formatStr == null) {
				return DEFAULT;
			}
			try {
				return valueOf(formatStr.toUpperCase(Locale.US));
			}catch (Exception e) {
				return DEFAULT;
			}
		}
	}


	/**
	 * Calls BIRT engine API to generate report HTML and writes it in response stream
	 * @param context FacesContext
	 * @param component UIComponent
	 * @param formatStr
	 */
	public void renderBirt(FacesContext context, UIComponent component,
			String formatStr) {

		BirtWrapper birtWrapper = (BirtWrapper) component;
		
		ServletContext sc = null;
		Object s = context.getExternalContext().getSession(false);
		
		if (s instanceof HttpSession) {
			sc = ((HttpSession) s).getServletContext();
		} else {
			throw new FacesException("No servlet context available");
		}
		
		IReportRunnable design = null;
		InputStream stream = null;
		try {
			IReportEngine birtReportEngine = BirtEngine.getBirtEngine(birtWrapper, sc);
			// get report name and launch the engine
			String reportName = birtWrapper.getReportDesign();// req.getParameter(component.getReportName());

			stream = BirtUtils.getReportAsStream(reportName, sc);
			if (stream != null) {
				design = birtReportEngine.openReportDesign(stream);
			}
			
			OutputFormat format = OutputFormat.getFormat(formatStr != null ? formatStr : (String)component.getAttributes().get("format"));

			if (birtWrapper.getReportDocument() != null || birtWrapper.getPageNum() != null || birtWrapper.getPageRange() != null) {
				runThenRender(format, context, birtWrapper, sc, birtReportEngine, design);
			}else {
				runAndRender(format, context, birtWrapper, sc, birtReportEngine, design);
			}
			
		} catch (Exception e) {
			throw new FacesException(e);
		} finally {
			if (stream != null) {
				try {
					stream.close();
				}catch (IOException e) {
					throw new FacesException(e);
				}
			}
		}
	}
	
	
	/**
	 * Gets HTML render options of BIRT engine task
	 * @param out  outputStream generated HTML
	 * @param format output format
	 * @param component UIComponent
	 * @param sc ServletContext
	 * @return
	 */
	protected HTMLRenderOption getRenderOptions(OutputStream out, OutputFormat format, BirtWrapper component, ServletContext sc) {
		// set output options
		HTMLRenderOption options = new HTMLRenderOption();

		// set the image handler to a HTMLServerImageHandler if you plan on
		// using the base image url.
		options.setImageHandler(new HTMLServerImageHandler());
		options.setOutputFormat(HTMLRenderOption.OUTPUT_FORMAT_HTML);
		options.setEmbeddable(true);
		options.setEnableInlineStyle(false);

		options.setOutputFormat(format.getFormat());
		options.setOutputStream(out);
		options.setBaseImageURL(sc.getContextPath() + "/images");
		options.setImageDirectory(sc.getRealPath("/images"));
		
		Boolean svgFlag = component.getSvgAllowed();
		options.setSupportedImageFormats( svgFlag != null && svgFlag ? "PNG;GIF;JPG;BMP;SWF;SVG" : "PNG;GIF;JPG;BMP;SWF" ); //$NON-NLS-1$ //$NON-NLS-2$
		
		return options;
	}
	
	/**
	 * Assigns report parameters
	 * @param component
	 * @param task
	 */
	protected void assignParemeters(BirtWrapper component, IEngineTask task) {
		for (UIComponent kid : component.getChildren()) {
			if (kid instanceof UIParameter) {
				UIParameter p = (UIParameter) kid;
				task.setParameterValue(p.getName(), p.getValue());
			}
		}
	}
	
	/**
	 * Method runs and renders report without creating of .rptdocument file.
	 * It used if no reportDocument or custom page number or range specified 
	 * @param format outputFormat
	 * @param context FacesContext
	 * @param component UIComponent
	 * @param sc ServletContext
	 * @param birtReportEngine BIRT engine instance
	 * @param design Report design instance
	 * @throws EngineException 
	 * @throws IOException
	 */
	protected void runAndRender(OutputFormat format, FacesContext context, BirtWrapper component, ServletContext sc, IReportEngine birtReportEngine, IReportRunnable design) throws EngineException, IOException {
		// create task to run and render report
		IRunAndRenderTask task = birtReportEngine
				.createRunAndRenderTask(design);

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		task.setRenderOption(getRenderOptions(out, format, component, sc));
		assignParemeters(component, task);
		
		setLocale(context, task, component);
		task.run();
		task.close();
		
		printInResponse(format, context, out);
		
	}
	
	
	private OutputStream getOutputStream(FacesContext context) throws IOException {
		OutputStream stream = context.getResponseStream();

		if (stream != null) {
			return stream;
		}
		
		HttpServletResponse response = (HttpServletResponse)context.getExternalContext().getResponse();
		return response.getOutputStream();
	}
	
	/**
	 * Sets locale for runnable task
	 * @param context FacesContext
	 * @param task runnable task
	 * @param component UIComponent
	 */
	private void setLocale(FacesContext context, IEngineTask task, BirtWrapper component) {
		Locale locale = component.getLocale();
		if (locale == null) {
			locale = context.getViewRoot().getLocale();
		}
		if (locale != null) {
			task.setLocale(locale);
		}	
	}
	
	/**
	 * Method firstly calls run task to generate .rptdocument file.
	 * Then renders generated document file.
	 * Is used if reportDocument specified of custom page number/range requested
	 * @param format output format
	 * @param context  FacesContext
	 * @param birtWrapper birt wrapper component
	 * @param sc servlet context
	 * @param birtReportEngine BIRT engine instance
	 * @param design birt design instance
	 * @throws EngineException
	 * @throws IOException
	 */
	protected void runThenRender(OutputFormat format, FacesContext context,
			BirtWrapper birtWrapper, ServletContext sc,
			IReportEngine birtReportEngine, IReportRunnable design)
			throws EngineException, IOException {

		IReportDocument reportDocument = getReportDocument(birtWrapper,
				birtReportEngine, design, sc);
		
		if (reportDocument == null) {
			throw new FacesException("Report document does not exist");
		}
		
		String pageRange = birtWrapper.getPageRange();
		Long pageNumber = birtWrapper.getPageNum();

		IRenderTask task = birtReportEngine.createRenderTask(reportDocument);
		if (pageNumber != null) {
			Long pageCount = reportDocument.getPageCount();
			if (pageNumber > pageCount) {
				pageNumber = pageCount;
			}
			if (pageNumber < 1) {
				pageNumber = 1L;
			}
			task.setPageNumber(pageNumber);
		}
		if (pageRange != null) {
			task.setPageRange(pageRange);
		}

		ByteArrayOutputStream out = new ByteArrayOutputStream();

		task
				.setRenderOption(getRenderOptions(out, format, birtWrapper,
						sc));
		assignParemeters(birtWrapper, task);
		setLocale(context, task, birtWrapper);
		
		task.render();

		printInResponse(format, context, out);

	}
	
	/**
	 * Gets report document instance.
	 * Firstly checks if reportDocument attribute definied.
	 * If yes loads it returns.
	 * If no calls BIRT run task to generate report document file
	 * @param birtWrapper birt wrapper instance
	 * @param birtReportEngine BIRT engine instance
	 * @param design birt design instance 
	 * @param sc
	 * @return
	 */
	private IReportDocument getReportDocument(
			BirtWrapper birtWrapper, IReportEngine birtReportEngine,
			IReportRunnable design, ServletContext sc) {
		try {
			String reportDocument = birtWrapper.getReportDocument();
			if (reportDocument != null) {
				return birtReportEngine.openReportDocument(BirtUtils.getReportRealPath(
						reportDocument, sc));
			}

			String reportDesign = (String) birtWrapper.getReportDesign();
			String reportDesignPath = BirtUtils.getReportRealPath(reportDesign, sc);
			reportDocument = reportDesignPath.substring(0, reportDesignPath
					.lastIndexOf("."))
					+ ".rptdocument";

			if (true/*!(new File(reportDocument).exists())*/) {
				IRunTask runTask = birtReportEngine.createRunTask(design);
				assignParemeters(birtWrapper, runTask);
				runTask.run(reportDocument);
			}

			return birtReportEngine.openReportDocument(reportDocument);
		} catch (EngineException e) {
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Prints generated HTML or byte code to output stream
	 * @param format output format
	 * @param context FacesContext
	 * @param out outputStream containing generated report  
	 * @throws IOException
	 */
	private void printInResponse(OutputFormat format, FacesContext context, ByteArrayOutputStream out ) throws IOException {
		
		HttpServletResponse resp = (HttpServletResponse)context.getExternalContext().getResponse();
		OutputStream outputStream = null;
		try {
			if (context.getResponseWriter() != null) {
				context.getResponseWriter().write(out.toString("UTF-8"));
			} else {
				outputStream = resp.getOutputStream();
				if (outputStream == null) {
					throw new NullPointerException(
							"Response output stream is null !!");
				}
				resp.setContentType(format.getContentType());
				outputStream.write(out.toByteArray());
			}
		}finally {
			if (outputStream != null) {
				outputStream.close();
			}
		}
	}
}
