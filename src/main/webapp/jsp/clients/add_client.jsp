<%@ include file="/jsp/include.jsp" %>
<%@page import="pl.radek.dvd.model.Client, pl.radek.dvd.model.Constants, java.util.List"%>
<%@page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="pl" lang="pl">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-2" />
<title><spring:message code="header.title" /></title>
<link rel="stylesheet" type="text/css" href="jsp/css/main.css" />
<link rel="stylesheet" type="text/css" href="jsp/css/button.css" />
<link rel="stylesheet" type="text/css" href="jsp/css/form.css" />

   <script language="JavaScript" src="jsp/js/gen_validatorv4.js" type="text/javascript" xml:space="preserve">
   </script>
</head>
<body>
<div id="container">
<div id = "content">


<c:choose>
<c:when test="${client.id =='-1'}">
   
	 <h1><spring:message code="clients.addnew"/> </h1>
	 <form:form method="POST" commandName="client" action="addclient.htm">
<table>
    <tbody><tr>
        <td><form:hidden path="id"></form:hidden></td>
    </tr>
	<tr>
        <td><form:label path="firstName"><spring:message code="firstname"/>:</form:label></td>
        <td><form:input path="firstName" cssClass = "inputs"></form:input></td>
		<td><span class="error">${errors.first_name}</span><td>
    </tr>
    <tr>
        <td><form:label path="lastName"><spring:message code="lastname"/>:</form:label></td>
        <td><form:input path="lastName" cssClass = "inputs"></form:input></td>
		<td><span class="error">${errors.last_name}</span><td>
    </tr>
	<tr>
        <td><form:label path="pesel"><spring:message code="pesel"/>:</form:label></td>
        <td><form:input path="pesel" cssClass = "inputs"></form:input></td>
		<td><span class="error">${errors.pesel}</span><td>
    </tr>
	<tr>
        <td><form:label path="city"><spring:message code="city"/>:</form:label></td>
        <td><form:input path="city" cssClass = "inputs"></form:input></td>
		<td><span class="error">${errors.city}</span><td>
    </tr>
	<tr>
        <td><form:label path="street"><spring:message code="street"/>:</form:label></td>
        <td><form:input path="street" cssClass = "inputs"></form:input></td>
		<td><span class="error">${errors.street}</span><td>
    </tr>
	<tr>
        <td><form:label path="phoneNumber"><spring:message code="phonenumber"/>:</form:label></td>
        <td><form:input path="phoneNumber" cssClass = "inputs"></form:input></td>
		<td><span class="error">${errors.phone_number}</span><td>
    </tr>
	<tr>
        <td><form:label path="email"><spring:message code="email"/>:</form:label></td>
        <td><form:input path="email" cssClass = "inputs"></form:input></td>
		<td><span class="error">${errors.email}</span><td>
    </tr>
	<tr><td></td></tr>
    <tr>
        <td colspan="2">
            <input type="submit" value="<spring:message code="button.submit"/>" class = "myButton">
        </td>
        <td></td>
        <td></td>
    </tr>
</tbody></table>  
</form:form>
	 
     <form name="cancel" action="<c:url value="clients.htm"/>" method="get">
	 <table>
	   <tr>
		<td colspan="2">
           <input type="submit" value="<spring:message code="button.cancel"/>" class = "myButton"/>
		</td>
		<td></td>
        <td></td>
	   </tr>
	  </table>
     </form>

</c:when>
<c:otherwise>

	 <h1><spring:message code="clients.edit"/> </h1>
	 <form:form method="POST" commandName="client" action="updateclient.htm">
<table>
    <tbody><tr>
        <td><form:hidden path="id"></form:hidden></td>
    </tr>
	<tr>
        <td><form:label path="firstName"><spring:message code="firstname"/>:</form:label></td>
        <td><form:input path="firstName" cssClass = "inputs"></form:input></td>
		<td><span class="error">${errors.first_name}</span><td>
    </tr>
    <tr>
        <td><form:label path="lastName"><spring:message code="lastname"/>:</form:label></td>
        <td><form:input path="lastName" cssClass = "inputs"></form:input></td>
		<td><span class="error">${errors.last_name}</span><td>
    </tr>
	<tr>
        <td><form:label path="pesel"><spring:message code="pesel"/>:</form:label></td>
        <td><form:input path="pesel" cssClass = "inputs"></form:input></td>
		<td><span class="error">${errors.pesel}</span><td>
    </tr>
	<tr>
        <td><form:label path="city"><spring:message code="city"/>:</form:label></td>
        <td><form:input path="city" cssClass = "inputs"></form:input></td>
		<td><span class="error">${errors.city}</span><td>
    </tr>
	<tr>
        <td><form:label path="street"><spring:message code="street"/>:</form:label></td>
        <td><form:input path="street" cssClass = "inputs"></form:input></td>
		<td><span class="error">${errors.first_name}</span><td>
    </tr>
	<tr>
        <td><form:label path="phoneNumber"><spring:message code="phonenumber"/>:</form:label></td>
        <td><form:input path="phoneNumber" cssClass = "inputs"></form:input></td>
		<td><span class="error">${errors.phone_number}</span><td>
    </tr>
	<tr>
        <td><form:label path="email"><spring:message code="email"/>:</form:label></td>
        <td><form:input path="email" cssClass = "inputs"></form:input></td>
		<td><span class="error">${errors.email}</span><td>
    </tr>
	<tr><td></td></tr>
    <tr>
        <td colspan="2">
            <input type="submit" value="<spring:message code="button.submit"/>" class = "myButton">
        </td>
        <td></td>
        <td></td>
    </tr>
</tbody></table>  
</form:form>
	 
     <form name="cancel" action="<c:url value="clients.htm"/>" method="get">
	 <table>
	   <tr>
		<td colspan="2">
           <input type="submit" value="<spring:message code="button.cancel"/>" class = "myButton"/>
		</td>
		<td></td>
        <td></td>
	   </tr>
	  </table>
     </form>

</c:otherwise>
</c:choose>
</div></div>
</body>
</html>