<%@ include file="/WEB-INF/jsp/include.jsp" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@page import="pl.radek.dvd.model.Constants, pl.radek.dvd.utils.JspMethods, java.util.List"%>
<%@page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="cons" class="pl.radek.dvd.model.Constants"/>

<tiles:insertDefinition name="administrationTemplate">
	<tiles:putAttribute name="langs">
		<c:url var="englishLocaleUrl" value="/emp/administration/administration.htm">
		<c:param name="lang" value="en" />
		</c:url>
		<c:url var="polishLocaleUrl" value="/emp/administration/administration.htm">
		<c:param name="lang" value="pl" />
		</c:url>
		<a href="${englishLocaleUrl}">EN</a>
		<a href="${polishLocaleUrl}">PL</a>
	</tiles:putAttribute>
    <tiles:putAttribute name="content">
	
		<c:url value="/resources/images/loading.gif" var="loadgif" />

		<div id="ajax-content">
			This is default text, which will be replaced
		</div>
		
		<form id="formularz" action="#" method="get" onsubmit="popup()">
            <input type="submit" value="popupik" class = "myButton"/>
        </form>
		
		<script>
			$(document).ready(function(){
				$("#formularz").click(function() {
					alert( "Handler for .click() called." );
				});
			});
			
			$(document).ready(function(){

			$("#navigation_left ul li a").click(function() {

					var loading = $('<div id="loading"/>');
					loading.prepend('<img src="${loadgif}" />');
					
					$("#ajax-content").empty().append(loading);
					$("#navigation_left ul li a").removeClass("current");
					$(this).addClass("current");

					$.ajax
					({ url: this.href,
					   success: function(html) {
						$("#ajax-content").empty().append(html);
						}
					});
						return false;
				});
			});
		</script>
		
	</tiles:putAttribute>
</tiles:insertDefinition>