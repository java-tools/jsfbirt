package com.exadel.jsfbirt.taglib;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.webapp.UIComponentClassicTagBase;
import javax.servlet.jsp.JspException;

import com.exadel.jsfbirt.component.html.ActuateWrapper;

public class ActuateUIOptionTag extends UIComponentClassicTagBase {
	
	ValueExpression _name;
	ValueExpression _value;
	
	@Override
	public int doStartTag() throws JspException {
		UIComponent component = getParentUIComponentClassicTagBase(pageContext)
						.getComponentInstance();
		if (component instanceof ActuateWrapper) {
			ActuateWrapper actuateWrapper = (ActuateWrapper)component;
			
			String name = (String)_name.getValue(getELContext());
			Object o = null;
			Boolean value = null;
			if (_value.isLiteralText()) {
				value = Boolean.valueOf((String)o);
			}else {
				value = (Boolean)value;
			}
			actuateWrapper._getUIOptions().put(name, value);
		}
		return EVAL_BODY_BUFFERED;
	}
	
	@Override
	public void release() {
		_name = null;
		_value = null;
	}
	
	@Override
	public int doAfterBody() throws JspException {
		return EVAL_BODY_INCLUDE;
	}
	
	@Override
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}

	@Override
	protected UIComponent createComponent(FacesContext context, String newId)
			throws JspException {
		return null;
	}

	@Override
	protected boolean hasBinding() {
		return false;
	}

	@Override
	protected void setProperties(UIComponent component) {
		
	}

	@Override
	public String getComponentType() {
		return null;
	}

	@Override
	public String getRendererType() {
		return null;
	}

	public void setName(ValueExpression _name) {
		this._name = _name;
	}

	public void setValue(ValueExpression _value) {
		this._value = _value;
	}

	
		
}
