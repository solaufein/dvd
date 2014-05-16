<%@ include file="/WEB-INF/jsp/include.jsp" %>
<%@page import="pl.radek.dvd.model.Client, pl.radek.dvd.model.Constants, pl.radek.dvd.utils.JspMethods, java.util.List"%>
<%@page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="pl" lang="pl">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-2" />
<title><spring:message code="common.header.title" /></title>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/main.css" />" />
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/button.css" />" />
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/table.css" />" />

          <script>
              function ConfirmDelete()
              {
                var x = confirm("<spring:message code="common.question.deletion"/>");
                if (x)
                    return true;
                else
                  return false;
              }
          </script>
		  <style>

	      </style>
</head>
<body>
   <div id="container">
   	<div id="header">
   		<h1>
   			<spring:message code="common.header" />
   		</h1>
   	</div>
   	<div id="login_info">
    		<div class="location">Location: Clients > List</div>
    		<a href="<c:url value="/j_spring_security_logout"/>" class="logout">Logout ${pageContext.request.userPrincipal.name}</a>
    	</div>
   	<div id="navigation">
   		<ul>
   			<li><a href="#"><spring:message code="menu.home" /></a></li>
   			<li><a href="#"><spring:message code="menu.clients" /></a></li>
   			<li><a href="#"><spring:message code="menu.movies" /></a></li>
   			<li><a href="#"><spring:message code="menu.raports" /></a></li>
   			<li><a href="#"><spring:message code="menu.administration" /></a></li>
   		</ul>
   	</div>
	<!-- Header right begin-->
	<div id="langs">
		<c:url var="englishLocaleUrl" value="/emp/clients.htm">
			<c:param name="lang" value="en" />
		</c:url>
		<c:url var="polishLocaleUrl" value="/emp/clients.htm">
			<c:param name="lang" value="pl" />
		</c:url>
		<a href="${englishLocaleUrl}">EN</a>
		<a href="${polishLocaleUrl}">PL</a>
	</div>
	<!-- Header right end-->
   	<div id="content">
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

   	</div>
   	<div id="footer">
   		<spring:message code="common.footer"/>
   	</div>
   </div>
</body>
</html>
