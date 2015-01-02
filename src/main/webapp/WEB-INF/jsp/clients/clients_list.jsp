<%@ include file="/WEB-INF/jsp/include.jsp" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="hero" uri="/WEB-INF/tags/headero.tld" %>
<%@ taglib prefix="pagi" uri="/WEB-INF/tags/client_pagination.tld" %>
<%@page import="pl.radek.dvd.model.Client, pl.radek.dvd.model.Constants, pl.radek.dvd.utils.JspMethods, java.util.List" %>
<%@page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:useBean id="cons" class="pl.radek.dvd.model.Constants"/>

<tiles:insertDefinition name="defaultTemplate">
<tiles:putAttribute name="langs">
    <c:url var="englishLocaleUrl" value="/emp/clients/clientslist">
        <c:param name="lang" value="en"/>
    </c:url>
    <c:url var="polishLocaleUrl" value="/emp/clients/clientslist">
        <c:param name="lang" value="pl"/>
    </c:url>
    <a href="${englishLocaleUrl}">EN</a>
    <a href="${polishLocaleUrl}">PL</a>
</tiles:putAttribute>
<tiles:putAttribute name="content">

<h2>
    <spring:message code="clients.clientsList.list"/>:
</h2>

<div id="myform">
    <div class="filtreheader">
        <p><spring:message code="common.fillin.filtre"/></p>
    </div>
    <div class="separator"></div>
    <form:form method="GET" commandName="client" action="clientslist">
    <div class="inputs">
        <table>
            <tbody>
            <tr>
                <td><form:label path="firstName"><spring:message
                        code="clients.clientsList.firstName"/>:</form:label></td>
                <td><form:input path="firstName" cssClass="inputs"></form:input></td>
                <td><form:errors path="firstName" cssClass="error"></form:errors></td>
            </tr>
            <tr>
                <td><form:label path="lastName"><spring:message
                        code="clients.clientsList.lastName"/>:</form:label></td>
                <td><form:input path="lastName" cssClass="inputs"></form:input></td>
                <td><form:errors path="lastName" cssClass="error"></form:errors></td>
            </tr>
            <tr>
                <td><form:label path="pesel"><spring:message
                        code="clients.clientsList.pesel"/>:</form:label></td>
                <td><form:input path="pesel" cssClass="inputs"></form:input></td>
                <td><form:errors path="pesel" cssClass="error"></form:errors></td>
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
                    <form name="clearTable" action=" <c:url value="clientslist"/>" method="get">
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
                <hero:Linkuj order="${param.order}" field="${param.field}" columnName="${cons.firstname}"
                             firstName="${param.firstName}" lastName="${param.lastName}"
                             pesel="${param.pesel}"/>
                <c:url value="clientslist${hlink}" var="paginationURL"/>
                <a href="${paginationURL}" class="link"><spring:message
                        code="clients.clientsList.firstName"/></a>
            </td>
            <td>
                <hero:Linkuj order="${param.order}" field="${param.field}" columnName="${cons.lastname}"
                             firstName="${param.firstName}" lastName="${param.lastName}"
                             pesel="${param.pesel}"/>
                <c:url value="clientslist${hlink}" var="paginationURL"/>
                <a href="${paginationURL}" class="link"><spring:message
                        code="clients.clientsList.lastName"/></a>
            </td>
            <td>
                <hero:Linkuj order="${param.order}" field="${param.field}" columnName="${cons.pesel}"
                             firstName="${param.firstName}" lastName="${param.lastName}"
                             pesel="${param.pesel}"/>
                <c:url value="clientslist${hlink}" var="paginationURL"/>
                <a href="${paginationURL}" class="link"><spring:message code="clients.clientsList.pesel"/></a>
            </td>
            <td>
                <hero:Linkuj order="${param.order}" field="${param.field}" columnName="${cons.city}"
                             firstName="${param.firstName}" lastName="${param.lastName}"
                             pesel="${param.pesel}"/>
                <c:url value="clientslist${hlink}" var="paginationURL"/>
                <a href="${paginationURL}" class="link"><spring:message code="clients.clientsList.city"/></a>
            </td>
            <td>
                <hero:Linkuj order="${param.order}" field="${param.field}" columnName="${cons.street}"
                             firstName="${param.firstName}" lastName="${param.lastName}"
                             pesel="${param.pesel}"/>
                <c:url value="clientslist${hlink}" var="paginationURL"/>
                <a href="${paginationURL}" class="link"><spring:message code="clients.clientsList.street"/></a>
            </td>
            <td>
                <hero:Linkuj order="${param.order}" field="${param.field}" columnName="${cons.phonenumber}"
                             firstName="${param.firstName}" lastName="${param.lastName}"
                             pesel="${param.pesel}"/>
                <c:url value="clientslist${hlink}" var="paginationURL"/>
                <a href="${paginationURL}" class="link"><spring:message
                        code="clients.clientsList.phoneNumber"/></a>
            </td>
            <td>
                <hero:Linkuj order="${param.order}" field="${param.field}" columnName="${cons.email}"
                             firstName="${param.firstName}" lastName="${param.lastName}"
                             pesel="${param.pesel}"/>
                <c:url value="clientslist${hlink}" var="paginationURL"/>
                <a href="${paginationURL}" class="link"><spring:message code="clients.clientsList.email"/></a>
            </td>
            <td></td>
            <td></td>
            <sec:authorize ifAnyGranted="ROLE_ADMIN">
                <td></td>
            </sec:authorize>
        </tr>
        <c:forEach items="${clientList}" var="client">
            <tr>
                <td><c:out value="${client.firstName}"/></td>
                <td><c:out value="${client.lastName}"/></td>
                <td><c:out value="${client.pesel}"/></td>
                <td><c:out value="${client.city}"/></td>
                <td><c:out value="${client.street}"/></td>
                <td><c:out value="${client.phoneNumber}"/></td>
                <td><c:out value="${client.email}"/></td>
                <td>
                    <form name="clientdetails" action=" <c:url value="clientdetails"/>" method="get">
                        <input type="hidden" name="id" value="${client.id}"/>
                        <input type="submit" value="<spring:message code="common.button.details"/>"
                               class="myButton"/>
                    </form>
                </td>
                <td>
                    <form name="editclient" action=" <c:url value="controller"/>" method="post">
                        <input type="hidden" name="id" value="${client.id}"/>
                        <input type="submit" value="<spring:message code="common.button.edit"/>"
                               class="myButton"/>
                    </form>
                </td>
                <sec:authorize ifAnyGranted="ROLE_ADMIN">
                    <td>
                        <form action=" <c:url value="delete"/>" method="post"
                              onsubmit="return ConfirmDelete();">
                            <input type="hidden" name="id" value="${client.id}"/>
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
                    <pagi:Linkuj order="${param.order}" field="${param.field}" currentPage="1"
                                 firstName="${param.firstName}" lastName="${param.lastName}"
                                 pesel="${param.pesel}"/>
                    <c:url value="clientslist${paginlink}" var="paginURL"/>
                    <a href="${paginURL}" class="myButtonTwo"> |<< </a>
                </c:if>
            </td>

            <td>
                    <%--Displaying Previous link except for the 1st page--%>
                <c:if test="${currentPage != 1}">
                    <pagi:Linkuj order="${param.order}" field="${param.field}" currentPage="${currentPage - 1}"
                                 firstName="${param.firstName}" lastName="${param.lastName}"
                                 pesel="${param.pesel}"/>
                    <c:url value="clientslist${paginlink}" var="paginURL"/>
                    <a href="${paginURL}" class="myButtonTwo"> < </a>
                </c:if>
            </td>

                <%--Displaying Page numbers--%>
            <td><c:out value="${currentPage}"/>/<c:out value="${noOfPages}"/></td>

            <td>
                    <%--Displaying Next link--%>
                <c:if test="${currentPage < noOfPages}">
                    <pagi:Linkuj order="${param.order}" field="${param.field}" currentPage="${currentPage + 1}"
                                 firstName="${param.firstName}" lastName="${param.lastName}"
                                 pesel="${param.pesel}"/>
                    <c:url value="clientslist${paginlink}" var="paginURL"/>
                    <a href="${paginURL}" class="myButtonTwo"> > </a>
                </c:if>
            </td>

            <td>
                    <%--Displaying Last link--%>
                <c:if test="${currentPage < noOfPages}">
                    <pagi:Linkuj order="${param.order}" field="${param.field}" currentPage="${noOfPages}"
                                 firstName="${param.firstName}" lastName="${param.lastName}"
                                 pesel="${param.pesel}"/>
                    <c:url value="clientslist${paginlink}" var="paginURL"/>
                    <a href="${paginURL}" class="myButtonTwo"> >>| </a>
                </c:if>
            </td>
        </tr>
    </table>
</div>
</br>
<form name="newclient" action="<c:url value="controller"/>" method="post">
    <input type="hidden" name="id" value="new"/>
    <input type="submit" value="<spring:message code="clients.button.newclient"/>" class="myButton"/>
</form>
</br>


</tiles:putAttribute>
</tiles:insertDefinition>
