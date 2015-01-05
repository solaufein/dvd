<%@ include file="/WEB-INF/jsp/include.jsp" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="hero" uri="/WEB-INF/tags/movies/movies_table_sort.tld" %>
<%@ taglib prefix="pagi" uri="/WEB-INF/tags/movies/movies_pagination.tld" %>
<%@page import="pl.radek.dvd.model.Constants, pl.radek.dvd.utils.JspMethods, java.util.List" %>
<%@page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:useBean id="cons" class="pl.radek.dvd.model.Constants"/>

<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="langs">
        <c:url var="englishLocaleUrl" value="/emp/raports/">
            <c:param name="lang" value="en"/>
        </c:url>
        <c:url var="polishLocaleUrl" value="/emp/raports/">
            <c:param name="lang" value="pl"/>
        </c:url>
        <a href="${englishLocaleUrl}">EN</a>
        <a href="${polishLocaleUrl}">PL</a>
    </tiles:putAttribute>
    <tiles:putAttribute name="content">

        <div id="myform">
            <div class="filtreheader">
                <p><spring:message code="menu.raports"/></p>
            </div>
            <div class="separator"></div>
            <c:if test="${not empty error}">
                <div class="error">${error}</div>
            </c:if>
            <div class="inputs">
                <a href="<c:url value="/emp/raports/income"/>" class="myButton"><spring:message
                        code="raports.raportslist.income"/></a>
                <a href="<c:url value="/emp/raports/tophits"/>" class="myButton"><spring:message
                        code="raports.raportslist.tophits"/></a>
                <a href="<c:url value="/emp/raports/promotions"/>" class="myButton"><spring:message
                        code="raports.raportslist.promotions"/></a>
                <a href="<c:url value="/emp/raports/notreturned"/>" class="myButton"><spring:message
                        code="raports.raportslist.returned"/></a>
            </div>
        </div>

        <script>

            /*$(document).ready(function () {
             });*/

        </script>

    </tiles:putAttribute>
</tiles:insertDefinition>