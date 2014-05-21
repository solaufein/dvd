<%@ include file="/WEB-INF/jsp/include.jsp" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@page import="pl.radek.dvd.model.Client, pl.radek.dvd.model.Constants, pl.radek.dvd.utils.JspMethods, java.util.List"%>
<%@page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="cons" class="pl.radek.dvd.model.Constants"/>

<tiles:insertDefinition name="defaultTemplate">

    <tiles:putAttribute name="content">
	<h2>
   			Lista:
	</h2>

   		bla bla bla
   		bla bla bla
   		bla bla bla
   		bla bla bla
   		bla bla bla
   		bla bla bla
   		bla bla bla
   		bla bla bla
   		bla bla bla
   		bla bla bla
		
<h1>Using JSTL</h1>
<h3>1 cons.CLIENT</h3>
<c:out value="${cons.client}"/>



</tiles:putAttribute>
</tiles:insertDefinition>

