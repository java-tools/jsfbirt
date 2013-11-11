package com.exadel.jsfbirt.component.html;

import java.util.HashMap;
import java.util.Map;

import javax.el.ELException;
import javax.el.ValueExpression;
import javax.faces.FacesException;
import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;

/**
 * Component class for actuate wrapper.
 * Component is responsible for rendering and running reports from actuate server specified.
 * @author Andrey Markavtsov
 *
 */
public class ActuateWrapper extends UIComponentBase {

	/** Component family */
	public static final String COMPONENT_FAMILY = "com.exadel.jsfbirt.ActuateWrapper";

	/** Component type */
	public static final String COMPONENT_TYPE = "com.exadel.jsfbirt.ActuateWrapper";

	/**
	 * Height attribute
	 * Defines height of report container on the page
	 */
	private String _height = null;

	/**
	 * iserverLogin attribute
	 * Defines login for actuate server
	 */
	private String _iserverLogin = null;

	/**
	 * iserverPassword attribute
	 * Defines password for actuate server
	 */
	private String _iserverPassword = null;

	/**
	 * iserverUrl attribute
	 * Defines actuate server URL
	 */
	private String _iserverUrl = null;

	/**
	 * onclick attribute
	 * Defines client side script code to be executed when the element is clicked
	 */
	private String _onclick = null;

	/**
	 * ondblclick attribute
	 * Defines client side script method to be executed when the element is
	 * double-clicked
	 */
	private String _ondblclick = null;

	/**
	 * onkeydown attribute
	 * Defines client side script method to be executed when a key is pressed down
	 * over the element
	 */
	private String _onkeydown = null;

	/**
	 * onkeypress attribute
	 * Defines client side script method to be executed when a key is pressed over the
	 * element and released
	 */
	private String _onkeypress = null;

	/**
	 * onkeyup attribute
	 * Defines client side script method to be executed when a key is released
	 */
	private String _onkeyup = null;

	/**
	 * onmousedown attribute
	 * Defines client side script method to be executed when a mouse button is pressed
	 * down over the element
	 */
	private String _onmousedown = null;

	/**
	 * onmousemove attribute
	 * Defines client side script method to be executed when a pointer is moved within
	 * the element
	 */
	private String _onmousemove = null;

	/**
	 * onmouseout attribute
	 * Defines client side script method to be executed when a pointer is moved away
	 * from the element
	 */
	private String _onmouseout = null;

	/**
	 * onmouseover attribute
	 * Defines client side script method to be executed when a pointer is moved onto
	 * the element
	 */
	private String _onmouseover = null;

	/**
	 * onmouseup attribute
	 * Defines client side script method to be executed when a mouse button is
	 * released
	 */
	private String _onmouseup = null;

	/**
	 * reportName attribute
	 * Defines report design file to be loaded and run on client side from actuate server
	 */
	private String _reportName = null;
	

	/**
	 * runWhenRendered attribute
	 * Defines if report will be run immediately after component has been
	 * rendered
	 */
	private Boolean _runWhenRendered = null;

	/**
	 * Width attribute
	 * Defines width of report container on the page
	 */
	private String _width = null;
	
	/**
	 * Enable scroll control attribute
	 * Defines if report should display scroll control inside
	 */
	private Boolean _scrollControlEnabled = null;
	
	
	/**
	 * Width attribute
	 * Defines if scrolling by mouse is enabled
	 */
	private Boolean _mouseScrollingEnabled = null;
	
	/**
	 * Width attribute
	 * Defines if pan in out is enabled
	 */
	private Boolean _panInOutEnabled = null;
	
	
	/**
	 * UI options attribute
	 * Defines map of UI options to be set for actuate viewer
	 */
	private Map<String, Boolean> _uiOptions;


	/**
	 * UI options map
	 * Holds map of UI options available from actuate JS API.
	 * See http://www.birt-exchange.org/documentation/JavaComponents10/JSAPI-JSdoc/symbols/actuate.viewer.UIOptions.html for details
	 * Map is filled by 'actuateUIOption' tag placed inside of the component
	 */
	transient private Map<String, Boolean> __uiOptions = null;

	/**
	 * Constructor for actuate component class 
	 */
	public ActuateWrapper() {
		setRendererType("com.exadel.jsfbirt.ActuateRenderer");
	}

