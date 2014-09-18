<%@ include file="/WEB-INF/jsp/include.jsp" %>
<%@page import="pl.radek.dvd.model.Constants, java.util.List"%>
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

   <div class="header">
  <h3> <spring:message code="movies.addMovieCopy.header"/> </h3>
  <p> <spring:message code="common.fillin"/> </p>
   </div>
	<div class="separator"></div>
	 <form:form method="POST" commandName="movieCopy" action="addmoviecopy">
	 <div class="inputs">
<table>
    <tbody>
	<tr>
        <td><form:hidden path="id"></form:hidden></td>
    </tr>
	<tr>
        <td><form:label path="serialNumber"><spring:message code="movies.movieCopy.serialNumber"/>:</form:label></td>
        <td><form:input path="serialNumber" cssClass = "inputs"></form:input></td>
		<td><form:errors path="serialNumber" cssClass="error"></form:errors>
			<c:if test="${not empty duplicate}">
				<span class="error"><c:out value="${duplicate}"/></span>
			</c:if>
		</td>
    </tr>
	<tr>
		<td></td>
	</tr>
    <tr>
        <td colspan="2">
			<input type="hidden" name="movieid" value="${movieid}" />
            <input type="submit" value="<spring:message code="common.button.submit"/>" >
        </td>
        <td></td>
        <td></td>
    </tr>
	</tbody>
</table>

</form:form>

     <form name="cancel" action="<c:url value="moviedetails"/>" method="get">
	 <table>
	   <tr>
		<td colspan="2">
			<input type="hidden" name="id" value="${movieid}" />
           <input type="submit" value="<spring:message code="common.button.cancel"/>" />
		</td>
		<td></td>
        <td></td>
	   </tr>
	  </table>
     </form>
</div>
</div></div>
</body>
</html>