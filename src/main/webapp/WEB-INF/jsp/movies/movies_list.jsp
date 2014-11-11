<%@ include file="/WEB-INF/jsp/include.jsp" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="hero" uri="/WEB-INF/tags/movies/movies_table_sort.tld" %>
<%@ taglib prefix="pagi" uri="/WEB-INF/tags/movies/movies_pagination.tld" %>
<%@page import="pl.radek.dvd.model.Constants, pl.radek.dvd.utils.JspMethods, java.util.List" %>
<%@page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:useBean id="cons" class="pl.radek.dvd.model.Constants"/>

<tiles:insertDefinition name="defaultTemplate">
<tiles:putAttribute name="langs">
    <c:url var="englishLocaleUrl" value="/emp/movies/movieslist">
        <c:param name="lang" value="en"/>
    </c:url>
    <c:url var="polishLocaleUrl" value="/emp/movies/movieslist">
        <c:param name="lang" value="pl"/>
    </c:url>
    <a href="${englishLocaleUrl}">EN</a>
    <a href="${polishLocaleUrl}">PL</a>
</tiles:putAttribute>
<tiles:putAttribute name="content">

<h2>
    <spring:message code="movies.moviesList.list"/>:
</h2>

<div id="myform">
    <div class="filtreheader">
        <p><spring:message code="common.fillin.filtre"/></p>
    </div>
    <div class="separator"></div>
    <form:form method="GET" commandName="movie" action="movieslist">
    <div class="inputs">
        <table>
            <tbody>
            <tr>
                <td><form:label path="title"><spring:message code="movies.moviesList.title"/>:</form:label></td>
                <td><form:input path="title" cssClass="inputs"></form:input></td>
                <td><form:errors path="title" cssClass="error"></form:errors></td>
            </tr>
            <tr>
                <td><form:label path="genre"><spring:message code="movies.moviesList.genre"/>:</form:label></td>
                <td><form:select path="genre" cssClass="inputs">
                    <form:option value="NONE" label="--- Select ---"/>
                    <form:options items="${allGenres}"/>
                </form:select>
                </td>
                <td><form:errors path="genre" cssClass="error"></form:errors></td>
            </tr>
            <tr>
                <td><form:label path="promotion"><spring:message
                        code="movies.moviesList.promotion"/>:</form:label></td>
                <td><form:select path="promotion" cssClass="inputs">
                    <form:option value="NONE" label="--- Select ---"/>
                    <form:options items="${allPromotions}"/>
                </form:select>
                </td>
                <td><form:errors path="promotion" cssClass="error"></form:errors></td>
            </tr>
            <tr>
                <td><form:label path="actor"><spring:message code="movies.moviesList.actor"/>:</form:label></td>
                <td><form:input path="actor" cssClass="inputs"></form:input></td>
                <td><form:errors path="actor" cssClass="error"></form:errors></td>
            </tr>
            <tr>
                <td></td>
            </tr>
            <tr>
                <td>
                    <input type="submit" value="<spring:message code="common.button.filtre"/>">
                </td>
                </form:form>
                <td>
                    <form name="clearTable" action=" <c:url value="movieslist"/>" method="get">
                        <input type="submit" value="<spring:message code="common.button.clear"/>"/>
                    </form>
                </td>
                <td></td>
                <td></td>
            </tr>
            </tbody>
        </table>
    </div>
</div>

