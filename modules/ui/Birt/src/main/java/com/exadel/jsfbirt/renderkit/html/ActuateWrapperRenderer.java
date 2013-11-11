/**
 * License Agreement.
 *
 * Ajax4jsf 1.1 - Natural Ajax for Java Server Faces (JSF)
 *
 * Copyright (C) 2007 Exadel, Inc.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License version 2.1 as published by the Free Software Foundation.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301  USA
 */

package com.exadel.jsfbirt.renderkit.html;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Collection;
import java.util.Map;

import javax.faces.FacesException;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import com.exadel.jsfbirt.component.html.ActuateWrapper;
import com.exadel.jsfbirt.renderkit.ActuateWrapperRendererBase;


/**
 * JSF renderer for actuate wrapper component.
 * @author Andrey Markavtsov
 *
 */
@SuppressWarnings( { "unused", "unchecked" })
public class ActuateWrapperRenderer extends ActuateWrapperRendererBase {

	/**
	 * Default constructor
	 */
	public ActuateWrapperRenderer() {
		super();
	}

	// Some converters 
	private String convertToString(Object obj) {
		return (obj == null ? "" : obj.toString());
	}

	private String convertToString(boolean b) {
		return String.valueOf(b);
	}

	private String convertToString(int b) {
		return b != Integer.MIN_VALUE ? String.valueOf(b) : "";
	}

	private String convertToString(long b) {
		return b != Long.MIN_VALUE ? String.valueOf(b) : "";
	}

	private boolean isEmpty(Object o) {
		if (null == o) {
			return true;
		}
		if (o instanceof String) {
			return (0 == ((String) o).length());
		}
		if (o instanceof Collection) {
			return (0 == ((Collection) o).size());
		}
		if (o instanceof Map) {
			return (0 == ((Map) o).size());
		}
		if (o.getClass().isArray()) {
			return (0 == ((Object[]) o).length);
		}
		return false;
	}

	/**
	 * Get base component class, targetted for this renderer. Used for check
	 * arguments in decode/encode.
	 * 
	 * @return
	 */
	protected Class<?> getComponentClass() {
		return javax.faces.component.UIComponent.class;
	}
	
	/* (non-Javadoc)
	 * @see javax.faces.render.Renderer#encodeBegin(javax.faces.context.FacesContext, javax.faces.component.UIComponent)
	 */
	@Override
	public void encodeBegin(FacesContext context, UIComponent component)
			throws IOException {
		ResponseWriter writer = context.getResponseWriter();
		doEncodeBegin(writer, context, component);
	}
	
	
	/* (non-Javadoc)
	 * @see javax.faces.render.Renderer#encodeEnd(javax.faces.context.FacesContext, javax.faces.component.UIComponent)
	 */
	@Override
	public void encodeEnd(FacesContext context, UIComponent component)
			throws IOException {
		ResponseWriter writer = context.getResponseWriter();
		doEncodeEnd(writer, context, component);
	}

	/**
	 * Performs initial component rendering
	 * @param writer ResponseWriter
	 * @param context FacesContext
	 * @param component UIComponent
	 * @throws IOException
	 */
	public void doEncodeBegin(ResponseWriter writer, FacesContext context,
			javax.faces.component.UIComponent component) throws IOException {
		java.lang.String id = component.getClientId(context);

		writer.startElement("div", component);
		writer.writeAttribute("id", id, null);
		writer
				.writeAttribute("style", "width: "
						+ convertToString(component.getAttributes()
								.get("width"))
						+ "; "
						+ convertToString(component.getAttributes()
								.get("style")), null);
		//
		// pass thru attributes
		//
		encodeAttributesFromArray(writer, component, new String[] { "onclick",
				"ondblclick", "onkeydown", "onkeypress", "onkeyup",
				"onmousedown", "onmousemove", "onmouseout", "onmouseover",
				"onmouseup", "title", "styleClass" });

		if (!isScriptRendered(context)) {
			encodeActuateUtilsScript(writer, context, component);
			putScriptRendered(context);
		}

		encodeHeader(context, component);

		writer.startElement("div", component);
		writer.writeAttribute("id", convertToString(id) + "_report", null);
		writer.writeAttribute("iserverlogin", getIserverLogin(context,
				component), null);
		writer.writeAttribute("iserverpassword", getIserverPassword(context,
				component), null);
		writer.writeAttribute("iserverurl", getIserverUrl(context, component),
				null);
		writer.writeAttribute("params", "["
				+ convertToString(getParams(context, component)) + "]", null);
		writer.writeAttribute("options", "["
				+ convertToString(getOptions(context, component)) + "]", null);
		writer.writeAttribute("reportname", component.getAttributes().get(
				"reportName"), null);
		writer.writeAttribute("style", "position: relative; overflow: hidden; width: "
				+ convertToString(component.getAttributes().get("width"))
				+ "; height: "
				+ convertToString(component.getAttributes().get("height"))
				+ ";" 
				+ component.getAttributes().get("reportContainerStyle"), null);
		writer.writeAttribute("class", component.getAttributes().get("reportContainerStyleClass"), null);
		ActuateWrapper actuateWrapper = (ActuateWrapper)component;
		if (actuateWrapper.getReportName() == null || (actuateWrapper.getReportName() != null && "".equals(actuateWrapper.getReportName()))) {
			encodeEmptyReportFacet(context, component);
		}

	}
	
//	/**
//	 * Encodes reference to actuate JS API script
//	 * @param writer ResponseWriter
//	 * @param context FacesContext
//	 * @param component UIComponent
//	 * @throws IOException
//	 */
//	public void encodeActuateAPIScript(ResponseWriter writer, FacesContext context, javax.faces.component.UIComponent component) throws IOException {
//		
//		URL url = new URL(getIserverUrl(context, component) + "/jsapi");
//		InputStream stream = url.openStream(); 
//		ByteArrayOutputStream b = null;
//		if (stream != null) {
//			byte [] bytes = new byte[4096];
//			b = new ByteArrayOutputStream();
//			int read = -1;
//			while((read = stream.read(bytes)) >= 0) {
//				b.write(bytes, 0, read);
//			}
//			stream.close();
//		}
//		
//		if (b == null) {
//			throw new FacesException("Actuate JS API is not available !");
//		}
//		
//		StringBuffer script = new StringBuffer("if (window.actuate) return;");
//		script.append(b.toString("UTF-8"));
//				
//		writer.startElement("script", component);
//		writer.writeAttribute("type", "text/javascript", null);
//		writer.write(script.toString());
//		writer.endElement("script");
//	}

	/**
	 * Finishes component rendering
	 * @param writer ResponseWriter
	 * @param context FacesContext
	 * @param component UIComponent
	 * @throws IOException
	 */
	public void doEncodeEnd(ResponseWriter writer, FacesContext context,
			javax.faces.component.UIComponent component) throws IOException {
		writer.endElement("div");
		encodeRunScript(context, component);
		writer.endElement("div");
	}

	/**
	 * Encodes list of standard HTML attributes
	 * @param writer ResponseWriter
	 * @param component UIComponent
	 * @param attributes array of attributes
	 * @throws IOException
	 */
	private void encodeAttributesFromArray(ResponseWriter writer,
			UIComponent component, String[] attributes) throws IOException {
		for (String attr : attributes) {
			Object v = component.getAttributes().get(attr);
			if (v != null) {
				if ("styleClass".equals(attr)) {
					attr = "class";
				}
				writer.writeAttribute(attr, convertToString(v), null);
			}
		}
	}

}
