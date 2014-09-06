<!DOCTYPE HTML>
<%@ include file="/WEB-INF/jsp/include.jsp" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-2" />
    <title><spring:message code="common.header.title" /></title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/main.css" />" />
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/button.css" />" />
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/table.css" />" />
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/filtreform.css" />" />
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<!--script type="text/javascript" src="<c:url value="/resources/js/hello.js" />" ></script-->


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
    		  <style></style>
	</head>
	<body>
       <div id="container">
       	<div id="header">
       		<tiles:insertAttribute name="header" />
       	</div>
       	<div id="login_info">
        		<div class="location">
				<tiles:insertAttribute name="location_bar" />
				</div>
        		<tiles:insertAttribute name="login_info" />
        </div>
       	<div id="navigation">
       		<tiles:insertAttribute name="navigation" />
       	</div>
    	<!-- Header right begin-->
    	<div id="langs">
    		<tiles:insertAttribute name="langs" />
    	</div>
    	<!-- Header right end-->
       	<div id="content">
            <tiles:insertAttribute name="content" />
       	</div>
       	<div id="footer">
       		<tiles:insertAttribute name="footer" />
       	</div>
       </div>
    </body>
    <script>
    	$(document).ready(function(){
    		$("#navigation ul li.${menuItem} a").addClass("active");
    	});
    </script>
</html>