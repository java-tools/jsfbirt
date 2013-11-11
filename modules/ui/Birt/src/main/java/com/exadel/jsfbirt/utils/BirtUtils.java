package com.exadel.jsfbirt.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Map;

import javax.faces.FacesException;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.eclipse.birt.report.engine.api.IReportDocument;
import org.eclipse.birt.report.engine.api.IReportEngine;
import org.eclipse.birt.report.engine.api.IReportRunnable;
import org.eclipse.birt.report.engine.api.IRunTask;
import org.eclipse.birt.report.engine.util.FileUtil;

import sun.misc.IOUtils;

import com.exadel.jsfbirt.engine.BirtEngine;

/**
 * BIRT utils class. 
 * Used for extracting parameters from report document before component will be rendered.
 * For example to implement pagination we have to know report page count.
 * @author Andrey Markavtsov
 *
 */
public class BirtUtils {
	
	/**
	 * Gets page count from report.
	 * If report document was specified uses this file.
	 * Otherwise generates report document file from design file passed as parameter.
	 * @param context FacesContext
	 * @param reportDesign report design file path
	 * @param reportDocument report document file path 
	 * @param params map of report parameters
	 * @return
	 */
	public static Long getPageCount(FacesContext context,
			String reportDesign, String reportDocument, Map<String, Object> params) {
		ServletContext sc = null;
		String reportDesignPath = null;
		String reportDocumentPath = null;
		Object s = context.getExternalContext().getSession(false);
		
		if (reportDesign == null && reportDocument == null) {
			throw new FacesException("Should be specified either report design or document file");
		}

		try {
			if (s instanceof HttpSession) {
				sc = ((HttpSession) s).getServletContext();
			} else {
				throw new FacesException("No servlet context available");
			}
			IReportEngine birtEngine = BirtEngine.getBirtEngine(null, sc);
			IReportDocument document = null;
			if (reportDocument == null) {
				reportDesignPath = sc.getRealPath(reportDesign);
				reportDocumentPath = reportDesignPath.substring(0, reportDesignPath
						.lastIndexOf("."))
						+ ".rptdocument";

				IReportRunnable design;
				design = birtEngine.openReportDesign(getReportAsStream(reportDesign, sc));
				IRunTask task = birtEngine.createRunTask(design);
				if (params != null) {
					task.setParameterValues(params);
				}
				task.run(reportDocumentPath);

			}else {
				reportDocumentPath = sc.getRealPath(reportDocument);
			}
			if (reportDocumentPath == null) {
				throw new FacesException("Report document does not exist");
			}
			document = birtEngine.openReportDocument(reportDocumentPath);
			
			return document.getPageCount();

		} catch (Exception e) {
			throw new FacesException("Error during getting report page count", e);
		}

	}
	
	public static InputStream getReportAsStream(String path, ServletContext sc) {
		if (path == null) {
			return null;
		}
		InputStream stream = sc.getResourceAsStream(path);
		if (stream == null) {
			return Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
		}
		return stream;
	}
	
	public static String getReportRealPath(String path, ServletContext sc) throws Exception {
		if (path == null) {
			return null;
		}
		String realPath = null;
		URL url = sc.getResource(path);
		if (url == null) {
			url = Thread.currentThread().getContextClassLoader().getResource(path);
			if (url != null) {
				realPath = url.getPath();
			}
		}
		realPath = sc.getRealPath(path);
		
				
		return realPath;
	}


}
