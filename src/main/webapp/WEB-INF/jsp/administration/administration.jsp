<%@ include file="/WEB-INF/jsp/include.jsp" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@page import="pl.radek.dvd.model.Constants, pl.radek.dvd.utils.JspMethods, java.util.List"%>
<%@page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="cons" class="pl.radek.dvd.model.Constants"/>

<tiles:insertDefinition name="administrationTemplate">
	<tiles:putAttribute name="langs">
		<c:url var="englishLocaleUrl" value="/emp/administration/administration">
		<c:param name="lang" value="en" />
		</c:url>
		<c:url var="polishLocaleUrl" value="/emp/administration/administration">
		<c:param name="lang" value="pl" />
		</c:url>
		<a href="${englishLocaleUrl}">EN</a>
		<a href="${polishLocaleUrl}">PL</a>
	</tiles:putAttribute>
    <tiles:putAttribute name="content">
	
		<c:url value="/resources/images/loading.gif" var="loadgif" />

		<div id="ajax-content">
			Choose tab that you are interested in...
		</div>
		
		
		<script>
			
			$(document).ready(function(){
				tabsAction("${loadgif}");
			});
			
		</script>
		
	</tiles:putAttribute>
</tiles:insertDefinition>