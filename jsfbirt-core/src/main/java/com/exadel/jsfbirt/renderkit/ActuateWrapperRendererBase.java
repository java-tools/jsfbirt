package com.exadel.jsfbirt.renderkit;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.faces.FacesException;
import javax.faces.component.UIComponent;
import javax.faces.component.UIParameter;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.Renderer;

import com.exadel.jsfbirt.component.html.ActuateWrapper;

/**
 * Base JSF renderer for actuate wrapper component
 * @author Andrey Markavtsov
 *
 */
public class ActuateWrapperRendererBase extends Renderer {
	
	/** actuate server URL */
	static String iServerUrl = null;
	
	/** actuate server login */
	static String iServerLogin = null;
	
	/** actuate server password */
	static String iServerPassword = null;

	/** JS to load report from actuate server */
	static final String RUN_REPORT = "JSF4Actuate.RunReport('%1$s_report')";

	/** Flag specified if JS was rendered per request to avoid multi scripts on the page */
	static final String script_rendered_flag = ActuateWrapperRendererBase.class.getName();
	
	/**
	 * Encodes header component facet
	 * @param context FacesContex
	 * @param component UIComponent
	 * @throws IOException
	 */
	public void encodeHeader(FacesContext context, UIComponent component)
			throws IOException {
		UIComponent facet = component.getFacet("header");
		if (facet != null && facet.isRendered()) {
			ResponseWriter writer = context.getResponseWriter();
			writer.startElement("div", component);
			writer.writeAttribute("style", "position: relative; z-index: 201", null);
			facet.encodeAll(context);
			writer.endElement("div");
		}
	}

	/**
	 * Encodes JS script to load report
	 * @param context FacesContext 
	 * @param component UIComponent 
	 * @throws IOException
	 */
	public void encodeRunScript(FacesContext context, UIComponent component)
			throws IOException {
		ActuateWrapper actuateWrapperBase = (ActuateWrapper) component;
		if (actuateWrapperBase.getRunWhenRendered() && actuateWrapperBase.getReportName() != null && !"".equals(actuateWrapperBase.getReportName())) {
			ResponseWriter writer = context.getResponseWriter();
			writer.startElement("script", component);
			writer.writeAttribute("type", "text/javascript", null);
			writer.write(String.format(RUN_REPORT, component
					.getClientId(context)));
			writer.endElement("script");
		}
	}
	
	/**
	 * Returns UI options specified in JSON format
	 * @param context FacesContext
	 * @param component UIComponent
	 * @return
	 */
	public String getOptions(FacesContext context, UIComponent component) {
		ActuateWrapper actuateWrapper = (ActuateWrapper) component;
		StringBuilder buffer = new StringBuilder("{");
		buffer.append("uioptions : ");
		buffer.append(mapToScript(actuateWrapper.getUIOptionsMap()));
		buffer.append(",scrollControl : ");
		buffer.append(actuateWrapper.getScrollControlEnabled());
		buffer.append(",mouseScroll : ");
		buffer.append(actuateWrapper.getMouseScrollingEnabled());
		buffer.append(",panInOut : ");
		buffer.append(actuateWrapper.getPanInOutEnabled());
		buffer.append("}");
		
		return buffer.toString();
	}
	
	/**
	 * Encodes emptyReport facet. Will be rendered if not report design file specified for component
	 * @param context FacesContext
	 * @param component UIComponent
	 * @throws IOException
	 */
	public void encodeEmptyReportFacet(FacesContext context, UIComponent component)throws IOException {
		UIComponent facet = component.getFacet("emptyReport");
		if (facet != null && facet.isRendered()) {
			facet.encodeAll(context);
		}
	}
	
	/**
	 * Returns parameters map for actuate report.
	 * @param context FacesContext
	 * @param component UIComponent
	 * @return parameters map in JSON format
	 */
	public Object getParams (FacesContext context, UIComponent component) {
		Map<String, Object> params = new HashMap<String, Object>();
		for (UIComponent c : component.getChildren()) {
			if (c instanceof UIParameter) {
				UIParameter p = (UIParameter)c;
				params.put(p.getName(), p.getValue());
			}
		}
		return mapToScript(params);
	}

	
	/**
	 * Transforms map to JSON based string
	 * @param params
	 * @return
	 */
	private Object mapToScript(Map<String, ?> params) {
		StringBuilder b = new StringBuilder("{");
		boolean firstKey = true;
		for (String k : params.keySet()) {
			if (!firstKey) {
				b.append(",");
			}else {
				firstKey = false;
			}
			b.append("");
			b.append(k);
			b.append(":");
			Object o = params.get(k);
			if (o instanceof String) {
				b.append("'");
			}
			b.append(params.get(k));
			if (o instanceof String) {
				b.append("'");
			}
		}
		b.append("}");
		return b.toString();
	}

