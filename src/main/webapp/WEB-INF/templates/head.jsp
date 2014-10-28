<%@ include file="/WEB-INF/jsp/include.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-2" />
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
 <tiles:importAttribute name="cssList"/>
 <tiles:importAttribute name="jsList"/>
    <title>
        <spring:message code="common.header.title" />
    </title>

	<!--script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script-->
	<!--script src="http://code.jquery.com/jquery-1.10.2.js"></script-->
    <!--script src="http://code.jquery.com/ui/1.11.2/jquery-ui.js"></script-->

    <!-- stylesheets -->
    <c:forEach var="css" items="${cssList}">
        <link rel="stylesheet" type="text/css" href="<c:url value="${css}"/>">
    </c:forEach>
    <!-- end stylesheets -->

    <!-- scripts -->
    <c:forEach var="script" items="${jsList}">
        <script src="<c:url value="${script}"/>"></script>
    </c:forEach>
    <!-- end scripts -->

              <script>

              </script>
    		  <style>

    		  </style>