	/**
	 * Getter for height attribute
	 * @return _height
	 */
	public String getHeight() {
		if (this._height != null) {
			return this._height;
		}
		ValueExpression ve = getValueExpression("height");
		if (ve != null) {
			String value = null;

			try {
				value = (String) ve.getValue(getFacesContext().getELContext());
			} catch (ELException e) {
				throw new FacesException(e);
			}

			return value;
		}
		// Default value
		return "100%";

	}

	/**
	 * Setter for height attribute
	 * @param _height
	 */
	public void setHeight(String _height) {
		this._height = _height;
	}

	/**
	 * Getter for iserverLogin attribute
	 * @return _iserverLogin
	 */
	public String getIserverLogin() {
		if (this._iserverLogin != null) {
			return this._iserverLogin;
		}
		ValueExpression ve = getValueExpression("iserverLogin");
		if (ve != null) {
			String value = null;

			try {
				value = (String) ve.getValue(getFacesContext().getELContext());
			} catch (ELException e) {
				throw new FacesException(e);
			}

			return value;
		}

		return null;

	}

	/**
	 * Setter for iserverLogin attribute
	 * @param _iserverLogin
	 */
	public void setIserverLogin(String _iserverLogin) {
		this._iserverLogin = _iserverLogin;
	}

	/**
	 * Getter for iserverPassword attribute
	 * @return _iserverPassword
	 */
	public String getIserverPassword() {
		if (this._iserverPassword != null) {
			return this._iserverPassword;
		}
		ValueExpression ve = getValueExpression("iserverPassword");
		if (ve != null) {
			String value = null;

			try {
				value = (String) ve.getValue(getFacesContext().getELContext());
			} catch (ELException e) {
				throw new FacesException(e);
			}

			return value;
		}

		return null;

	}

	/**
	 * Setter for iserverPassword attribute
	 * @param _iserverPassword
	 */
	public void setIserverPassword(String _iserverPassword) {
		this._iserverPassword = _iserverPassword;
	}

	/**
	 * Getter for iserverUrl attribute
	 * @return _iserverUrl
	 */
	public String getIserverUrl() {
		if (this._iserverUrl != null) {
			return this._iserverUrl;
		}
		ValueExpression ve = getValueExpression("iserverUrl");
		if (ve != null) {
			String value = null;

			try {
				value = (String) ve.getValue(getFacesContext().getELContext());
			} catch (ELException e) {
				throw new FacesException(e);
			}

			return value;
		}

		return null;

	}

	/**
	 * Setter for iserverUrl attribute
	 * @param _iserverUrl
	 */
	public void setIserverUrl(String _iserverUrl) {
		this._iserverUrl = _iserverUrl;
	}

	/**
	 * Getter for onclick attribute
	 * @return _onclick
	 */
	public String getOnclick() {
		if (this._onclick != null) {
			return this._onclick;
		}
		ValueExpression ve = getValueExpression("onclick");
		if (ve != null) {
			String value = null;

			try {
				value = (String) ve.getValue(getFacesContext().getELContext());
			} catch (ELException e) {
				throw new FacesException(e);
			}

			return value;
		}

		return null;

	}

	/**
	 * Setter for onclick attribute 
	 * @param _onclick
	 */
	public void setOnclick(String _onclick) {
		this._onclick = _onclick;
	}

	/**
	 * Getter for ondblclick attribute
	 * @return _ondblclick
	 */
	public String getOndblclick() {
		if (this._ondblclick != null) {
			return this._ondblclick;
		}
		ValueExpression ve = getValueExpression("ondblclick");
		if (ve != null) {
			String value = null;

			try {
				value = (String) ve.getValue(getFacesContext().getELContext());
			} catch (ELException e) {
				throw new FacesException(e);
			}

			return value;
		}

		return null;

	}

	/**
	 * Setter for ondblclick attribute
	 * @param _ondblclick
	 */
	public void setOndblclick(String _ondblclick) {
		this._ondblclick = _ondblclick;
	}

	/**
	 * Getter for onkeydown attribute
	 * @return _onkeydown
	 */
	public String getOnkeydown() {
		if (this._onkeydown != null) {
			return this._onkeydown;
		}
		ValueExpression ve = getValueExpression("onkeydown");
		if (ve != null) {
			String value = null;

			try {
				value = (String) ve.getValue(getFacesContext().getELContext());
			} catch (ELException e) {
				throw new FacesException(e);
			}

			return value;
		}

		return null;

	}

