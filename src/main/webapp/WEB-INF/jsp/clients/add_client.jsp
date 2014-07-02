<%@ include file="/WEB-INF/jsp/include.jsp" %>
<%@page import="pl.radek.dvd.model.Client, pl.radek.dvd.model.Constants, java.util.List"%>
<%@page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="pl" lang="pl">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-2" />
<title><spring:message code="common.header.title" /></title>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/addedit.css" />" />
 <script language="JavaScript" src="jsp/js/gen_validatorv4.js" type="text/javascript" xml:space="preserve">
 </script>
</head>
<body>
<div class="container">
<div id = "myform">

<c:choose>
<c:when test="${client.id =='-1'}">
   <div class="header">
  <h3> <spring:message code="clients.add.header"/> </h3>
  <p> <spring:message code="common.fillin"/> </p>
   </div>
	<div class="separator"></div>
	 <form:form method="POST" commandName="client" action="addclient.htm">
	 <div class="inputs">
<table>
    <tbody><tr>
        <td><form:hidden path="id"></form:hidden></td>
    </tr>
	<tr>
        <td><form:label path="firstName"><spring:message code="clients.clientsList.firstName"/>:</form:label></td>
        <td><form:input path="firstName" cssClass = "inputs"></form:input></td>
		<td><form:errors path="firstName" cssClass="error"></form:errors></td>
    </tr>
    <tr>
        <td><form:label path="lastName"><spring:message code="clients.clientsList.lastName"/>:</form:label></td>
        <td><form:input path="lastName" cssClass = "inputs"></form:input></td>
		<td><form:errors path="lastName" cssClass="error"></form:errors></td>
    </tr>
	<tr>
        <td><form:label path="pesel"><spring:message code="clients.clientsList.pesel"/>:</form:label></td>
        <td><form:input path="pesel" cssClass = "inputs"></form:input></td>
		<td><form:errors path="pesel" cssClass="error"></form:errors></td>
    </tr>
	<tr>
        <td><form:label path="city"><spring:message code="clients.clientsList.city"/>:</form:label></td>
        <td><form:input path="city" cssClass = "inputs"></form:input></td>
		<td><form:errors path="city" cssClass="error"></form:errors></td>
    </tr>
	<tr>
        <td><form:label path="street"><spring:message code="clients.clientsList.street"/>:</form:label></td>
        <td><form:input path="street" cssClass = "inputs"></form:input></td>
		<td><form:errors path="street" cssClass="error"></form:errors></td>
    </tr>
	<tr>
        <td><form:label path="phoneNumber"><spring:message code="clients.clientsList.phoneNumber"/>:</form:label></td>
        <td><form:input path="phoneNumber" cssClass = "inputs"></form:input></td>
		<td><form:errors path="phoneNumber" cssClass="error"></form:errors></td>
    </tr>
	<tr>
        <td><form:label path="email"><spring:message code="clients.clientsList.email"/>:</form:label></td>
        <td><form:input  path="email" cssClass = "inputs"></form:input></td>
		<td><form:errors path="email" cssClass="error"></form:errors></td>
    </tr>
	<tr><td></td></tr>
    <tr>
        <td colspan="2">
            <input type="submit" value="<spring:message code="common.button.submit"/>" >
        </td>
        <td></td>
        <td></td>
    </tr>
</tbody></table> 

</form:form>
	 
     <form name="cancel" action="<c:url value="clientslist.htm"/>" method="get">
	 <table>
	   <tr>
		<td colspan="2">
           <input type="submit" value="<spring:message code="common.button.cancel"/>" />
		</td>
		<td></td>
        <td></td>
	   </tr>
	  </table>
     </form>
</div> 
</c:when>
<c:otherwise>
	<div class="header">
	<h3> <spring:message code="clients.edit.header"/> </h3>
	<p> <spring:message code="common.fillin"/> </p>
	</div>
	<div class="separator"></div>
	
	 <form:form method="POST" commandName="client" action="updateclient.htm">
	 <div class="inputs">
<table>
    <tbody><tr>
        <td><form:hidden path="id"></form:hidden></td>
    </tr>
	<tr>
        <td><form:label path="firstName"><spring:message code="clients.clientsList.firstName"/>:</form:label></td>
        <td><form:input path="firstName" cssClass = "inputs"></form:input></td>
		<td><form:errors path="firstName" cssClass="error"></form:errors></td>
    </tr>
    <tr>
        <td><form:label path="lastName"><spring:message code="clients.clientsList.lastName"/>:</form:label></td>
        <td><form:input path="lastName" cssClass = "inputs"></form:input></td>
		<td><form:errors path="lastName" cssClass="error"></form:errors></td>
    </tr>
	<tr>
        <td><form:label path="pesel"><spring:message code="clients.clientsList.pesel"/>:</form:label></td>
        <td><form:input path="pesel" cssClass = "inputs"></form:input></td>
		<td><form:errors path="pesel" cssClass="error"></form:errors></td>
    </tr>
	<tr>
        <td><form:label path="city"><spring:message code="clients.clientsList.city"/>:</form:label></td>
        <td><form:input path="city" cssClass = "inputs"></form:input></td>
		<td><form:errors path="city" cssClass="error"></form:errors></td>
    </tr>
	<tr>
        <td><form:label path="street"><spring:message code="clients.clientsList.street"/>:</form:label></td>
        <td><form:input path="street" cssClass = "inputs"></form:input></td>
		<td><form:errors path="street" cssClass="error"></form:errors></td>
    </tr>
	<tr>
        <td><form:label path="phoneNumber"><spring:message code="clients.clientsList.phoneNumber"/>:</form:label></td>
        <td><form:input path="phoneNumber" cssClass = "inputs"></form:input></td>
		<td><form:errors path="phoneNumber" cssClass="error"></form:errors></td>
    </tr>
	<tr>
        <td><form:label path="email"><spring:message code="clients.clientsList.email"/>:</form:label></td>
        <td><form:input path="email" cssClass = "inputs"></form:input></td>
		<td><form:errors path="email" cssClass="error"></form:errors></td>
    </tr>
	<tr><td></td></tr>
    <tr>
        <td colspan="2">
            <input type="submit" value="<spring:message code="common.button.submit"/>" >
        </td>
        <td></td>
        <td></td>
    </tr>
</tbody></table>  
</form:form>
	 
     <form name="cancel" action="<c:url value="clientslist.htm"/>" method="get">
	 <table>
	   <tr>
		<td colspan="2">
           <input type="submit" value="<spring:message code="common.button.cancel"/>" />
		</td>
		<td></td>
        <td></td>
	   </tr>
	  </table>
     </form>
</div>
</c:otherwise>
</c:choose>
</div></div>
</body>
</html>