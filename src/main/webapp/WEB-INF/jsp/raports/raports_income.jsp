<%@ include file="/WEB-INF/jsp/include.jsp" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="pagi" uri="/WEB-INF/tags/raports/income_raport_pagination.tld" %>
<%@page import="pl.radek.dvd.model.Constants, pl.radek.dvd.utils.JspMethods, java.util.List" %>
<%@page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:useBean id="cons" class="pl.radek.dvd.model.Constants"/>

<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="langs">
        <c:url var="englishLocaleUrl" value="/emp/raports/income">
            <c:param name="lang" value="en"/>
        </c:url>
        <c:url var="polishLocaleUrl" value="/emp/raports/income">
            <c:param name="lang" value="pl"/>
        </c:url>
        <a href="${englishLocaleUrl}">EN</a>
        <a href="${polishLocaleUrl}">PL</a>
    </tiles:putAttribute>
    <tiles:putAttribute name="content">

        <div id="myform">
            <div class="filtreheader">
                <p><spring:message code="raports.raportslist.income"/></p>
            </div>
            <div class="separator"></div>
            <form:form id="ok" method="GET" commandName="income" action="income">
            <div class="inputs">
                <table>
                    <tbody>
                    <tr>
                        <td><form:label path="dateFrom"><spring:message
                                code="raports.tophits.dateFrom"/></form:label></td>
                        <td><form:input path="dateFrom" cssClass="inputs"></form:input></td>
                        <td><form:label path="dateTo"><spring:message code="raports.tophits.dateTo"/></form:label></td>
                        <td><form:input path="dateTo" cssClass="inputs"></form:input></td>
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
                        <td><form:label path="section"><spring:message
                                code="raports.income.section"/>:</form:label></td>
                        <td><form:select path="section">
                            <form:options items="${allSections}"/>
                        </form:select>
                        </td>
                        <td><form:errors path="section" cssClass="error"></form:errors></td>
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
                            <form name="clearTable" action=" <c:url value="income"/>" method="get">
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
                        <c:forEach items="${promotionNames}" var="prom">
                            <td><c:out value="${prom}"/></td>
                        </c:forEach>
                        <td style="background: lightblue">
                            <spring:message code="raports.tophits.total"/>
                        </td>
                    </tr>
                    <c:forEach items="${dataList}" var="o">
                        <tr>
                            <td><c:out value="${o.name}"/></td>
                            <c:forEach items="${o.amount}" var="amount">
                                <td><c:out value="${amount}"/></td>
                            </c:forEach>
                            <td><c:out value="${o.totalAmount}"/></td>
                        </tr>
                    </c:forEach>
                    <tr class="">
                        <td style="background: lightblue"><spring:message code="raports.tophits.total"/></td>
                        <c:forEach items="${amountPerPromotion}" var="amount">
                            <td style="background: lightblue"><c:out value="${amount.totalAmount}"/></td>
                        </c:forEach>
                        <td style="background: lightblue"><c:out value="${total}"/></td>
                    </tr>
                </table>
            </div>
            <br>

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
                $("#dateFrom").datepicker({
                    dateFormat: 'yy-mm-dd',
                    defaultDate: "+1w",
                    changeMonth: true,
                    numberOfMonths: 3,
                    onClose: function (selectedDate) {
                        $("#dateTo").datepicker("option", "minDate", selectedDate);
                    },
                    onSelect: function (dateText) {
                        $("#dateFrom-error").remove();
                        $("#dateFrom").removeClass("error");
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
                        $("#dateTo-error").remove();
                        $("#dateTo").removeClass("error");
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
                $("#section").selectmenu({
                    width: 180
                });

            });

        </script>

    </tiles:putAttribute>
</tiles:insertDefinition>