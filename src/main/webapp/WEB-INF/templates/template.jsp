<!DOCTYPE HTML>
<%@ include file="/WEB-INF/jsp/include.jsp" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html>
	<head>
          <tiles:insertAttribute name="head" />
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
    	<div class="clear"></div>
        <tiles:insertAttribute name="navigation_left" />

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
          navigation("${menuItem}");
        });
		
	function ConfirmDelete()
	  {
		var x = confirm("<spring:message code="common.question.deletion"/>");
		if (x)
			return true;
		else
		  return false;
	  }


    </script>
</html>