	/**
	 * Setter for onkeydown attribute
	 * @param _onkeydown
	 */
	public void setOnkeydown(String _onkeydown) {
		this._onkeydown = _onkeydown;
	}

	/**
	 * Getter for onkeypress attribute
	 * @return _onkeypress
	 */
	public String getOnkeypress() {
		if (this._onkeypress != null) {
			return this._onkeypress;
		}
		ValueExpression ve = getValueExpression("onkeypress");
		if (ve != null) {
			String value = null;

			try {
				value = (String) ve.getValue(getFacesContext().getELContext());
			} catch (ELException e) {
				throw new FacesException(e);
			}

			return value;
		}

		return null;

	}

	/**
	 * Setter for onkeypress attribute
	 * @param _onkeypress
	 */
	public void setOnkeypress(String _onkeypress) {
		this._onkeypress = _onkeypress;
	}

	/**
	 * Getter for onkeyup attribute
	 * @return _onkeyup
	 */
	public String getOnkeyup() {
		if (this._onkeyup != null) {
			return this._onkeyup;
		}
		ValueExpression ve = getValueExpression("onkeyup");
		if (ve != null) {
			String value = null;

			try {
				value = (String) ve.getValue(getFacesContext().getELContext());
			} catch (ELException e) {
				throw new FacesException(e);
			}

			return value;
		}

		return null;

	}

	/**
	 * Setter for onkeyup attribute
	 * @param _onkeyup
	 */
	public void setOnkeyup(String _onkeyup) {
		this._onkeyup = _onkeyup;
	}

	/**
	 * Getter for onmousedown attribute
	 * @return _onmousedown
	 */
	public String getOnmousedown() {
		if (this._onmousedown != null) {
			return this._onmousedown;
		}
		ValueExpression ve = getValueExpression("onmousedown");
		if (ve != null) {
			String value = null;

			try {
				value = (String) ve.getValue(getFacesContext().getELContext());
			} catch (ELException e) {
				throw new FacesException(e);
			}

			return value;
		}

		return null;

	}

	/**
	 * Setter for onmousedown attribute
	 * @param _onmousedown
	 */
	public void setOnmousedown(String _onmousedown) {
		this._onmousedown = _onmousedown;
	}

	/**
	 * Getter for onmousemove attribute
	 * @return _onmousemove
	 */
	public String getOnmousemove() {
		if (this._onmousemove != null) {
			return this._onmousemove;
		}
		ValueExpression ve = getValueExpression("onmousemove");
		if (ve != null) {
			String value = null;

			try {
				value = (String) ve.getValue(getFacesContext().getELContext());
			} catch (ELException e) {
				throw new FacesException(e);
			}

			return value;
		}

		return null;

	}

	/**
	 * Setter for onmousemove attribute
	 * @param _onmousemove
	 */
	public void setOnmousemove(String _onmousemove) {
		this._onmousemove = _onmousemove;
	}

	/**
	 * Getter for onmouseout attribute
	 * @return _onmouseout
	 */
	public String getOnmouseout() {
		if (this._onmouseout != null) {
			return this._onmouseout;
		}
		ValueExpression ve = getValueExpression("onmouseout");
		if (ve != null) {
			String value = null;

			try {
				value = (String) ve.getValue(getFacesContext().getELContext());
			} catch (ELException e) {
				throw new FacesException(e);
			}

			return value;
		}

		return null;

	}

	/**
	 * Setter for onmouseout attribute
	 * @param _onmouseout
	 */
	public void setOnmouseout(String _onmouseout) {
		this._onmouseout = _onmouseout;
	}

	/**
	 * Getter for onmouseover attribute
	 * @return _onmouseover
	 */
	public String getOnmouseover() {
		if (this._onmouseover != null) {
			return this._onmouseover;
		}
		ValueExpression ve = getValueExpression("onmouseover");
		if (ve != null) {
			String value = null;

			try {
				value = (String) ve.getValue(getFacesContext().getELContext());
			} catch (ELException e) {
				throw new FacesException(e);
			}

			return value;
		}

		return null;

	}

	/**
	 * Setter for onmouseover attribute
	 * @param _onmouseover
	 */
	public void setOnmouseover(String _onmouseover) {
		this._onmouseover = _onmouseover;
	}

	/**
	 * Getter for onmouseup attribute
	 * @return _onmouseup
	 */
	public String getOnmouseup() {
		if (this._onmouseup != null) {
			return this._onmouseup;
		}
		ValueExpression ve = getValueExpression("onmouseup");
		if (ve != null) {
			String value = null;

			try {
				value = (String) ve.getValue(getFacesContext().getELContext());
			} catch (ELException e) {
				throw new FacesException(e);
			}

			return value;
		}

		return null;

	}

