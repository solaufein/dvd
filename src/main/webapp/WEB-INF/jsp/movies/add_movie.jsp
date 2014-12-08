<%@ include file="/WEB-INF/jsp/include.jsp" %>
<%@page import="pl.radek.dvd.model.Constants" %>
<%@page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="pl" lang="pl">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-2"/>
    <title><spring:message code="common.header.title"/></title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/jquery-ui.css" />"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/addedit.css" />"/>
    <script src="<c:url value="/resources/js/jquery-1.11.1.min.js" />" type="text/javascript"
            xml:space="preserve"></script>
    <script src="<c:url value="/resources/js/jquery-ui.min.js" />" type="text/javascript" xml:space="preserve"></script>
    <script src="<c:url value="/resources/js/actorsAddEdit.js" />" type="text/javascript" xml:space="preserve"></script>
</head>
<body>
<div class="container">
    <div id="myform">

        <c:choose>
            <c:when test="${movie.id =='-1'}">
                <div class="header">
                    <h3><spring:message code="movies.add.header"/></h3>

                    <p><spring:message code="common.fillin"/></p>
                </div>
                <div class="separator"></div>
                <div class="inputs">
                    <form:form method="POST" commandName="movie" action="addmovie">
                        <table>
                            <tbody>
                            <tr>
                                <td><form:hidden path="id"></form:hidden></td>
                            </tr>
                            <tr>
                                <td><form:label path="title"><spring:message
                                        code="movies.moviesList.title"/>:</form:label></td>
                                <td><form:input path="title" cssClass="inputs"></form:input></td>
                                <td><form:errors path="title" cssClass="error"></form:errors></td>
                            </tr>
                            <tr>
                                <td><form:label path="director"><spring:message
                                        code="movies.moviesList.director"/>:</form:label></td>
                                <td><form:input path="director" cssClass="inputs"></form:input></td>
                                <td><form:errors path="director" cssClass="error"></form:errors></td>
                            </tr>
                            <tr>
                                <td><form:label path="productionYear"><spring:message
                                        code="movies.moviesList.productionYear"/>:</form:label></td>
                                <td><form:input path="productionYear" cssClass="inputs"></form:input></td>
                                <td><form:errors path="productionYear" cssClass="error"></form:errors></td>
                            </tr>
                            <tr>
                                <td><form:label path="genre"><spring:message
                                        code="movies.moviesList.genre"/>:</form:label></td>
                                <td><form:select path="genre">
                                    <form:options items="${allGenres}" itemValue="id" itemLabel="name"/>
                                </form:select>
                                </td>
                                <td><form:errors path="genre" cssClass="error"></form:errors></td>
                            </tr>
                            <tr>
                                <td><form:label path="promotion"><spring:message
                                        code="movies.moviesList.promotion"/>:</form:label></td>
                                <td><form:select path="promotion">
                                    <form:options items="${allPromotions}" itemValue="id" itemLabel="name"/>
                                </form:select>
                                </td>
                                <td><form:errors path="promotion" cssClass="error"></form:errors></td>
                            </tr>
                            <tr>
                                <td>
                                    <form:hidden path="actorset"></form:hidden>
                                </td>
                            </tr>
                            <tr>
                                <td></td>
                            </tr>
                            <tr>
                                <td colspan="2">
                                    <input type="submit" value="<spring:message code="common.button.submit"/>">
                                </td>
                                <td></td>
                                <td></td>
                            </tr>
                            </tbody>
                        </table>
                    </form:form>

                    <form name="cancel" action="<c:url value="movieslist"/>" method="get">
                        <table>
                            <tr>
                                <td colspan="2">
                                    <input type="submit" value="<spring:message code="common.button.cancel"/>"/>
                                </td>
                                <td></td>
                                <td></td>
                            </tr>
                        </table>
                    </form>
                </div>
                <div class="findActorArea inputs">
                    <div class="searchActor">
                        <label for="ac">Actor:</label>
                        <input type="text" name="ac" value=""/>
                    </div>
                </div>
            </c:when>
            <c:otherwise>
                <div class="header">
                    <h3><spring:message code="movies.edit.header"/></h3>

                    <p><spring:message code="common.fillin"/></p>
                </div>
                <div class="separator"></div>
                <div class="inputs">
                    <form:form method="POST" commandName="movie" action="editmovie">
                        <table>
                            <tbody>
                            <tr>
                                <td><form:hidden path="id"></form:hidden></td>
                            </tr>
                            <tr>
                                <td><form:label path="title"><spring:message
                                        code="movies.moviesList.title"/>:</form:label></td>
                                <td><form:input path="title" cssClass="inputs"></form:input></td>
                                <td><form:errors path="title" cssClass="error"></form:errors></td>
                            </tr>
                            <tr>
                                <td><form:label path="director"><spring:message
                                        code="movies.moviesList.director"/>:</form:label></td>
                                <td><form:input path="director" cssClass="inputs"></form:input></td>
                                <td><form:errors path="director" cssClass="error"></form:errors></td>
                            </tr>
                            <tr>
                                <td><form:label path="productionYear"><spring:message
                                        code="movies.moviesList.productionYear"/>:</form:label></td>
                                <td><form:input path="productionYear" cssClass="inputs"></form:input></td>
                                <td><form:errors path="productionYear" cssClass="error"></form:errors></td>
                            </tr>
                            <tr>
                                <td><form:label path="genre"><spring:message
                                        code="movies.moviesList.genre"/>:</form:label></td>
                                <td><form:select path="genre">
                                    <form:options items="${allGenres}" itemValue="id" itemLabel="name"/>
                                </form:select>
                                </td>
                                <td><form:errors path="genre" cssClass="error"></form:errors></td>
                            </tr>
                            <tr>
                                <td><form:label path="promotion"><spring:message
                                        code="movies.moviesList.promotion"/>:</form:label></td>
                                <td><form:select path="promotion">
                                    <form:options items="${allPromotions}" itemValue="id" itemLabel="name"/>
                                </form:select>
                                </td>
                                <td><form:errors path="promotion" cssClass="error"></form:errors></td>
                            </tr>
                            <tr>
                                <td>
                                    <form:hidden path="actorset"></form:hidden>
                                </td>
                            </tr>
                            <tr>
                                <td></td>
                            </tr>
                            <tr>
                                <td colspan="2">
                                    <input type="submit" value="<spring:message code="common.button.submit"/>">
                                </td>
                                <td></td>
                                <td></td>
                            </tr>
                            </tbody>
                        </table>
                    </form:form>

                    <form name="cancel" action="<c:url value="movieslist"/>" method="get">
                        <table>
                            <tr>
                                <td colspan="2">
                                    <input type="submit" value="<spring:message code="common.button.cancel"/>"/>
                                </td>
                                <td></td>
                                <td></td>
                            </tr>
                        </table>
                    </form>
                </div>
                <div class="findActorArea inputs">
                    <div class="searchActor">
                        <label for="ac">Actor:</label>
                        <input type="text" name="ac" value=""/>
                    </div>
                </div>
            </c:otherwise>
        </c:choose>
    </div>
</div>

<script>

    $(document).ready(function () {

        InitAutocompleteInput.init();
        $( "#genre" ).selectmenu({
            width: 180
        });
        $( "#promotion" ).selectmenu({
            width: 180
        });

    });

</script>
</body>
</html>