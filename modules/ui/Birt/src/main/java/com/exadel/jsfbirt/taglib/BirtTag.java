/**
 * GENERATED FILE - DO NOT EDIT
 *
 */
package com.exadel.jsfbirt.taglib;

import java.util.Locale;

import javax.el.ELException;
import javax.el.ValueExpression;
import javax.faces.FacesException;
import javax.faces.component.UIComponent;
import javax.faces.webapp.UIComponentELTag;

import com.exadel.jsfbirt.component.html.BirtWrapper;

/**
 * @author auto generated JSF stuff
 *
 */
public class BirtTag extends UIComponentELTag {

	// Fields

	/*
	 * configPath The attribute provides access to a component state on the
	 * client side
	 */
	private ValueExpression _configPath;

	/**
	 * The attribute provides access to a component state on the client side
	 * Setter for configPath
	 * 
	 * @param configPath
	 *            - new value
	 */
	public void setConfigPath(ValueExpression __configPath) {
		this._configPath = __configPath;
	}

	/*
	 * reportName The attribute provides access to a component state on the
	 * client side
	 */
	private ValueExpression _reportDesign;

	/**
	 * The attribute provides access to a component state on the client side
	 * Setter for reportName
	 * 
	 * @param reportName
	 *            - new value
	 */
	public void setReportDesign(ValueExpression _reportDesign) {
		this._reportDesign = _reportDesign;
	}

	/*
	 * width Width
	 */
	//private ValueExpression _width;

	// HTML universal attributes
	private ValueExpression _style;
	private ValueExpression _styleClass;
	private ValueExpression _title;

	// HTML event handler attributes
	private ValueExpression _onclick;
	private ValueExpression _ondblclick;
	private ValueExpression _onkeydown;
	private ValueExpression _onkeypress;
	private ValueExpression _onkeyup;
	private ValueExpression _onmousedown;
	private ValueExpression _onmousemove;
	private ValueExpression _onmouseout;
	private ValueExpression _onmouseover;
	private ValueExpression _onmouseup;
	private ValueExpression _reportDocument;
	private ValueExpression _svgAllowed;
	private ValueExpression _pageRange;
	private ValueExpression _pageNum;
	private ValueExpression _locale;

	public void release() {
		super.release();
		this._configPath = null;
		this._reportDesign = null;
		this._style = null;
		this._styleClass = null;
		this._title = null;
		this._onclick = null;
		this._ondblclick = null;
		this._onkeydown = null;
		this._onkeypress = null;
		this._onkeyup = null;
		this._onmousedown = null;
		this._onmousemove = null;
		this._onmouseout = null;
		this._onmouseover = null;
		this._onmouseup = null;
		this._locale = null;
		this._pageNum = null;
		this._pageRange = null;
		this._reportDocument = null;
		this._svgAllowed = null;
	}