	/**
	 * Setter for onmouseup attribute
	 * @param _onmouseup
	 */
	public void setOnmouseup(String _onmouseup) {
		this._onmouseup = _onmouseup;
	}

	/**
	 * Getter for reportName attribute
	 * @return _reportName
	 */
	public String getReportName() {
		if (this._reportName != null) {
			return this._reportName;
		}
		ValueExpression ve = getValueExpression("reportName");
		if (ve != null) {
			String value = null;

			try {
				value = (String) ve.getValue(getFacesContext().getELContext());
			} catch (ELException e) {
				throw new FacesException(e);
			}

			return value;
		}

		return null;

	}

	/**
	 * Setter for reportName attribute
	 * @param _reportName
	 */
	public void setReportName(String _reportName) {
		this._reportName = _reportName;
	}

	/**
	 * Getter for runWhenRendered attribute
	 * @return _runWhenRendered
	 */
	public Boolean getRunWhenRendered() {
		if (this._runWhenRendered != null) {
			return this._runWhenRendered;
		}
		ValueExpression ve = getValueExpression("runWhenRendered");
		if (ve != null) {
			Boolean value = null;

			try {
				value = (Boolean) ve.getValue(getFacesContext().getELContext());
			} catch (ELException e) {
				throw new FacesException(e);
			}

			return value;
		}

		return true;

	}

	/**
	 * Setter for runWhenRendered attribute
	 * @param _runWhenRendered
	 */
	public void setRunWhenRendered(Boolean _runWhenRendered) {
		this._runWhenRendered = _runWhenRendered;
	}

	/**
	 * Getter for width attribute
	 * @return _width
	 */
	public String getWidth() {
		if (this._width != null) {
			return this._width;
		}
		ValueExpression ve = getValueExpression("width");
		if (ve != null) {
			String value = null;

			try {
				value = (String) ve.getValue(getFacesContext().getELContext());
			} catch (ELException e) {
				throw new FacesException(e);
			}

			return value;
		}
		// Default value
		return "100%";

	}

	/**
	 * Setter for width attribute
	 * @param _width
	 */
	public void setWidth(String _width) {
		this._width = _width;
	}
	
	
	/**
	 * Getter for uiOptions attribute
	 * @return _uiOptions
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Boolean> getUIOptions() {
		if (this._uiOptions != null) {
			return this._uiOptions;
		}
		ValueExpression ve = getValueExpression("uiOptions");
		if (ve != null) {
			Map<String, Boolean> value = null;

			try {
				Object o = ve.getValue(getFacesContext().getELContext());
				if (o != null && o instanceof Map) {
					value = (Map<String, Boolean>) ve.getValue(getFacesContext().getELContext());
				}
			} catch (ELException e) {
				throw new FacesException(e);
			}

			return value;
		}
		return null;

	}

	/**
	 * Setter for uiOptions attribute
	 * @param _uiOptions
	 */
	public void setUIOptions(Map<String, Boolean> _uiOptions) {
		this._uiOptions = _uiOptions;
	}
	
	/**
	 * Getter for scrollControlEnabled attribute
	 * @return _scrollControlEnabled
	 */
	public Boolean getScrollControlEnabled() {
		if (this._scrollControlEnabled != null) {
			return this._scrollControlEnabled;
		}
		ValueExpression ve = getValueExpression("scrollControlEnabled");
		if (ve != null) {
			Boolean value = null;

			try {
				value = (Boolean) ve.getValue(getFacesContext().getELContext());
			} catch (ELException e) {
				throw new FacesException(e);
			}

			return value;
		}
		// Default value
		return true;

	}

	/**
	 * Setter for enabled scroll control attribute
	 * @param _scrollControlEnabled
	 */
	public void setScrollControlEnabled(Boolean _scrollControlEnabled) {
		this._scrollControlEnabled = _scrollControlEnabled;
	}
	
	/**
	 * Getter for mouseScrollingEnabled attribute
	 * @return _mouseScrollingEnabled
	 */
	public Boolean getMouseScrollingEnabled() {
		if (this._mouseScrollingEnabled != null) {
			return this._mouseScrollingEnabled;
		}
		ValueExpression ve = getValueExpression("mouseScrollingEnabled");
		if (ve != null) {
			Boolean value = null;

			try {
				value = (Boolean) ve.getValue(getFacesContext().getELContext());
			} catch (ELException e) {
				throw new FacesException(e);
			}

			return value;
		}
		// Default value
		return true;

	}

