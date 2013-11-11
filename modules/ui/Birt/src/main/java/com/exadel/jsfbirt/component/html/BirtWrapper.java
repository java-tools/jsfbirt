package com.exadel.jsfbirt.component.html;

import java.util.Locale;

import javax.el.ELException;
import javax.el.ValueExpression;
import javax.faces.FacesException;
import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.FacesEvent;

import com.exadel.jsfbirt.engine.BirtEngine;
import com.exadel.jsfbirt.engine.BirtRendererUtils;
import com.exadel.jsfbirt.event.ExportFormatEvent;
import com.exadel.jsfbirt.renderkit.html.BirtWrapperRenderer;

/**
 * Component class for birt wrapper Component is responsible for displaying of
 * open-source based birt wrappers. It uses BIRT engine API to generate report
 * HTML code and puts it on appropriate place on client side
 * 
 * @author Andrey Markavtsov
 * 
 */
public class BirtWrapper extends UIComponentBase {

	/** Component family */
	public static final String COMPONENT_FAMILY = "com.exadel.jsfbirt.BirtWrapper";

	/** Component type */
	public static final String COMPONENT_TYPE = "com.exadel.jsfbirt.BirtWrapper";

	/**
	 * configPath attribute
	 * Defines relative path to BIRT engine's configuration folder 
	 * It should be placed under server according to BIRT runtime installation instruction 
	 */
	private String _configPath = null;

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
	 * reportDesign attribute
	 * Defines relative path to BIRT report design file which should be placed under server 
	 * and should available through resource lookup
	 */
	private String _reportDesign = null;
	
	/**
	 * reportDocument attribute
	 * Defines relative path to BIRT report document file which should be placed under server 
	 * and should available through resource lookup
	 */
	private String _reportDocument = null;


	/**
	 * locale attribute
	 * Defines locale to be used for BIRT engine 
	 */
	private Locale _locale = null;

	/**
	 * svgAllowed attribute
	 * Defines if SVG format is allowed
	 */
	private Boolean _svgAllowed = null;
	
	/**
	 * pageNum attribute
	 * Defines page number of report to be rendered
	 */
	private Long _pageNum = null;
	
	
	/**
	 * pageRange attribute
	 * Defines page range to be rendered
	 */
	private String _pageRange = null;
	
	/**
	 * Constructor for BIRT wrapper component class
	 */
	public BirtWrapper() {
		setRendererType("com.exadel.jsfbirt.BirtRenderer");
	}

