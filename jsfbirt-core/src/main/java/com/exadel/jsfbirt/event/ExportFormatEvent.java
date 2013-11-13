package com.exadel.jsfbirt.event;

import javax.faces.component.UIComponent;
import javax.faces.event.FacesEvent;
import javax.faces.event.FacesListener;

/**
 * Class for event which will be generated when retrieved special request from client side to get report in specified format
 * @author Andrey Markavtsov
 *
 */
public class ExportFormatEvent extends FacesEvent {
	
	private static final long serialVersionUID = -6914219973014862766L;
	
	/** Report format requested */
	String format;

	/**
	 * Constructor for event
	 * @param component
	 * @param format
	 */
	public ExportFormatEvent(UIComponent component, String format) {
		super(component);
		this.format = format;
	}


	/* (non-Javadoc)
	 * @see javax.faces.event.FacesEvent#isAppropriateListener(javax.faces.event.FacesListener)
	 */
	@Override
	public boolean isAppropriateListener(FacesListener listener) {
		return false;
	}

	/* (non-Javadoc)
	 * @see javax.faces.event.FacesEvent#processListener(javax.faces.event.FacesListener)
	 */
	@Override
	public void processListener(FacesListener listener) {
		
	}
	
	/**
	 * Gets report format requested
	 * @return
	 */
	public String getFormat() {
		return format;
	}

}
