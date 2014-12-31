<%@ include file="/WEB-INF/jsp/include.jsp" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="hero" uri="/WEB-INF/tags/movies/movies_table_sort.tld" %>
<%@ taglib prefix="pagi" uri="/WEB-INF/tags/movies/movies_pagination.tld" %>
<%@page import="pl.radek.dvd.model.Constants, pl.radek.dvd.utils.JspMethods, java.util.List" %>
<%@page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:useBean id="cons" class="pl.radek.dvd.model.Constants"/>

<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="langs">
        <c:url var="englishLocaleUrl" value="/emp/raports/notreturned">
            <c:param name="lang" value="en"/>
        </c:url>
        <c:url var="polishLocaleUrl" value="/emp/raports/notreturned">
            <c:param name="lang" value="pl"/>
        </c:url>
        <a href="${englishLocaleUrl}">EN</a>
        <a href="${polishLocaleUrl}">PL</a>
    </tiles:putAttribute>
    <tiles:putAttribute name="content">

        <div id="myform">
            <div class="filtreheader">
                <p><spring:message code="raports.raportslist.returned"/></p>
            </div>
            <div class="separator"></div>
            <div class="inputs">
            </div>
        </div>

        <div class="table">
            <table>
                <tr>
                    <td>
                        <spring:message code="clients.clientsList.firstName"/>
                    </td>
                    <td>
                        <spring:message code="clients.clientsList.lastName"/>
                    </td>
                    <td>
                        <spring:message code="movies.moviesList.title"/>
                    </td>
                    <td>
                        <spring:message code="clients.clientHistoryList.rentDate"/>
                    </td>
                    <td>
                        <spring:message code="clients.clientHistoryList.returnDate"/>
                    </td>
                    <td>
                        <spring:message code="raports.raportslist.dayslate"/>
                    </td>
                    <td>
                        <spring:message code="clients.clientsList.phoneNumber"/>
                    </td>
                    <td>
                        <spring:message code="clients.client.details"/>
                    </td>
                </tr>
                <c:forEach items="${dataList}" var="rap">
                    <tr>
                        <td><c:out value="${rap.firstName}"/></td>
                        <td><c:out value="${rap.lastName}"/></td>
                        <td><c:out value="${rap.title}"/></td>
                        <td><c:out value="${rap.rentDate}"/></td>
                        <td><c:out value="${rap.returnDate}"/></td>
                        <td><c:out value="${rap.daysLate}"/></td>
                        <td><c:out value="${rap.phoneNumber}"/></td>
                        <td><form name="clientdetails" action=" <c:url value="/emp/clients/clientdetails"/>" method="get">
                            <input type="hidden" name="id" value="${rap.id}" />
                            <input type="submit" value="<spring:message code="common.button.details"/>" class = "myButton"/>
                        </form></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <br>

        <div id="page_numbers">
            <table>
                <tr>
                    <td>
                            <%--Displaying First link except for the 1st page--%>
                        <c:if test="${currentPage != 1}">
                            <form name="firstlink" action="<c:url value="/emp/raports/notreturned"/>" method="get">
                                <input type="hidden" name="currentPage" value="1"/>
                                <input type="submit" value="|<<" class="myButtonTwo"/>
                            </form>
                        </c:if>
                    </td>

                    <td>
                            <%--Displaying Previous link except for the 1st page--%>
                        <c:if test="${currentPage != 1}">
                            <form name="previouslink" action="<c:url value="/emp/raports/notreturned"/>" method="get">
                                <input type="hidden" name="currentPage" value="${currentPage - 1}"/>
                                <input type="submit" value="<" class="myButtonTwo"/>
                            </form>
                        </c:if>
                    </td>

                        <%--Displaying Page numbers--%>
                    <td><c:out value="${currentPage}"/>/<c:out value="${noOfPages}"/></td>

                    <td>
                            <%--Displaying Next link--%>
                        <c:if test="${currentPage < noOfPages}">
                            <form name="nextlink" action="<c:url value="/emp/raports/notreturned"/>" method="get">
                                <input type="hidden" name="currentPage" value="${currentPage + 1}"/>
                                <input type="submit" value=">" class="myButtonTwo"/>
                            </form>
                        </c:if>
                    </td>

                    <td>
                            <%--Displaying Last link--%>
                        <c:if test="${currentPage < noOfPages}">
                            <form name="lastlink" action="<c:url value="/emp/raports/notreturned"/>" method="get">
                                <input type="hidden" name="currentPage" value="${noOfPages}"/>
                                <input type="submit" value=">>|" class="myButtonTwo"/>
                            </form>
                        </c:if>
                    </td>
                </tr>
            </table>
        </div>
        </br>

        <form name="back" action="<c:url value="/emp/raports/"/>" method="get">
            <table>
                <tr>
                    <td colspan="2">
                        <input type="submit" value="<spring:message code="common.button.back"/>" class="myButton"/>
                    </td>
                    <td></td>
                    <td></td>
                </tr>
            </table>
        </form>

        <script>

            /*$(document).ready(function () {
             });*/

        </script>

    </tiles:putAttribute>
</tiles:insertDefinition>