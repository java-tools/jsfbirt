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

// 
// Imports
//
import java.io.IOException;
import java.util.Collection;
import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.Renderer;

import com.exadel.jsfbirt.engine.BirtRendererUtils;
import com.exadel.jsfbirt.event.ExportFormatEvent;
import com.exadel.jsfbirt.renderkit.ActuateWrapperRendererBase;

/**
 * JSF renderer for birt wrapper component.
 * @author Andrey Markavtsov
 *
 */
@SuppressWarnings( { "unused", "unchecked" })
public class BirtWrapperRenderer extends Renderer {
	
	/** Flag indicates if component script was already rendered per current request */
	static final String SCRIPT_RENDERED_FLAG = BirtWrapperRenderer.class.getName();

	/**
	 * Default constructor
	 */
	public BirtWrapperRenderer() {
		super();
	}
	
	/* (non-Javadoc)
	 * @see javax.faces.render.Renderer#decode(javax.faces.context.FacesContext, javax.faces.component.UIComponent)
	 */
	@Override
	public void decode(FacesContext context, UIComponent component) {
		String id = component.getClientId(context);
		Map<String, String> requestMap = context.getExternalContext()
				.getRequestParameterMap();
		if (requestMap.containsKey(id + BirtRendererUtils.EXPORT_PARAMETER_SUFFIX)) {
			new ExportFormatEvent(component, requestMap.get(id + BirtRendererUtils.EXPORT_PARAMETER_SUFFIX))
					.queue();
		}

		super.decode(context, component);
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
	protected Class getComponentClass() {
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
		//
		// pass thru attributes
		//
		encodeAttributesFromArray(writer, component, new String[] {
				"onclick", "ondblclick", "onkeydown",
				"onkeypress", "onkeyup", "onmousedown", "onmousemove",
				"onmouseout", "onmouseover", "onmouseup", "style", "title", "styleClass" });

		new BirtRendererUtils().renderBirt(context, component, null);

	}

	/**
	 * Finishes component rendering
	 * @param writer ResponseWriter
	 * @param context FacesContext
	 * @param component UIComponent
	 * @throws IOException
	 */
	public void doEncodeEnd(ResponseWriter writer, FacesContext context,
			javax.faces.component.UIComponent component) throws IOException {
		ActuateWrapperRendererBase.encodeActuateUtilsScript(writer, context, component);
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
				String value = convertToString(v);
				if ("styleClass".equals(attr)) {
					attr = "class";
				} else if ("style".equals(attr)) {
					String _style = "";
					if (component.getAttributes().get("width") != null) {
						_style += ("width: "
								+ (String) component.getAttributes().get(
										"width") + "; ");
					}
					if (component.getAttributes().get("height") != null) {
						_style += ("height: "
								+ (String) component.getAttributes().get(
										"height") + "; ");
					}
					if (_style != null) {
						value = _style + value;
					}
				}
				if (!"".equals(value)) {
					writer.writeAttribute(attr, value, null);
				}
		}
	}

}
