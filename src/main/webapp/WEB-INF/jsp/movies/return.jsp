<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/include.jsp" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<jsp:useBean id="cons" class="pl.radek.dvd.model.Constants"/>
<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="langs">
        <form id="polishLocale" name="polishLocale" action="<c:url value="/emp/return/movie"/>" method="POST">
            <input type="hidden" name="lang" value="pl"/>
            <input type="hidden" name="clientId" value="${param.clientId}"/>
            <input type="hidden" name="movieCopyId" value="${param.movieCopyId}"/>
            <input type="hidden" name="registryId" value="${param.registryId}"/>
        </form>

        <form id="englishLocale" name="englishLocale" action="<c:url value="/emp/return/movie"/>" method="POST">
            <input type="hidden" name="lang" value="en"/>
            <input type="hidden" name="clientId" value="${param.clientId}"/>
            <input type="hidden" name="movieCopyId" value="${param.movieCopyId}"/>
            <input type="hidden" name="registryId" value="${param.registryId}"/>
        </form>

        <a href="#" onclick="document.getElementById('englishLocale').submit(); return false;">EN</a>
        <a href="#" onclick="document.getElementById('polishLocale').submit(); return false;">PL</a>
    </tiles:putAttribute>
    <tiles:putAttribute name="content">

        <div id="clientDetails">
            <div class="filtreheader">
                <p><spring:message code="common.button.return"/></p>
            </div>
            <div class="separator"></div>
            <div class="inputs">
                <ul>
                    <li><label> <spring:message code="clients.clientsList.firstName"/>: </label> <c:out
                            value="${returnData.clientFirstName}"/></li>
                    <li><label> <spring:message code="clients.clientsList.lastName"/>: </label> <c:out
                            value="${returnData.clientLastName}"/></li>
                    <li><label> <spring:message code="movies.moviesList.title"/>: </label> <c:out
                            value="${returnData.title}"/>
                    </li>
                    <li><label> <spring:message code="movies.movieCopy.serialNumber"/>: </label> <c:out
                            value="${returnData.serialNumber}"/>
                    </li>
                    <li><label> <spring:message code="movies.moviesList.promotion"/>: </label> <c:out
                            value="${returnData.promotionName}"/>
                    </li>
                    <li><label> <spring:message code="clients.clientHistoryList.expectedReturnDate"/>: </label> <c:out
                            value="${returnData.expectedReturnDate}"/>
                    </li>
                    <li>
                        <label> <spring:message code="movies.movieCopy.comment"/>: </label>
                    </li>
                </ul>

                <table>
                    <tr>
                        <td>
                            <div id="returnOrPrint">
                                <form:form id="ok" name="ok" method="POST" commandName="returnDto" action="save">
                                    <form:hidden path="clientId"></form:hidden>
                                    <form:hidden path="movieCopyId"></form:hidden>
                                    <form:hidden path="registryId"></form:hidden>
                                    <form:textarea path="comment" rows="5" cols="30" cssClass="inputs"/>
                                    <form:errors path="comment" cssClass="error"></form:errors>
                                    <div class="separatorInvisible"></div>
                                    <input type="submit" value="<spring:message code="common.button.return"/>"
                                           class="myButton"/>
                                </form:form>
                            </div>
                        </td>
                    </tr>
                </table>

                <table>
                    <tr>
                        <td>
                            <form name="cancel"
                                  action="<c:url value="/clientdetails" context="/dvd/emp/clients"/>"
                                  method="get">
                                <input type="hidden" name="id" value="${param.clientId}"/>
                                <input type="submit" value="<spring:message code="common.button.back"/>"
                                       class="myButton"/>
                            </form>
                        </td>
                    </tr>
                </table>
            </div>
        </div>

        <script>
            $(document).ready(function () {
                InitReturnPrintReceipt.init();

            });
        </script>

    </tiles:putAttribute>
</tiles:insertDefinition>