	/**
	 * Getter for configPath attribute
	 * @return
	 */
	public String getConfigPath() {
		if (this._configPath != null) {
			return this._configPath;
		}
		ValueExpression ve = getValueExpression("configPath");
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
	 * Setter for configPath attribute
	 * @param _configPath
	 */
	public void setConfigPath(String _configPath) {
		this._configPath = _configPath;
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
	 * Getter for locale attribute
	 * @return _locale
	 */
	public Locale getLocale() {
		if (this._locale != null) {
			return this._locale;
		}
		ValueExpression ve = getValueExpression("locale");
		if (ve != null) {
			Locale value = null;

			try {
				value = (Locale) ve.getValue(getFacesContext().getELContext());
			} catch (ELException e) {
				throw new FacesException(e);
			}

			return value;
		}

		return null;

	}

	/**
	 * Setter for locale attribute
	 * @param _locale
	 */
	public void setLocale(Locale _locale) {
		this._locale = _locale;
	}

	/**
	 * Getter for svgAllowed attribute
	 * @return _svgAllowed
	 */
	public Boolean getSvgAllowed() {
		if (this._svgAllowed != null) {
			return this._svgAllowed;
		}
		ValueExpression ve = getValueExpression("svgAllowed");
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
		return Boolean.TRUE;

	}

	/**
	 * Setter for svgAllowed attribute
	 * @param _svgAllowed
	 */
	public void setSvgAllowed(Boolean _svgAllowed) {
		this._svgAllowed = _svgAllowed;
	}
	
	/**
	 * Getter for pageNum attribute
	 * @return _pageNum
	 */
	public Long getPageNum() {
		if (this._pageNum != null) {
			return this._pageNum;
		}
		ValueExpression ve = getValueExpression("pageNum");
		if (ve != null) {
			Long value = null;

			try {
				value = (Long) ve.getValue(getFacesContext().getELContext());
			} catch (ELException e) {
				throw new FacesException(e);
			}

			return value;
		}
		return null;

	}

	/**
	 * Setter for pageNum attribute
	 * @param _pageNum
	 */
	public void setPageNum(Long _pageNum) {
		this._pageNum = _pageNum;
	}
	
	/**
	 * Getter for pageRange attribute
	 * @return _pageRange
	 */
	public String getPageRange() {
		if (this._pageRange != null) {
			return this._pageRange;
		}
		ValueExpression ve = getValueExpression("pageRange");
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
	 * Setter for pageRange attribute
	 * @param _pageRange
	 */
	public void setPageRange(String _pageRange) {
		this._pageRange = _pageRange;
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
	 * Getter for reportDesign attribute
	 * @return _reportDesign
	 */
	public String getReportDesign() {
		if (this._reportDesign != null) {
			return this._reportDesign;
		}
		ValueExpression ve = getValueExpression("reportDesign");
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
	 * Setter for reportDesign attribute
	 * @param _reportDesign
	 */
	public void setReportDesign(String _reportDesign) {
		this._reportDesign = _reportDesign;
	}
	
	/**
	 * Getter for reportDocument attribute
	 * @return _reportDocument
	 */
	public String getReportDocument() {
		if (this._reportDocument != null) {
			return this._reportDocument;
		}
		ValueExpression ve = getValueExpression("reportDocument");
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
	 * Setter for reportDocument attribute
	 * @param _reportDocument
	 */
	public void setReportDocument(String _reportDocument) {
		this._reportDocument = _reportDocument;
	}

	/* (non-Javadoc)
	 * @see javax.faces.component.UIComponent#getFamily()
	 */
	public String getFamily() {
		return COMPONENT_FAMILY;
	}

	/* (non-Javadoc)
	 * @see javax.faces.component.UIComponentBase#saveState(javax.faces.context.FacesContext)
	 */
	@Override
	public Object saveState(FacesContext context) {
		Object[] state = new Object[18];
		state[0] = super.saveState(context);
		state[1] = _configPath;
		state[2] = _onclick;
		state[3] = _ondblclick;
		state[4] = _onkeydown;
		state[5] = _onkeypress;
		state[6] = _onkeyup;
		state[7] = _onmousedown;
		state[8] = _onmousemove;
		state[9] = _onmouseout;
		state[10] = _onmouseover;
		state[11] = _onmouseup;
		state[12] = _reportDesign;
		state[13] = _reportDocument;
		state[14] = _pageRange;
		state[15] = _pageNum;
		state[16] = _locale;
		state[17] = _svgAllowed;
		return state;
	}

	/* (non-Javadoc)
	 * @see javax.faces.component.UIComponentBase#restoreState(javax.faces.context.FacesContext, java.lang.Object)
	 */
	@Override
	public void restoreState(FacesContext context, Object state) {
		Object[] states = (Object[]) state;
		super.restoreState(context, states[0]);
		_configPath = (String) states[1];
		_onclick = (String) states[2];
		_ondblclick = (String) states[3];
		_onkeydown = (String) states[4];
		_onkeypress = (String) states[5];
		_onkeyup = (String) states[6];
		_onmousedown = (String) states[7];
		_onmousemove = (String) states[8];
		_onmouseout = (String) states[9];
		_onmouseover = (String) states[10];
		_onmouseup = (String) states[11];
		_reportDesign = (String) states[12];
		_reportDocument = (String)states[13];
		_pageRange = (String)states[14];
		_pageNum = (Long)states[15];
		_locale = (Locale)states[16];
		_svgAllowed = (Boolean)states[17];

	}
	
	/* (non-Javadoc)
	 * @see javax.faces.component.UIComponentBase#broadcast(javax.faces.event.FacesEvent)
	 */
	@Override
	public void broadcast(FacesEvent event) throws AbortProcessingException {
		if (event instanceof ExportFormatEvent) {
			new BirtRendererUtils().renderBirt(getFacesContext(), this, ((ExportFormatEvent)event).getFormat());
			getFacesContext().responseComplete();
		}
		super.broadcast(event);
	}

}
