<%@ include file="/WEB-INF/jsp/include.jsp" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@page import="pl.radek.dvd.model.Client, pl.radek.dvd.model.Constants, pl.radek.dvd.utils.JspMethods, java.util.List"%>
<%@page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="cons" class="pl.radek.dvd.model.Constants"/>

<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="langs">
		<c:url var="englishLocaleUrl" value="/emp/clientdetails.htm">
		<c:param name="id" value="${param.id}" />
		<c:param name="lang" value="en" />
		</c:url>
		<c:url var="polishLocaleUrl" value="/emp/clientdetails.htm">
		<c:param name="id" value="${param.id}" />
		<c:param name="lang" value="pl" />
		</c:url>
		
		<a href="${englishLocaleUrl}">EN</a>
		<a href="${polishLocaleUrl}">PL</a>
	</tiles:putAttribute>
    <tiles:putAttribute name="content">
	<h2>
   		<spring:message code="clients.client.details"/>:
	</h2>
   		<div id = "myform">
		<div class="filtreheader">
			<p> <spring:message code="clients.client.history"/> </p>
		</div>
		<div class="separator"></div>
		<div class="inputs">
			<ul>
				<li><label> <spring:message code="clients.clientsList.firstName"/>: </label> <c:out value="${client.firstName}"/>  </li>
				<li><label> <spring:message code="clients.clientsList.lastName"/>: </label>  <c:out value="${client.lastName}"/>  </li>
				<li><label> <spring:message code="clients.clientsList.pesel"/>: </label>  <c:out value="${client.pesel}"/>  </li>
				<li><label> <spring:message code="clients.clientsList.city"/>: </label>  <c:out value="${client.city}"/>  </li>
				<li><label> <spring:message code="clients.clientsList.street"/>: </label>  <c:out value="${client.street}"/>  </li>
				<li><label> <spring:message code="clients.clientsList.phoneNumber"/>: </label>  <c:out value="${client.phoneNumber}"/>  </li>
				<li><label> <spring:message code="clients.clientsList.email"/>: </label>  <c:out value="${client.email}"/>  </li>
			</ul>
		</div> 
		</div>
		<div class="table">
		
		</div>
		</br>
		
		<form name="back" action="<c:url value="clients.htm"/>" method="get">
		<table>
		<tr>
			<td colspan="2">
				<input type="submit" value="<spring:message code="common.button.back"/>" class = "myButton"/>
			</td>
			<td></td>
			<td></td>
		</tr>
		</table>
		</form>
		
</tiles:putAttribute>
</tiles:insertDefinition>