	protected void setProperty(UIComponent component, String propName,
			ValueExpression valueExpression) {
		if (valueExpression != null) {
			if (valueExpression.isLiteralText()) {
				component.getAttributes().put(propName,
						valueExpression.getValue(getELContext()));
			} else {
				component.setValueExpression(propName, valueExpression);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.ajax4jsf.components.taglib.html.HtmlCommandButtonTagBase#setProperties
	 * (javax.faces.component.UIComponent)
	 */
	protected void setProperties(UIComponent component) {
		super.setProperties(component);
		BirtWrapper comp = (BirtWrapper) component;

		if (this._configPath != null) {
			if (this._configPath.isLiteralText()) {
				try {

					java.lang.String __configPath = (java.lang.String) getFacesContext()
							.getApplication().getExpressionFactory()
							.coerceToType(
									this._configPath.getExpressionString(),
									java.lang.String.class);

					comp.setConfigPath(__configPath);
				} catch (ELException e) {
					throw new FacesException(e);
				}
			} else {
				component.setValueExpression("configPath", this._configPath);
			}
		}

		if (this._reportDesign != null) {
			if (this._reportDesign.isLiteralText()) {
				try {

					java.lang.String __reportDesign = (java.lang.String) getFacesContext()
							.getApplication().getExpressionFactory()
							.coerceToType(
									this._reportDesign.getExpressionString(),
									java.lang.String.class);

					comp.setReportDesign(__reportDesign);
				} catch (ELException e) {
					throw new FacesException(e);
				}
			} else {
				component.setValueExpression("reportDesign", this._reportDesign);
			}
		}
		
		if (this._reportDocument != null) {
			if (this._reportDocument.isLiteralText()) {
				try {

					java.lang.String __reportDocument = (java.lang.String) getFacesContext()
							.getApplication().getExpressionFactory()
							.coerceToType(
									this._reportDocument.getExpressionString(),
									java.lang.String.class);

					comp.setReportDocument(__reportDocument);
				} catch (ELException e) {
					throw new FacesException(e);
				}
			} else {
				component.setValueExpression("reportDocument", this._reportDocument);
			}
		}
		
		if (this._svgAllowed != null) {
			if (this._svgAllowed.isLiteralText()) {
				try {

					java.lang.Boolean __svgAllowed = (java.lang.Boolean) getFacesContext()
							.getApplication().getExpressionFactory()
							.coerceToType(
									this._svgAllowed.getExpressionString(),
									java.lang.Boolean.class);

					comp.setSvgAllowed(__svgAllowed);
				} catch (ELException e) {
					throw new FacesException(e);
				}
			} else {
				component.setValueExpression("svgAllowed", this._svgAllowed);
			}
		}
		
		if (this._pageRange != null) {
			if (this._pageRange.isLiteralText()) {
				try {

					java.lang.String __pageRange = (java.lang.String) getFacesContext()
							.getApplication().getExpressionFactory()
							.coerceToType(
									this._pageRange.getExpressionString(),
									java.lang.String.class);

					comp.setPageRange(__pageRange);
				} catch (ELException e) {
					throw new FacesException(e);
				}
			} else {
				component.setValueExpression("pageRange", this._pageRange);
			}
		}
		
		if (this._pageNum != null) {
			if (this._pageNum.isLiteralText()) {
				try {

					java.lang.Long __pageNum = (java.lang.Long) getFacesContext()
							.getApplication().getExpressionFactory()
							.coerceToType(
									this._pageNum.getExpressionString(),
									java.lang.Long.class);

					comp.setPageNum(__pageNum);
				} catch (ELException e) {
					throw new FacesException(e);
				}
			} else {
				component.setValueExpression("pageNum", this._pageNum);
			}
		}
		
		if (this._locale != null) {
			if (this._locale.isLiteralText()) {
				try {

					Locale __locale = (Locale) getFacesContext()
							.getApplication().getExpressionFactory()
							.coerceToType(
									this._locale.getExpressionString(),
									Locale.class);

					comp.setLocale(__locale);
				} catch (ELException e) {
					throw new FacesException(e);
				}
			} else {
				component.setValueExpression("locale", this._locale);
			}
		}
		
		
		setProperty(component, "style", _style);
		setProperty(component, "title", _title);
		setProperty(component, "styleClass", _styleClass);
		setProperty(component, "onclick", _onclick);
		setProperty(component, "ondblclick", _ondblclick);
		setProperty(component, "onmousedown", _onmousedown);
		setProperty(component, "onmouseup", _onmouseup);
		setProperty(component, "onmouseover", _onmouseover);
		setProperty(component, "onmousemove", _onmousemove);
		setProperty(component, "onmouseout", _onmouseout);
		setProperty(component, "onkeypress", _onkeypress);
		setProperty(component, "onkeydown", _onkeydown);
		setProperty(component, "onkeyup", _onkeyup);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.faces.webapp.UIComponentTag#getComponentType()
	 */
	public String getComponentType() {
		return "com.exadel.jsfbirt.BirtWrapper";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.faces.webapp.UIComponentTag#getRendererType()
	 */
	public String getRendererType() {
		return "com.exadel.jsfbirt.BirtRenderer";
	}

	public void setReportDocument(ValueExpression document) {
		_reportDocument = document;
	}

	public void setSvgAllowed(ValueExpression allowed) {
		_svgAllowed = allowed;
	}

	public void setPageRange(ValueExpression range) {
		_pageRange = range;
	}

	public void setPageNum(ValueExpression num) {
		_pageNum = num;
	}

	public void setLocale(ValueExpression _locale) {
		this._locale = _locale;
	}

}