	/**
	 * Initializes actuate server configuration form web.xml file. 
	 * @param context FacesContext
	 */
	private void initIserverParams(FacesContext context) {
		if (iServerUrl == null) {
			synchronized (this) {
				ExternalContext ext = context.getExternalContext();
				iServerUrl = ext.getInitParameter("actuate.serverUrl");
				iServerLogin = ext.getInitParameter("actuate.serverLogin");
				iServerPassword = ext.getInitParameter("actuate.serverPassword");
				
				if (iServerUrl == null) {
					throw new FacesException("No actuate server URL was defined");
				}
			}
		}
	}
	
	/**
	 * Returns actuate server URL.
	 * Firstly extracts it from component attribute.
	 * If attribute is not defined returns value from web.xml.  
	 * @param context FacesContext
	 * @param component UIComponent
	 * @return
	 */
	public String getIserverUrl(FacesContext context, UIComponent component) {
		if (component.getAttributes().get("iserverUrl") != null) {
			return (String)component.getAttributes().get("iserverUrl");
		}
		
		initIserverParams(context);
		
		return iServerUrl;
		
	}
	
	/**
	 * Returns actuate server login.
	 * Firstly extracts it from component attribute.
	 * If attribute is not defined returns value from web.xml.  
	 * @param context FacesContext
	 * @param component UIComponent
	 * @return
	 */
	public String getIserverLogin(FacesContext context, UIComponent component) {
		if (component.getAttributes().get("iserverLogin") != null) {
			return (String)component.getAttributes().get("iserverLogin");
		}
		
		initIserverParams(context);
		
		return iServerLogin;
		
	}
	
	
	/**
	 * Returns actuate server password.
	 * Firstly extracts it from component attribute.
	 * If attribute is not defined returns value from web.xml.  
	 * @param context FacesContext
	 * @param component UIComponent
	 * @return
	 */
	public String getIserverPassword(FacesContext context, UIComponent component) {
		if (component.getAttributes().get("iserverPassword") != null) {
			return (String)component.getAttributes().get("iserverPassword");
		}
		
		initIserverParams(context);
		
		return iServerPassword;
		
	}
	
	/**
	 * Encodes actuate JS script used for component
	 * @param writer ResponseWriter 
	 * @param context FacesContext
	 * @param component UIComponent
	 * @throws IOException
	 */
	public static void encodeActuateUtilsScript(ResponseWriter writer,  FacesContext context, UIComponent component)throws IOException {
		if (isScriptRendered(context)) {
			return;
		}
		
		InputStream stream = ActuateWrapperRendererBase.class.getClassLoader().getResourceAsStream("com/exadel/jsfbirt/renderkit/html/script/actuateUtils.js");
		if (stream != null) {
			ByteArrayOutputStream b = new ByteArrayOutputStream();
			byte [] bytes = new byte[4096];
			int read = 0;
			while ((read = stream.read(bytes)) > 0) {
				b.write(bytes, 0 , read);
			}
			writer.startElement("script", component);
			writer.writeAttribute("type", "text/javascript", null);
			writer.write(b.toString());
			writer.endElement("script");
		}

		putScriptRendered(context);
	}
	
	/**
	 * Returns true if script already was rendered per current request
	 * @param context FacesContext
	 * @return
	 */
	public static boolean isScriptRendered(FacesContext context) {
		return context.getExternalContext().getRequestMap().containsKey(script_rendered_flag);
	}
	
	/**
	 * Mark that script was rendered per current request
	 * @param context
	 */
	public static void putScriptRendered(FacesContext context) {
		//context.getViewRoot().getAttributes().put(script_rendered_flag, true);
		context.getExternalContext().getRequestMap().put(script_rendered_flag, true);
	}
	
}
