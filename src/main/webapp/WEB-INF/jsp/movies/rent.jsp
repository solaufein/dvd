<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/include.jsp" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<jsp:useBean id="cons" class="pl.radek.dvd.model.Constants"/>
<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="langs">
        <form id="polishLocale" name="polishLocale" action="<c:url value="/emp/rent/movie"/>" method="POST">
            <input type="hidden" name="lang" value="pl"/>
            <input type="hidden" name="clientId" value="${param.clientId}"/>
            <input type="hidden" name="movieCopyId" value="${param.movieCopyId}"/>
        </form>

        <form id="englishLocale" name="englishLocale" action="<c:url value="/emp/rent/movie"/>" method="POST">
            <input type="hidden" name="lang" value="en"/>
            <input type="hidden" name="clientId" value="${param.clientId}"/>
            <input type="hidden" name="movieCopyId" value="${param.movieCopyId}"/>
        </form>

        <a href="#" onclick="document.getElementById('englishLocale').submit(); return false;">EN</a>
        <a href="#" onclick="document.getElementById('polishLocale').submit(); return false;">PL</a>
    </tiles:putAttribute>
    <tiles:putAttribute name="content">

        <c:if test="${not empty msg}">
            <div class="msg">${msg}</div>
        </c:if>

        <div id="clientDetails">
            <div class="filtreheader">
                <p><spring:message code="common.button.rentMovie"/></p>
            </div>
            <div class="separator"></div>
            <div class="inputs">
                <ul>
                    <li><label> <spring:message code="clients.clientsList.firstName"/>: </label> <c:out
                            value="${client.firstName}"/></li>
                    <li><label> <spring:message code="clients.clientsList.lastName"/>: </label> <c:out
                            value="${client.lastName}"/></li>
                    <li><label> <spring:message code="movies.moviesList.title"/>: </label> <c:out
                            value="${rentData.title}"/>
                    </li>
                    <li><label> <spring:message code="movies.movieCopy.serialNumber"/>: </label> <c:out
                            value="${rentData.serialNumber}"/>
                    </li>
                    <li><label> <spring:message code="movies.moviesList.promotion"/>: </label> <c:out
                            value="${rentData.promotion}"/>
                    </li>
                </ul>
                <form id="ok" name="ok" action="<c:url value="/emp/rent/save"/>" method="POST" target="_blank">
                    <table>
                        <tr>
                            <td>
                                <label for="datepicker"> <spring:message
                                        code="clients.clientHistoryList.expectedReturnDate"/>: </label>
                                <input type="text" name="date" id="datepicker">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <input type="hidden" name="clientId" value="${param.clientId}"/>
                                <input type="hidden" name="movieCopyId" value="${param.movieCopyId}"/>
                                <input type="submit" value="<spring:message code="common.button.ok"/>"
                                       class="myButton"/>
                            </td>
                        </tr>
                    </table>
                </form>

                <form name="cancel" action="<c:url value="/clientdetails/moviesrent" context="/dvd/emp/clients"/>"
                      method="get">
                    <table>
                        <tr>
                            <td colspan="2">
                                <input type="hidden" name="id" value="${param.clientId}"/>
                                <input type="submit" value="<spring:message code="common.button.back"/>"
                                       class="myButton"/>
                            </td>
                            <td></td>
                            <td></td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>

        <script>

            $(document).ready(function () {
                $("#datepicker").datepicker({
                    dateFormat: 'yy-mm-dd',
                    minDate: 0, // 0 days offset = today
                    onSelect: function (dateText) {
                        $("#datepicker-error").remove();
                    }
                });

                $("#ok").validate({
                    rules: {
                        date: {
                            required: true
                        }
                    },
                    messages: {
                        date: {
                            required: "Please provide a date"
                        }
                    }
                });
            });

        </script>

    </tiles:putAttribute>
</tiles:insertDefinition>