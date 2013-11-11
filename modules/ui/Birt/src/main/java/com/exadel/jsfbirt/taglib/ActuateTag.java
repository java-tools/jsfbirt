/**
 * GENERATED FILE - DO NOT EDIT
 *
 */
package com.exadel.jsfbirt.taglib;

import javax.el.ELException;
import javax.el.ValueExpression;
import javax.faces.FacesException;
import javax.faces.component.UIComponent;
import javax.faces.webapp.UIComponentELTag;

import com.exadel.jsfbirt.component.html.ActuateWrapper;

/**
 * @author auto generated JSF stuff
 *
 */
public class ActuateTag extends UIComponentELTag {

	// Fields

	/*
	 * height Height
	 */
	private ValueExpression _height;

	/**
	 * Height Setter for height
	 * 
	 * @param height
	 *            - new value
	 */
	public void setHeight(ValueExpression __height) {
		this._height = __height;
	}

	/*
	 * iserverLogin Actuate server login
	 */
	private ValueExpression _iserverLogin;

	/**
	 * Actuate server login Setter for iserverLogin
	 * 
	 * @param iserverLogin
	 *            - new value
	 */
	public void setIserverLogin(ValueExpression __iserverLogin) {
		this._iserverLogin = __iserverLogin;
	}

	/*
	 * iserverPassword Actuate server password
	 */
	private ValueExpression _iserverPassword;

	/**
	 * Actuate server password Setter for iserverPassword
	 * 
	 * @param iserverPassword
	 *            - new value
	 */
	public void setIserverPassword(ValueExpression __iserverPassword) {
		this._iserverPassword = __iserverPassword;
	}

	/*
	 * iserverUrl URL to actuate server
	 */
	private ValueExpression _iserverUrl;

	/**
	 * URL to actuate server Setter for iserverUrl
	 * 
	 * @param iserverUrl
	 *            - new value
	 */
	public void setIserverUrl(ValueExpression __iserverUrl) {
		this._iserverUrl = __iserverUrl;
	}

	/*
	 * reportName The attribute provides access to a component state on the
	 * client side
	 */
	private ValueExpression _reportName;

	/**
	 * The attribute provides access to a component state on the client side
	 * Setter for reportName
	 * 
	 * @param reportName
	 *            - new value
	 */
	public void setReportName(ValueExpression __reportName) {
		this._reportName = __reportName;
	}

	/*
	 * runWhenRendered Defines if report will be run immediately after component
	 * has been rendered
	 */
	private ValueExpression _runWhenRendered;

	/**
	 * Defines if report will be run immediately after component has been
	 * rendered Setter for runWhenRendered
	 * 
	 * @param runWhenRendered
	 *            - new value
	 */
	public void setRunWhenRendered(ValueExpression __runWhenRendered) {
		this._runWhenRendered = __runWhenRendered;
	}

	/*
	 * width Width
	 */
	private ValueExpression _width;

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
	private ValueExpression _reportContainerStyle;
	private ValueExpression _reportContainerStyleClass;
	private ValueExpression _scrollControlEnabled;
	private ValueExpression _mouseScrollingEnabled;
	private ValueExpression _panInOutEnabled;
	private ValueExpression _emptyReportMessage;

	/**
	 * Width Setter for width
	 * 
	 * @param width
	 *            - new value
	 */
	public void setWidth(ValueExpression __width) {
		this._width = __width;
	}

