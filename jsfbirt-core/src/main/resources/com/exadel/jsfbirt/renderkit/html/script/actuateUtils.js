(function () {

	if (!window.JSF4Actuate) {
		window.JSF4Actuate = {reports : {}, initPending : false, actuateLoadPending : false};
	}

	JSF4Actuate.RunReport = function(id) {
		
				if (!window.actuate) {
					JSF4Actuate.loadActuate(id);
					return;
				}
		
				try {
					if (!actuate.isInitialized()) {
						if (!JSF4Actuate.initPending) {
							JSF4Actuate._init(id);
						}else {
							JSF4Actuate.reports[id] = id;
						}
					} else {
						JSF4Actuate._RunReport(document.getElementById(id));
					}

				} catch (e) {
					
				}
		};
		
		JSF4Actuate.loadActuate = function (id) {
			if (JSF4Actuate.actuateLoadPending) {
				JSF4Actuate.reports[id] = id;
				return;
			}
			JSF4Actuate.actuateLoadPending = true;
			var e = document.getElementById(id);
			var src = e.getAttribute('iserverurl') + '/jsapi';
			var script = document.createElement('script');
			script.src = src;
			script.type = 'text/javascript';
			
			var onLoad = function () {
				if (!window.actuate && this.innerHTML && this.innerHTML != '') {
					if (window.execScript) {
						window.execScript(this.innerHTML);
					}else {
						window.eval(this.innerHTML);
					}
				}
				JSF4Actuate.actuateLoadPending = false;
				JSF4Actuate.RunReport(id);
			};
			script.onreadystatechange = onLoad;
			script.onload = onLoad;
			
			var head = document.getElementsByTagName("head")[0]||document.documentElement;
			head.appendChild(script);
		};
		
		JSF4Actuate._init = function (id) {
			
			if (!actuate) {
				return;
			}

			JSF4Actuate.initPending = true;
			
			if (document.readyState || document.loaded) {
				if ((document.readyState && document.readyState != 'complete') || (!document.readyState && document.loaded == false)) {
	  				if (window.addEventListener) {
	  					window.addEventListener("load", function () {JSF4Actuate._init(id);}, false);
				      } else {
				    	  window.attachEvent("onload", function () {JSF4Actuate._init(id);});
				      }
					return;
				}	
			}
	
			var el = document.getElementById(id);			
			var url = el.getAttribute('iserverurl');
			var login = el.getAttribute('iserverlogin');
			var password = el.getAttribute('iserverpassword') ? el
					.getAttribute('iserverpassword') : "";

			//actuate.load("parameter");
			actuate.load("viewer");

			JSF4Actuate.reports[id] = id;

			actuate.initialize(url, null, login, password,
					JSF4Actuate._postInit);

		};

		JSF4Actuate._postInit = function() {
			JSF4Actuate.initPending = false;
			JSF4Actuate.initialized = true;
			for ( var id in JSF4Actuate.reports) {
				if (id) {
					var el = document.getElementById(id);
					JSF4Actuate._RunReport(el);
				}
			}

			JSF4Actuate.reports = {};
		};
		
		JSF4Actuate.SessionTimeoutHandler = function (view) {
			var c = view.getHelper().container;
			var id = c.id;
			if (c && !JSF4Actuate.initPending) {
				JSF4Actuate.initPending = true;
				var url = c.getAttribute('iserverurl');
				var login = c.getAttribute('iserverlogin');
				var password = c.getAttribute('iserverpassword') ? c
						.getAttribute('iserverpassword') : "";
						
				actuate.authenticate(url, null, login, password, null, function (){
					JSF4Actuate.initPending = false;
					c.component.destroy();
					JSF4Actuate.RunReport(id);
				}, null);
			}
		};

		JSF4Actuate._RunReport = function(el) {

			var reportName = el.getAttribute('reportname');
			if (el.getAttribute('options')) {
				var options = window.eval(el.getAttribute('options'))[0];
			}

			var id = el.id;
			var w = el.offsetWidth; 
			var h = el.offsetHeight; 
			try {
				
			   var scrollPanel = new actuate.viewer.ScrollPanel();
			   scrollPanel.setScrollControlEnabled(options.scrollControl);
			   scrollPanel.setPanInOutEnabled(options.panInOut);
			   scrollPanel.setMouseScrollingEnabled(options.mouseScroll);
			   var config = new actuate.viewer.UIConfig();
			   config.setContentPanel(scrollPanel);

				var viewer = new actuate.Viewer(id, config);
				viewer.registerEventHandler(actuate.viewer.EventConstants.ON_SESSION_TIMEOUT, JSF4Actuate.SessionTimeoutHandler); 
	
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
					// viewer.setUIOptions(uiOptions);
					for ( var o in options.uioptions) {
						if (typeof (uiOptions[o]) == 'function') {
							uiOptions[o](options.uioptions[o]);
						}
					}
					viewer.setUIOptions(uiOptions);
				}
				
				el.component = {};
				el.component['rich:destructor'] = "destroy";
				el.component.destroy = function() {
					try {
						var v = actuate.getViewer(id);
						if (v && v._) {
							v._.__reset();
							if (actuate.viewer && actuate.viewer.impl
									&& actuate.viewer.impl.Viewer) {
								actuate.viewer.impl.Viewer._viewersMap.remove(id);
							}
						}
					} catch (e) {
						alert(e);
					}
				};
	
				viewer.submit();
	
			} catch (e) {
				
			}

	};

	JSF4Actuate.pdf = function(id, target) {
		this._export(id, 'pdf', target);
	};

	JSF4Actuate.xls = function(id, target) {
		this._export(id, 'xls', target);
	};

	JSF4Actuate.doc = function(id, target) {
		this._export(id, 'doc', target);
	};

	JSF4Actuate._export = function(id, format, target) {
		var el = document.getElementById(id);
		if (el) {
			var form = this._getForm(el);
			if (form) {
				var i = document.createElement('input');
				i.name = id + '_format';
				i.value = format;
				i.style.display = 'none';
				var oldTarget = form.getAttribute('target');
				try {
					form.setAttribute('target', target ? target : '_blank');
					form.appendChild(i);
					form.submit();
				} finally {
					form.setAttribute('target', oldTarget);
					form.removeChild(i);
				}
			}
		}
	};

	JSF4Actuate._getForm = function(el) {
		while (el && el.tagName.toLowerCase() != 'form') {
			el = el.parentNode;
		}
		return el;
	};

})();
