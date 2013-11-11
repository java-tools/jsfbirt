<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> 

<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib prefix="birt" uri="http://jsf4birt.components" %>

<html>
<f:view>
	<head>
		
	</head>
	<body>
	<a href="javascript: void(0)" onclick="JSF4Actuate.pdf('form2:t')">PDF</a><br/>
	<a href="javascript: void(0)" onclick="JSF4Actuate.doc('form2:t')">DOC</a><br/>
	<a href="javascript: void(0)" onclick="JSF4Actuate.xls('form2:t')">XLS</a>
	<h:form id="form">
		<table cellpadding="5" cellspacing="0">
			<tr>
				<td><h:outputText value="Dynamic Text: " style="float: left; font-weight: bold" /></td>
				<td><h:inputText value="#{bean.text}" /></td>
			</tr>
			<tr>
				<td><h:outputText value="Report Parameter (number): "
					style="float: left; font-weight: bold" /></td>
				<td><h:inputText id="x" value="#{bean.x}" /><h:message for="x" />
				</td>
			</tr>
		</table>
		<h:commandLink actionListener="#{bean.changeParams}">Send</h:commandLink>
	</h:form>
	<h:form id="form2">
		<script>
			<!-- <[CDATA[
			function showDetails(name) {
					window.open('/birt-demo/pages/reportDetail.jsf?name=' + name);
			}
			]]> -->
		</script>
		<br/><br/>
		<span style="font-weight: bold; color: blue;">Click on category item !!</span>
		<birt:birtWrapper id="t"  reportDesign="/Reports/new_report.rptdesign" rendered="true">
		
		<f:param name="Text" value="#{bean.text}"></f:param>
		<f:param name="X" value="#{bean.x}"></f:param>
		
		</birt:birtWrapper>
		
		
	</h:form>	
	</body>
</f:view>
</html>
