(function () {
	
if (window.JSF4Actuate) {
	return;
}

window.JSF4Actuate = { 
		
	reports : {},
	initMutex : false,
		
	RunReport : function (id) {

	window.setTimeout(function () {
	var el = document.getElementById(id);
	
	if (!actuate) {
		return;
	}
		
	if (!actuate.isInitialized() && !JSF4Actuate.initMutex) {
		JSF4Actuate.initMutex = true;
		
		if (JSF4Actuate.initMutex) {
			var url = el.getAttribute('iserverurl');
			var login = el.getAttribute('iserverlogin');
			var password = el.getAttribute('iserverpassword') ? el.getAttribute('iserverpassword') : "";
		
			actuate.load("parameter");
			actuate.load("viewer");
			
		
			JSF4Actuate.reports[id] = id;
		
			actuate.initialize( url, null, login, password, function () { JSF4Actuate._postInit(); });
		}else {
			JSF4Actuate.reports[id] = id;
		}
		
	}else if (JSF4Actuate.initMutex) {
		JSF4Actuate.reports[id] = id;
	} else if (actuate.isInitialized() && !JSF4Actuate.initMutex) {
		JSF4Actuate._RunReport(el);
	}}, 10);
},


_postInit : function () {
	
	this.initMutex = false;
	
	for (var id in this.reports) {
		var el = document.getElementById(id);
		this._RunReport(el);
	}
	
	this.reports = {};
},

_RunReport : function (el) {
	
	var reportName = el.getAttribute('reportname');
	if (el.getAttribute('options')) {
		var options = window.eval(el.getAttribute('options'))[0];
	}
	
	var id = el.id;
	var w = el.offsetWidth; //(el.style.width) ? el.style.width.replace(/%px/g, '') : null ;
	var h = el.offsetHeight; //(el.style.height) ? el.style.height.replace(/%px/g, '') : null ;
	try {
	var viewer = new actuate.Viewer(id);
	if (h) {
		viewer.setHeight(parseInt(h));
	}
	if (w) {
		viewer.setWidth(parseInt(w));
	}
	viewer.setReportName(reportName);
	
	var paramsStr = el.getAttribute('params');
	if (paramsStr) {
		var p = window.eval(paramsStr);
		if (p && p.length > 0) {
			viewer.setParameters(window.eval(p[0]));
		}
	}
	
	if (options && options.uioptions) {
		var uiOptions = new actuate.viewer.UIOptions();
		//viewer.setUIOptions(uiOptions);
		for (var o in options.uioptions) {
			if (typeof(uiOptions[o]) == 'function') {
				uiOptions[o](options.uioptions[o]);
			}
		}
		viewer.setUIOptions(uiOptions);
	}
	
	viewer.submit();
	//viewer.submit(function () { JSF4Actuate._setOptions(id, options); });
	
	el.component = {};
	
	el.component['rich:destructor'] = "destroy";
	
	el.component.destroy = function () {
		try {
			var v = actuate.getViewer(id);
			if (v && v._) {
				v._.__reset();
				if (actuate.viewer && actuate.viewer.impl && actuate.viewer.impl.Viewer) {
					actuate.viewer.impl.Viewer._viewersMap.remove(id);
				}
			}
			var d = document.getElementById(id);
			if (d) {
				//d.innerHTML = '';
			}
		}catch(e) {
			;
		}
	};
	}catch(e) {
		//
	}
},

_setOptions : function (id, options) {
	var viewer = actuate.getViewer(id);
	if (options && options.uiOptions) {
		var uiOptions = new actuate.viewer.UIOptions();
		for (var o in options.uiOptions) {
			if (typeof(uiOptions[o]) == 'function') {
				uiOptions[o](options.uiOptions[o]);
			}
		}
		viewer.setUIOptions(uiOptions);
	}
},

pdf : function (id, target) {
	this._export(id, 'pdf', target);
},

xls : function (id, target) {
	this._export(id, 'xls', target);
},

doc : function (id, target) {
	this._export(id, 'doc', target);
},

_export : function (id, format, target) {
	var el = document.getElementById(id);
	if (el) {
		var form = this._getForm(el);
		if (form) {
			var i = document.createElement('input');
			i.name = id + '_format';
			i.value = format;
			var oldTarget = form.getAttribute('target');
			try {
				form.setAttribute('target', target ? target : '_blank');
				form.appendChild(i);
				form.submit();
			}finally {
				form.setAttribute('target', oldTarget);
				form.removeChild(i);
			}
		}
	}
},

_getForm : function (el) {
	while (el && el.tagName.toLowerCase() != 'form') {
		el = el.parentNode;
	}
	return el;
}}})();