	public void release() {
		super.release();
		this._height = null;
		this._iserverLogin = null;
		this._iserverPassword = null;
		this._iserverUrl = null;
		this._reportName = null;
		this._runWhenRendered = null;
		this._width = null;
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
		this._mouseScrollingEnabled = null;
		this._scrollControlEnabled = null;
		this._panInOutEnabled = null;
		this._reportContainerStyle = null;
		this._reportContainerStyleClass = null;
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
		ActuateWrapper comp = (ActuateWrapper) component;

		if (this._height != null) {
			if (this._height.isLiteralText()) {
				try {

					java.lang.String __height = (java.lang.String) getFacesContext()
							.getApplication().getExpressionFactory()
							.coerceToType(this._height.getExpressionString(),
									java.lang.String.class);

					comp.setHeight(__height);
				} catch (ELException e) {
					throw new FacesException(e);
				}
			} else {
				component.setValueExpression("height", this._height);
			}
		}

		if (this._iserverLogin != null) {
			if (this._iserverLogin.isLiteralText()) {
				try {

					java.lang.String __iserverLogin = (java.lang.String) getFacesContext()
							.getApplication().getExpressionFactory()
							.coerceToType(
									this._iserverLogin.getExpressionString(),
									java.lang.String.class);

					comp.setIserverLogin(__iserverLogin);
				} catch (ELException e) {
					throw new FacesException(e);
				}
			} else {
				component
						.setValueExpression("iserverLogin", this._iserverLogin);
			}
		}

		if (this._iserverPassword != null) {
			if (this._iserverPassword.isLiteralText()) {
				try {

					java.lang.String __iserverPassword = (java.lang.String) getFacesContext()
							.getApplication()
							.getExpressionFactory()
							.coerceToType(
									this._iserverPassword.getExpressionString(),
									java.lang.String.class);

					comp.setIserverPassword(__iserverPassword);
				} catch (ELException e) {
					throw new FacesException(e);
				}
			} else {
				component.setValueExpression("iserverPassword",
						this._iserverPassword);
			}
		}

		if (this._iserverUrl != null) {
			if (this._iserverUrl.isLiteralText()) {
				try {

					java.lang.String __iserverUrl = (java.lang.String) getFacesContext()
							.getApplication().getExpressionFactory()
							.coerceToType(
									this._iserverUrl.getExpressionString(),
									java.lang.String.class);

					comp.setIserverUrl(__iserverUrl);
				} catch (ELException e) {
					throw new FacesException(e);
				}
			} else {
				component.setValueExpression("iserverUrl", this._iserverUrl);
			}
		}

		if (this._reportName != null) {
			if (this._reportName.isLiteralText()) {
				try {

					java.lang.String __reportName = (java.lang.String) getFacesContext()
							.getApplication().getExpressionFactory()
							.coerceToType(
									this._reportName.getExpressionString(),
									java.lang.String.class);

					comp.setReportName(__reportName);
				} catch (ELException e) {
					throw new FacesException(e);
				}
			} else {
				component.setValueExpression("reportName", this._reportName);
			}
		}

		if (this._runWhenRendered != null) {
			if (this._runWhenRendered.isLiteralText()) {
				try {

					java.lang.Boolean __runWhenRendered = (java.lang.Boolean) getFacesContext()
							.getApplication()
							.getExpressionFactory()
							.coerceToType(
									this._runWhenRendered.getExpressionString(),
									java.lang.Boolean.class);

					comp.setRunWhenRendered(__runWhenRendered);
				} catch (ELException e) {
					throw new FacesException(e);
				}
			} else {
				component.setValueExpression("runWhenRendered",
						this._runWhenRendered);
			}
		}

		if (this._width != null) {
			if (this._width.isLiteralText()) {
				try {

					java.lang.String __width = (java.lang.String) getFacesContext()
							.getApplication().getExpressionFactory()
							.coerceToType(this._width.getExpressionString(),
									java.lang.String.class);

					comp.setWidth(__width);
				} catch (ELException e) {
					throw new FacesException(e);
				}
			} else {
				component.setValueExpression("width", this._width);
			}
		}
		
		if (this._scrollControlEnabled != null) {
			if (this._scrollControlEnabled.isLiteralText()) {
				try {

					java.lang.Boolean __scrollControlEnabled = (java.lang.Boolean) getFacesContext()
							.getApplication().getExpressionFactory()
							.coerceToType(this._scrollControlEnabled.getExpressionString(),
									java.lang.Boolean.class);

					comp.setScrollControlEnabled(__scrollControlEnabled);
				} catch (ELException e) {
					throw new FacesException(e);
				}
			} else {
				component.setValueExpression("scrollControlEnabled", this._scrollControlEnabled);
			}
		}

		
		if (this._mouseScrollingEnabled != null) {
			if (this._mouseScrollingEnabled.isLiteralText()) {
				try {

					java.lang.Boolean __mouseScrollingEnabled = (java.lang.Boolean) getFacesContext()
							.getApplication().getExpressionFactory()
							.coerceToType(this._mouseScrollingEnabled.getExpressionString(),
									java.lang.Boolean.class);

					comp.setMouseScrollingEnabled(__mouseScrollingEnabled);
				} catch (ELException e) {
					throw new FacesException(e);
				}
			} else {
				component.setValueExpression("mouseScrollingEnabled", this._mouseScrollingEnabled);
			}
		}


		
		if (this._panInOutEnabled != null) {
			if (this._panInOutEnabled.isLiteralText()) {
				try {

					java.lang.Boolean __panInOutEnabled = (java.lang.Boolean) getFacesContext()
							.getApplication().getExpressionFactory()
							.coerceToType(this._panInOutEnabled.getExpressionString(),
									java.lang.Boolean.class);

					comp.setPanInOutEnabled(__panInOutEnabled);
				} catch (ELException e) {
					throw new FacesException(e);
				}
			} else {
				component.setValueExpression("panInOutEnabled", this._panInOutEnabled);
			}
		}



		setProperty(component, "style", _style);
		setProperty(component, "title", _title);
		setProperty(component, "styleClass", _styleClass);
		setProperty(component, "reportContainerStyleClass", _reportContainerStyle);
		setProperty(component, "reportContainerStyle", _reportContainerStyleClass);
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
		setProperty(component, "emptyReportMessage", _emptyReportMessage);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.faces.webapp.UIComponentTag#getComponentType()
	 */
	public String getComponentType() {
		return "com.exadel.jsfbirt.ActuateWrapper";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.faces.webapp.UIComponentTag#getRendererType()
	 */
	public String getRendererType() {
		return "com.exadel.jsfbirt.ActuateRenderer";
	}

	public void setReportContainerStyleClass(ValueExpression containerStyleClass) {
		_reportContainerStyleClass = containerStyleClass;
	}

	public void setScrollControlEnabled(ValueExpression controlEnabled) {
		_scrollControlEnabled = controlEnabled;
	}

	public void setMouseScrollingEnabled(ValueExpression scrollingEnabled) {
		_mouseScrollingEnabled = scrollingEnabled;
	}

	public void setPanInOutEnabled(ValueExpression inOutEnabled) {
		_panInOutEnabled = inOutEnabled;
	}

	public void setReportContainerStyle(ValueExpression reportContainerStyle) {
		_reportContainerStyle = reportContainerStyle;
	}
	
	public void setEmptyReportMessage(ValueExpression emptyReportMessage) {
		_emptyReportMessage = emptyReportMessage;
	}

	public void setStyle(ValueExpression style) {
		_style = style;
	}
	
}