	/**
	 * Setter for enabled mouse scroll attribute
	 * @param _mouseScrollingEnabled
	 */
	public void setMouseScrollingEnabled(Boolean _mouseScrollingEnabled) {
		this._mouseScrollingEnabled = _mouseScrollingEnabled;
	}
	
	/**
	 * Getter for panInOutEnabled attribute
	 * @return _panInOutEnabled
	 */
	public Boolean getPanInOutEnabled() {
		if (this._panInOutEnabled != null) {
			return this._panInOutEnabled;
		}
		ValueExpression ve = getValueExpression("panInOutEnabled");
		if (ve != null) {
			Boolean value = null;

			try {
				value = (Boolean) ve.getValue(getFacesContext().getELContext());
			} catch (ELException e) {
				throw new FacesException(e);
			}

			return value;
		}
		// Default value
		return true;

	}

	/**
	 * Setter for panInOutEnabled attribute
	 * @param _panInOutEnabled
	 */
	public void setPanInOutEnabled(Boolean _panInOutEnabled) {
		this._panInOutEnabled = _panInOutEnabled;
	}
	
	public Map<String, Boolean> _getUIOptions() {
		if (__uiOptions == null) {
			__uiOptions = new HashMap<String, Boolean>();
		}
		
		return __uiOptions;
	}

	/**
	 * Returns map of UI options
	 * @return
	 */
	public Map<String, Boolean> getUIOptionsMap() {
		Map<String, Boolean> options = new HashMap<String, Boolean>();
		Map<String, Boolean> uiOptionsAttr = getUIOptions();
		if (uiOptionsAttr != null) {
			options.putAll(uiOptionsAttr);
		}
		
		if (__uiOptions != null) {
			options.putAll(__uiOptions);
		}
		return options;
	}

	/* (non-Javadoc)
	 * @see javax.faces.component.UIComponentBase#saveState(javax.faces.context.FacesContext)
	 */
	@Override
	public Object saveState(FacesContext context) {
		Object[] state = new Object[21];
		state[0] = super.saveState(context);
		state[1] = _height;
		state[2] = _iserverLogin;
		state[3] = _iserverPassword;
		state[4] = _iserverUrl;
		state[5] = _onclick;
		state[6] = _ondblclick;
		state[7] = _onkeydown;
		state[8] = _onkeypress;
		state[9] = _onkeyup;
		state[10] = _onmousedown;
		state[11] = _onmousemove;
		state[12] = _onmouseout;
		state[13] = _onmouseover;
		state[14] = _onmouseup;
		state[15] = _reportName;
		state[16] = _runWhenRendered;
		state[17] = _width;
		state[18] = _scrollControlEnabled;
		state[19] = _mouseScrollingEnabled;
		state[20] = _panInOutEnabled;
		return state;
	}

	/* (non-Javadoc)
	 * @see javax.faces.component.UIComponentBase#restoreState(javax.faces.context.FacesContext, java.lang.Object)
	 */
	@Override
	public void restoreState(FacesContext context, Object state) {
		Object[] states = (Object[]) state;
		super.restoreState(context, states[0]);
		_height = (String) states[1];
		_iserverLogin = (String) states[2];
		_iserverPassword = (String) states[3];
		_iserverUrl = (String) states[4];
		_onclick = (String) states[5];
		_ondblclick = (String) states[6];
		_onkeydown = (String) states[7];
		_onkeypress = (String) states[8];
		_onkeyup = (String) states[9];
		_onmousedown = (String) states[10];
		_onmousemove = (String) states[11];
		_onmouseout = (String) states[12];
		_onmouseover = (String) states[13];
		_onmouseup = (String) states[14];
		_reportName = (String) states[15];
		_runWhenRendered = (Boolean) states[16];
		_width = (String) states[17];
		_scrollControlEnabled = (Boolean)states[18];
		_mouseScrollingEnabled = (Boolean)states[19];
		_panInOutEnabled = (Boolean)states[20];
	}

	/* (non-Javadoc)
	 * @see javax.faces.component.UIComponent#getFamily()
	 */
	@Override
	public String getFamily() {
		return COMPONENT_FAMILY;
	}

}