<div class="table">
    <table>
        <tr>
            <td>
                <hero:Linkuj order="${param.order}" field="${param.field}" columnName="${cons.title}"
                             title="${param.title}" genre="${param.genre}" promotion="${param.promotion}"
                             actorName="${param.actor}"/>
                <c:url value="movieslist${hlink}" var="paginationURL"/>
                <a href="${paginationURL}" class="link"><spring:message code="movies.moviesList.title"/></a>
            </td>
            <td>
                <hero:Linkuj order="${param.order}" field="${param.field}" columnName="${cons.director}"
                             title="${param.title}" genre="${param.genre}" promotion="${param.promotion}"
                             actorName="${param.actor}"/>
                <c:url value="movieslist${hlink}" var="paginationURL"/>
                <a href="${paginationURL}" class="link"><spring:message code="movies.moviesList.director"/></a>
            </td>
            <td>
                <hero:Linkuj order="${param.order}" field="${param.field}" columnName="${cons.productionyear}"
                             title="${param.title}" genre="${param.genre}" promotion="${param.promotion}"
                             actorName="${param.actor}"/>
                <c:url value="movieslist${hlink}" var="paginationURL"/>
                <a href="${paginationURL}" class="link"><spring:message
                        code="movies.moviesList.productionYear"/></a>
            </td>
            <td>
                <hero:Linkuj order="${param.order}" field="${param.field}" columnName="${cons.genre}"
                             title="${param.title}" genre="${param.genre}" promotion="${param.promotion}"
                             actorName="${param.actor}"/>
                <c:url value="movieslist${hlink}" var="paginationURL"/>
                <a href="${paginationURL}" class="link"><spring:message code="movies.moviesList.genre"/></a>
            </td>
            <td>
                <hero:Linkuj order="${param.order}" field="${param.field}" columnName="${cons.promotionname}"
                             title="${param.title}" genre="${param.genre}" promotion="${param.promotion}"
                             actorName="${param.actor}"/>
                <c:url value="movieslist${hlink}" var="paginationURL"/>
                <a href="${paginationURL}" class="link"><spring:message code="movies.moviesList.promotion"/></a>
            </td>
            <td></td>
            <td></td>
            <td></td>
        </tr>
        <c:forEach items="${moviesList}" var="movie">
            <tr>
                <td><c:out value="${movie.title}"/></td>
                <td><c:out value="${movie.director}"/></td>
                <td><c:out value="${movie.productionYear}"/></td>
                <td><c:out value="${movie.genre}"/></td>
                <td><c:out value="${movie.name}"/></td>
                <td>
                    <form name="moviedetails" action=" <c:url value="moviedetails"/>" method="get">
                        <input type="hidden" name="id" value="${movie.id}"/>
                        <input type="submit" value="<spring:message code="common.button.details"/>"
                               class="myButton"/>
                    </form>
                </td>
                <td>
                    <form name="editmovie" action=" <c:url value="controller"/>" method="post">
                        <input type="hidden" name="id" value="${movie.id}"/>
                        <input type="submit" value="<spring:message code="common.button.edit"/>"
                               class="myButton"/>
                    </form>
                </td>
                <td>
                    <form action=" <c:url value="delete"/>" method="post" onsubmit="return ConfirmDelete();">
                        <input type="hidden" name="id" value="${movie.id}"/>
                        <input type="submit" value="<spring:message code="common.button.delete"/>"
                               class="myButton"/>
                    </form>
                </td>
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
                    <pagi:Linkuj order="${param.order}" field="${param.field}" currentPage="1"
                                 title="${param.title}" genre="${param.genre}" promotion="${param.promotion}"
                                 actorName="${param.actor}"/>
                    <c:url value="movieslist${paginlink}" var="paginURL"/>
                    <a href="${paginURL}" class="myButtonTwo"> |<< </a>
                </c:if>
            </td>

            <td>
                    <%--Displaying Previous link except for the 1st page--%>
                <c:if test="${currentPage != 1}">
                    <pagi:Linkuj order="${param.order}" field="${param.field}" currentPage="${currentPage - 1}"
                                 title="${param.title}" genre="${param.genre}" promotion="${param.promotion}"
                                 actorName="${param.actor}"/>
                    <c:url value="movieslist${paginlink}" var="paginURL"/>
                    <a href="${paginURL}" class="myButtonTwo"> < </a>
                </c:if>
            </td>

                <%--Displaying Page numbers--%>
            <td><c:out value="${currentPage}"/>/<c:out value="${noOfPages}"/></td>

            <td>
                    <%--Displaying Next link--%>
                <c:if test="${currentPage < noOfPages}">
                    <pagi:Linkuj order="${param.order}" field="${param.field}" currentPage="${currentPage + 1}"
                                 title="${param.title}" genre="${param.genre}" promotion="${param.promotion}"
                                 actorName="${param.actor}"/>
                    <c:url value="movieslist${paginlink}" var="paginURL"/>
                    <a href="${paginURL}" class="myButtonTwo"> > </a>
                </c:if>
            </td>

            <td>
                    <%--Displaying Last link--%>
                <c:if test="${currentPage < noOfPages}">
                    <pagi:Linkuj order="${param.order}" field="${param.field}" currentPage="${noOfPages}"
                                 title="${param.title}" genre="${param.genre}" promotion="${param.promotion}"
                                 actorName="${param.actor}"/>
                    <c:url value="movieslist${paginlink}" var="paginURL"/>
                    <a href="${paginURL}" class="myButtonTwo"> >>| </a>
                </c:if>
            </td>
        </tr>
    </table>
</div>
</br>
<form name="newmovie" action="<c:url value="controller"/>" method="post">
    <input type="hidden" name="id" value="new"/>
    <input type="submit" value="<spring:message code="movies.button.newmovie"/>" class="myButton"/>
</form>
</br>

<script>

    $(document).ready(function () {
        InitAutocompleteInput.init();
    });

</script>

</tiles:putAttribute>
</tiles:insertDefinition>