<%@ include file="/WEB-INF/jsp/include.jsp" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@page import="pl.radek.dvd.model.Constants, pl.radek.dvd.utils.JspMethods, java.util.List" %>
<%@page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:useBean id="cons" class="pl.radek.dvd.model.Constants"/>

<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="langs">
        <c:url var="englishLocaleUrl" value="/emp/movies/moviedetails">
            <c:param name="id" value="${param.id}"/>
            <c:param name="lang" value="en"/>
        </c:url>
        <c:url var="polishLocaleUrl" value="/emp/movies/moviedetails">
            <c:param name="id" value="${param.id}"/>
            <c:param name="lang" value="pl"/>
        </c:url>

        <a href="${englishLocaleUrl}">EN</a>
        <a href="${polishLocaleUrl}">PL</a>
    </tiles:putAttribute>
    <tiles:putAttribute name="content">
        <h2>
            <spring:message code="movies.movie.details"/>:
        </h2>

        <div id="dialog-form" title="Select client">
            <p class="validateTips">11 digit pesel required</p>

            <form name="findClients" id="findClients" method="GET"
                  action="<c:url value="/emp/movies/moviedetails/getClient"/>">
                <label for="pesel"><spring:message code="clients.clientsList.pesel"/>: </label>
                <input type="text" id="pesel" name="pesel"/>
                <input type="submit" value="<spring:message code="common.button.submit"/>" class="myButton"/>
            </form>
            <p><spring:message code="clients.client.details"/>: </p>

            <div class="table">

            </div>
        </div>

        <div id="myform">
            <div class="filtreheader">
                <p><spring:message code="movies.movie.history"/></p>
            </div>
            <div class="separator"></div>
            <div class="inputs">
                <ul>
                    <li><label> <spring:message code="movies.moviesList.title"/>: </label> <c:out
                            value="${movie.title}"/></li>
                    <li><label> <spring:message code="movies.moviesList.director"/>: </label> <c:out
                            value="${movie.director}"/></li>
                    <li><label> <spring:message code="movies.moviesList.productionYear"/>: </label> <c:out
                            value="${movie.productionYear}"/></li>
                    <li><label> <spring:message code="movies.moviesList.genre"/>: </label> <c:out
                            value="${movie.genre}"/></li>
                    <li><label> <spring:message code="movies.moviesList.promotion"/>: </label> <c:out
                            value="${movie.promotion}"/></li>
                    <li><label> <spring:message code="movies.moviesList.price"/>: </label> <c:out
                            value="${movie.promotion.value}"/></li>
                </ul>
            </div>
        </div>
        <div class="registerMovieCopyButton">
            <form name="registerMovieCopy" action=" <c:url value="register"/>" method="post">
                <input type="hidden" name="id" value="new"/>
                <input type="hidden" name="movieid" value="${movie.id}"/>
                <input type="submit" value="<spring:message code="common.button.register"/>" class="myButton"/>
            </form>
        </div>
        <div class="table">
            <table>
                <tr>
                    <td>
                        <spring:message code="movies.movieCopy.serialNumber"/>
                    </td>
                    <td>
                        <spring:message code="movies.movieCopy.availability"/>
                    </td>
                    <td></td>
                    <sec:authorize ifAnyGranted="ROLE_ADMIN">
                        <td></td>
                    </sec:authorize>
                </tr>
                <c:forEach items="${movieDetails}" var="details">
                    <tr>
                        <td><c:out value="${details.serialNumber}"/></td>
                        <c:if test="${details.availability == '1'}">
                            <td><spring:message code="movies.movieCopy.available"/></td>
                            <td>
                                <form name="rentMovieCopy" class="rentMovieCopy"
                                      action=" <c:url context="/dvd/emp" value="/rent/movie" />"
                                      method="post">
                                    <input type="hidden" name="moviecopyid" value="${details.id}"/>
                                    <input type="hidden" name="movieid" value="${movie.id}"/>
                                    <input type="submit" value="<spring:message code="common.button.rent"/>"
                                           class="myButton"/>
                                </form>
                            </td>
                        </c:if>
                        <c:if test="${details.availability == '0'}">
                            <td><spring:message code="movies.movieCopy.unavailable"/></td>
                            <td>
                                <form name="returnMovieCopy" class="returnMovieCopy"
                                      action=" <c:url context="/dvd/emp" value="/return/movie" />"
                                      method="post">
                                    <input type="hidden" name="movieCopyId" value="${details.id}"/>
                                    <input type="submit" value="<spring:message code="common.button.return"/>"
                                           class="myButton"/>
                                </form>
                            </td>
                        </c:if>
                        <sec:authorize ifAnyGranted="ROLE_ADMIN">
                            <td>
                                <form action=" <c:url value="deletecopy"/>" method="post"
                                      onsubmit="return ConfirmDelete();">
                                    <input type="hidden" name="id" value="${details.id}"/>
                                    <input type="hidden" name="movieid" value="${movie.id}"/>
                                    <input type="submit" value="<spring:message code="common.button.delete"/>"
                                           class="myButton"/>
                                </form>
                            </td>
                        </sec:authorize>
                    </tr>
                </c:forEach>
            </table>
        </div>
        </br>
        <div id="page_numbers">
            <table>
                <tr>
                    <td>
                            <%--Displaying First link except for the 1st page--%>
                        <c:if test="${currentPage != 1}">
                            <form name="firstlink" action="<c:url value="moviedetails"/>" method="get">
                                <input type="hidden" name="currentPage" value="1"/>
                                <input type="submit" value="|<<" class="myButtonTwo"/>
                            </form>
                        </c:if>
                    </td>

                    <td>
                            <%--Displaying Previous link except for the 1st page--%>
                        <c:if test="${currentPage != 1}">
                            <form name="previouslink" action="<c:url value="moviedetails"/>" method="get">
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
                            <form name="nextlink" action="<c:url value="moviedetails"/>" method="get">
                                <input type="hidden" name="currentPage" value="${currentPage + 1}"/>
                                <input type="submit" value=">" class="myButtonTwo"/>
                            </form>
                        </c:if>
                    </td>

                    <td>
                            <%--Displaying Last link--%>
                        <c:if test="${currentPage < noOfPages}">
                            <form name="lastlink" action="<c:url value="moviedetails"/>" method="get">
                                <input type="hidden" name="currentPage" value="${noOfPages}"/>
                                <input type="submit" value=">>|" class="myButtonTwo"/>
                            </form>
                        </c:if>
                    </td>
                </tr>
            </table>
        </div>
        </br>

        <form name="back" action="<c:url value="movieslist"/>" method="get">
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

            $(document).ready(function () {
                InitRentMovie.init();
            });

        </script>

    </tiles:putAttribute>
</tiles:insertDefinition>

