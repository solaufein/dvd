<%@ include file="/WEB-INF/jsp/include.jsp" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="pagi" uri="/WEB-INF/tags/raports/tophits_raport_pagination.tld" %>
<%@page import="pl.radek.dvd.model.Constants, pl.radek.dvd.utils.JspMethods, java.util.List" %>
<%@page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:useBean id="cons" class="pl.radek.dvd.model.Constants"/>

<tiles:insertDefinition name="defaultTemplate">
<tiles:putAttribute name="langs">
    <c:url var="englishLocaleUrl" value="/emp/raports/tophits">
        <c:param name="lang" value="en"/>
    </c:url>
    <c:url var="polishLocaleUrl" value="/emp/raports/tophits">
        <c:param name="lang" value="pl"/>
    </c:url>
    <a href="${englishLocaleUrl}">EN</a>
    <a href="${polishLocaleUrl}">PL</a>
</tiles:putAttribute>
<tiles:putAttribute name="content">

<div id="myform">
    <div class="filtreheader">
        <p><spring:message code="raports.raportslist.tophits"/></p>
    </div>
    <div class="separator"></div>
    <form:form id="ok" method="GET" commandName="tophits" action="tophits">
    <div class="inputs">
        <table>
            <tbody>
            <tr>
                <td><form:label path="dateFrom"><spring:message
                        code="raports.tophits.dateFrom"/></form:label></td>
                <td><form:input path="dateFrom" cssClass="inputs"></form:input></td>
                <td><form:errors path="dateFrom" cssClass="error"></form:errors></td>
                <td><form:label path="dateTo"><spring:message code="raports.tophits.dateTo"/></form:label></td>
                <td><form:input path="dateTo" cssClass="inputs"></form:input></td>
                <td><form:errors path="dateTo" cssClass="error"></form:errors></td>
            </tr>
            <tr>
                <td><form:label path="genre"><spring:message code="movies.moviesList.genre"/>:</form:label></td>
                <td><form:select path="genre">
                    <form:option value="NONE" label="--- Select ---"/>
                    <form:options items="${allGenres}"/>
                </form:select>
                </td>
                <td><form:errors path="genre" cssClass="error"></form:errors></td>
            </tr>
            <tr>
                <td><form:label path="promotion"><spring:message
                        code="movies.moviesList.promotion"/>:</form:label></td>
                <td><form:select path="promotion">
                    <form:option value="NONE" label="--- Select ---"/>
                    <form:options items="${allPromotions}"/>
                </form:select>
                </td>
                <td><form:errors path="promotion" cssClass="error"></form:errors></td>
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
                    <form name="clearTable" action=" <c:url value="tophits"/>" method="get">
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

<c:if test="${not empty dataList}">
    <div class="table">
        <table>
            <tr>
                <td>
                </td>
                <td>
                    <spring:message code="raports.tophits.loanAmount"/>
                </td>
            </tr>
            <c:forEach items="${dataList}" var="o">
                <tr>
                    <td><c:out value="${o.title}"/></td>
                    <td><c:out value="${o.loanCount}"/></td>
                </tr>
            </c:forEach>
            <tr>
                <td><spring:message code="raports.tophits.total"/></td>
                <td><c:out value="${loanCount}"/></td>
            </tr>
        </table>
    </div>
    <br>


    <div id="page_numbers">
        <table>
            <tr>
                <td>
                        <%--Displaying First link except for the 1st page--%>
                    <c:if test="${currentPage != 1}">
                        <pagi:Linkuj currentPage="1"
                                     dateFrom="${param.dateFrom}"
                                     dateTo="${param.dateTo}"
                                     genre="${param.genre}"
                                     promotion="${param.promotion}"/>
                        <c:url value="tophits${paginlink}" var="paginURL"/>
                        <a href="${paginURL}" class="myButtonTwo"> |<< </a>
                    </c:if>
                </td>

                <td>
                        <%--Displaying Previous link except for the 1st page--%>
                    <c:if test="${currentPage != 1}">
                        <pagi:Linkuj currentPage="${currentPage - 1}"
                                     dateFrom="${param.dateFrom}"
                                     dateTo="${param.dateTo}"
                                     genre="${param.genre}"
                                     promotion="${param.promotion}"/>
                        <c:url value="tophits${paginlink}" var="paginURL"/>
                        <a href="${paginURL}" class="myButtonTwo"> < </a>
                    </c:if>
                </td>

                    <%--Displaying Page numbers--%>
                <td><c:out value="${currentPage}"/>/<c:out value="${noOfPages}"/></td>

                <td>
                        <%--Displaying Next link--%>
                    <c:if test="${currentPage < noOfPages}">
                        <pagi:Linkuj currentPage="${currentPage + 1}"
                                     dateFrom="${param.dateFrom}"
                                     dateTo="${param.dateTo}"
                                     genre="${param.genre}"
                                     promotion="${param.promotion}"/>
                        <c:url value="tophits${paginlink}" var="paginURL"/>
                        <a href="${paginURL}" class="myButtonTwo"> > </a>
                    </c:if>
                </td>

                <td>
                        <%--Displaying Last link--%>
                    <c:if test="${currentPage < noOfPages}">
                        <pagi:Linkuj currentPage="${noOfPages}"
                                     dateFrom="${param.dateFrom}"
                                     dateTo="${param.dateTo}"
                                     genre="${param.genre}"
                                     promotion="${param.promotion}"/>
                        <c:url value="tophits${paginlink}" var="paginURL"/>
                        <a href="${paginURL}" class="myButtonTwo"> >>| </a>
                    </c:if>
                </td>
            </tr>
        </table>
    </div>
</c:if>
<br>

<div class="clear"></div>
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

    $(document).ready(function () {
        /*  $("#datepicker").datepicker({
         dateFormat: 'yy-mm-dd',
         minDate: 0, // 0 days offset = today
         onSelect: function (dateText) {
         $("#datepicker-error").remove();
         }
         });*/


        $("#dateFrom").datepicker({
            dateFormat: 'yy-mm-dd',
            defaultDate: "+1w",
            changeMonth: true,
            numberOfMonths: 3,
            onClose: function (selectedDate) {
                $("#dateTo").datepicker("option", "minDate", selectedDate);
            },
            onSelect: function (dateText) {
                $("#datepicker-error").remove();
            }
        });
        $("#dateTo").datepicker({
            dateFormat: 'yy-mm-dd',
            defaultDate: "+1w",
            changeMonth: true,
            numberOfMonths: 3,
            onClose: function (selectedDate) {
                $("#dateFrom").datepicker("option", "maxDate", selectedDate);
            },
            onSelect: function (dateText) {
                $("#datepicker-error").remove();
            }
        });

        $("#ok").validate({
            rules: {
                dateFrom: {
                    required: true
                },
                dateTo: {
                    required: true
                }
            },
            messages: {
                dateFrom: {
                    required: "Please provide a date"
                },
                dateTo: {
                    required: "Please provide a date"
                }
            }
        });

        $("#genre").selectmenu({
            width: 180
        });
        $("#promotion").selectmenu({
            width: 180
        });

    });

</script>

</tiles:putAttribute>
</tiles:insertDefinition>