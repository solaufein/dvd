<%@ include file="/WEB-INF/jsp/include.jsp" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="hero" uri="/WEB-INF/tags/headero.tld"%>
<%@page import="pl.radek.dvd.model.Client, pl.radek.dvd.model.Constants, pl.radek.dvd.utils.JspMethods, java.util.List"%>
<%@page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="cons" class="pl.radek.dvd.model.Constants"/>

<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="langs">
		<c:url var="englishLocaleUrl" value="/emp/movies/movieslist.htm">
		<c:param name="lang" value="en" />
		</c:url>
		<c:url var="polishLocaleUrl" value="/emp/movies/movieslist.htm">
		<c:param name="lang" value="pl" />
		</c:url>
		<a href="${englishLocaleUrl}">EN</a>
		<a href="${polishLocaleUrl}">PL</a>
	</tiles:putAttribute>
    <tiles:putAttribute name="content">
	
	jajajajajja
	
	</tiles:putAttribute>
</tiles:insertDefinition>