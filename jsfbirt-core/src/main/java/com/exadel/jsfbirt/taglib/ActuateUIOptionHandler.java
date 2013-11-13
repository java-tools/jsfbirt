package com.exadel.jsfbirt.taglib;

import java.io.IOException;

import javax.el.ELException;
import javax.faces.FacesException;
import javax.faces.component.UIComponent;

import com.exadel.jsfbirt.component.html.ActuateWrapper;
import com.sun.facelets.FaceletContext;
import com.sun.facelets.FaceletException;
import com.sun.facelets.tag.TagAttribute;
import com.sun.facelets.tag.TagConfig;
import com.sun.facelets.tag.TagHandler;

/**
 * Facelets handler for actuateUIOption tag
 * @author Andrey Markavtsov
 *
 */
public class ActuateUIOptionHandler extends TagHandler {
	
	/** Option name */
	TagAttribute name = null;
	
	/** Option value */
	TagAttribute value = null;

	/**
	 * Handler constructor
	 * @param config
	 */
	public ActuateUIOptionHandler(TagConfig config) {
		super(config);
		
		name = getAttribute("name");
		value = getAttribute("value");
	}

	/* (non-Javadoc)
	 * @see com.sun.facelets.FaceletHandler#apply(com.sun.facelets.FaceletContext, javax.faces.component.UIComponent)
	 */
	public void apply(FaceletContext ctx, UIComponent parent)
			throws IOException, FacesException, FaceletException, ELException {
		ActuateWrapper wrapper = null;
		if (parent instanceof ActuateWrapper) {
			wrapper = (ActuateWrapper)parent;
			Boolean o = value.getBoolean(ctx);
			if (o == null) {
				o = true;
			}
			wrapper._getUIOptions().put(name.getValue(ctx), o);
		}
		
	}

}
