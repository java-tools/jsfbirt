<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> 

<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="birt" uri="http://jsf4birt.components" %>

<html>
<f:view>

	<body>

	<h:form id="form">
		<c:forEach begin="1" end="1" varStatus="i">
		<birt:actuateWrapper 
			runWhenRendered="true"
			scrollControlEnabled="false"
			mouseScrollingEnabled="false"
			panInOutEnabled="false"
			emptyReportMessage="Select a report"
			width="500px"
			reportName="#{bean.reportName}"
			height="500px"
			style="">
			<f:param name="contextParam" value="tut.by/login?p=2"/>
			<birt:actuateUIOption name="enableMainMenu" value="false" />
			<f:facet name="emptyReport">
				<h:panelGroup layout="block" style="background-color: green">
					<table style="width: 100%; height: 100%">
						<tr>
							<td>Select a report</td>
						</tr>
					</table>
				</h:panelGroup>
			</f:facet>
			<f:facet name="header">
				<h:panelGroup layout="block" style="position: absolute; right: 20px; margin-top: 3px;">
					<h:selectOneMenu value="#{bean.reportName}" onchange="document.getElementById('form:refresh').onclick(event)">
						<f:selectItem itemLabel="/Home/administrator/MyCustomers.rptdesign" itemValue="/Home/administrator/MyCustomers.rptdesign"/>
						<f:selectItem itemLabel="/Home/administrator/Customer Dashboard.rptdesign" itemValue="/Home/administrator/Customer Dashboard.rptdesign"/>
					</h:selectOneMenu>
				</h:panelGroup>
			</f:facet>
		</birt:actuateWrapper>
		</c:forEach>
		
		
		
	</h:form>
	
	</body>
</f:view>
</html>
