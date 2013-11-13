/**
 * License Agreement.
 *
 * Rich Faces - Natural Ajax for Java Server Faces (JSF)
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

package com.exadel.birt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import com.exadel.jsfbirt.utils.BirtUtils;


/**
 * @author $Autor$
 *
 */
public class Bean {
	
	static final String REPORT_NAME = "/Reports/new_report_7.rptdocument";
	static final String PAGE_PARAMETER = "_page";
	
	String reportName = "/Home/administrator/dashboard/Dashboard/Invoices with delayed processing with param.rptdesign";
	
	String text = "Dynamic Text";
	Integer x = 10;
	String name = "Firrelli";
	
	Long pageNum = 1L;
	Long pageCount = null;
	List<Integer> pages = new ArrayList<Integer>();
	
	static Map<String, Boolean> options = new HashMap<String, Boolean>();
	static {
		options.put("enableMainMenu", Boolean.FALSE);
	}
	
	List<Param> params = new ArrayList<Param>(); 
	
	public static class Param {
		String name;
		
		Object value;
		
		public Param(String name, Object value) {
			this.name = name;
			this.value = value;
		}
		
		public String getName() {
			return name;
		}
		
		public Object getValue() {
			return value;
		}
	}
	
	public List<Param> getParams() {
		return params;
	}
	
	public void setParams(List<Param> params) {
		this.params = params;
	}
	
	public void changeParams(ActionEvent event) {
		params.clear();
		params.add(new Param("X", x));
		params.add(new Param("Text", text));
	}
	
	public Bean() {
		FacesContext context = FacesContext.getCurrentInstance();
		pageCount = BirtUtils.getPageCount(context, null, REPORT_NAME, null);
		for (int i=0;i<pageCount;i++) {
			pages.add(new Integer(i));
		}
		changeParams(null);
	}
	
	public void changePage(ActionEvent event) {
		FacesContext context = FacesContext.getCurrentInstance();
		String _page = context.getExternalContext().getRequestParameterMap().get(PAGE_PARAMETER);
		if (_page != null) {
			pageNum = Long.valueOf(_page);
		}
	}
	
	public Map<String, Boolean> getOptions() {
		return options;
	}
	
	public void nextPage(ActionEvent event) {
		if (pageNum < pageCount) {
			pageNum ++;
		}
	}
	
	public void previousPage(ActionEvent event) {
		if (pageNum > 1) {
			pageNum --;
		}
	}
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Integer getX() {
		return x;
	}
	public void setX(Integer x) {
		this.x = x;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getReportName() {
		return reportName;
	}
	
	public void setReportName(String reportName) {
		this.reportName = reportName;
	}
	
	public Long getPageNum() {
		return pageNum;
	}
	
	public void setPageNum(Long pageNum) {
		this.pageNum = pageNum;
	}
	
	public List<Integer> getPages() {
		return pages;
	}
	
	public Long getPageCount() {
		return pageCount;
	}
}