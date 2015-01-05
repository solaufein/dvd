<%@ include file="/WEB-INF/jsp/include.jsp" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="hero" uri="/WEB-INF/tags/movies/movies_table_sort.tld" %>
<%@ taglib prefix="pagi" uri="/WEB-INF/tags/movies/movies_pagination.tld" %>
<%@page import="pl.radek.dvd.model.Constants, pl.radek.dvd.utils.JspMethods, java.util.List" %>
<%@page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:useBean id="cons" class="pl.radek.dvd.model.Constants"/>

<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="langs">
        <c:url var="englishLocaleUrl" value="/emp/home/">
            <c:param name="lang" value="en"/>
        </c:url>
        <c:url var="polishLocaleUrl" value="/emp/home/">
            <c:param name="lang" value="pl"/>
        </c:url>
        <a href="${englishLocaleUrl}">EN</a>
        <a href="${polishLocaleUrl}">PL</a>
    </tiles:putAttribute>
    <tiles:putAttribute name="content">

        <div id="myform">
            <div class="filtreheader">
                <p><spring:message code="menu.home"/></p>
            </div>
            <div class="separator2"></div>
            <c:if test="${not empty error}">
                <div class="error">${error}</div>
            </c:if>
            <div class="inputs">

                <!-- RENT -->
                <form name="rentClientLastname" id="rentClientLastname" method="GET"
                      action="<c:url value="/emp/home/rent/clientName"/>">
                    <label for="lastName1" class="labelWidth"><spring:message
                            code="home.rent.client.lastName"/>: </label>
                    <input type="text" id="lastName1" name="lastName"/>
                    <input type="submit" value="<spring:message code="common.button.submit"/>" class="myButton"/>
                </form>

                <form name="rentClientPesel" id="rentClientPesel" method="GET"
                      action="<c:url value="/emp/home/rent/clientPesel"/>">
                    <label for="pesel1" class="labelWidth"><spring:message code="home.rent.client.pesel"/>: </label>
                    <input type="text" id="pesel1" name="pesel"/>
                    <input type="submit" value="<spring:message code="common.button.submit"/>" class="myButton"/>
                </form>

                <form name="rentMovieTitle" id="rentMovieTitle" method="GET"
                      action="<c:url value="/emp/home/rent/movieTitle"/>">
                    <label for="title1" class="labelWidth"><spring:message code="home.rent.movie.title"/>: </label>
                    <input type="text" id="title1" name="title"/>
                    <input type="submit" value="<spring:message code="common.button.submit"/>" class="myButton"/>
                </form>

                <form name="rentMovieSerialNumber" id="rentMovieSerialNumber" method="GET"
                      action="<c:url value="/emp/home/rent/movieSerialNumber"/>">
                    <label for="serialNumber1" class="labelWidth"><spring:message
                            code="home.rent.movie.serialNumber"/>: </label>
                    <input type="text" id="serialNumber1" name="serialNumber"/>
                    <input type="submit" value="<spring:message code="common.button.submit"/>" class="myButton"/>
                </form>
                <div class="separator2"></div>

                <!-- RETURN -->
                <form name="returnMovieTitle" id="returnMovieTitle" method="GET"
                      action="<c:url value="/emp/home/return/movieTitle"/>">
                    <label for="title2" class="labelWidth"><spring:message code="home.return.movie.title"/>: </label>
                    <input type="text" id="title2" name="title"/>
                    <input type="submit" value="<spring:message code="common.button.submit"/>" class="myButton"/>
                </form>

                <form name="returnSerialNumber" id="returnSerialNumber" method="GET"
                      action="<c:url value="/emp/home/return/movieSerialNumber"/>">
                    <label for="serialNumber2" class="labelWidth"><spring:message
                            code="home.return.movie.serialNumber"/>: </label>
                    <input type="text" id="serialNumber2" name="serialNumber"/>
                    <input type="submit" value="<spring:message code="common.button.submit"/>" class="myButton"/>
                </form>
                <div class="separator2"></div>

                <!-- FIND -->
                <form name="findClientLastname" id="findClientLastname" method="GET"
                      action="<c:url value="/emp/home/find/clientName"/>">
                    <label for="lastName3" class="labelWidth"><spring:message
                            code="home.find.client.lastName"/>: </label>
                    <input type="text" id="lastName3" name="lastName"/>
                    <input type="submit" value="<spring:message code="common.button.submit"/>" class="myButton"/>
                </form>

                <form name="findClientPesel" id="findClientPesel" method="GET"
                      action="<c:url value="/emp/home/find/clientPesel"/>">
                    <label for="pesel3" class="labelWidth"><spring:message code="home.find.client.pesel"/>: </label>
                    <input type="text" id="pesel3" name="pesel"/>
                    <input type="submit" value="<spring:message code="common.button.submit"/>" class="myButton"/>
                </form>

                <form name="findMovieTitle" id="findMovieTitle" method="GET"
                      action="<c:url value="/emp/home/find/movieTitle"/>">
                    <label for="title3" class="labelWidth"><spring:message code="home.find.movie.title"/>: </label>
                    <input type="text" id="title3" name="title"/>
                    <input type="submit" value="<spring:message code="common.button.submit"/>" class="myButton"/>
                </form>

                <form name="findMovieSerialNumber" id="findMovieSerialNumber" method="GET"
                      action="<c:url value="/emp/home/find/movieSerialNumber"/>">
                    <label for="serialNumber3" class="labelWidth"><spring:message
                            code="home.find.movie.serialNumber"/>: </label>
                    <input type="text" id="serialNumber3" name="serialNumber"/>
                    <input type="submit" value="<spring:message code="common.button.submit"/>" class="myButton"/>
                </form>
            </div>
        </div>


        <script>

            $(document).ready(function () {
                InitHomeFields.init();
            });

        </script>

    </tiles:putAttribute>
</tiles:insertDefinition>