<%@ include file="/WEB-INF/jsp/include.jsp" %>
<%@page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<h2>
    <spring:message code="promotion.promotionList.list"/>:
</h2>

<div id="dialog-form" title="Create new promotion">
    <p class="validateTips">All form fields are required.</p>

    <form:form method="POST" commandName="promotion" action="new">
        <form:hidden path="id"></form:hidden>

        <form:label path="name"><spring:message code="promotion.promotionList.name"/>:</form:label>
        <form:input path="name" cssClass="text ui-widget-content ui-corner-all"></form:input>
        <form:errors path="name" cssClass="error"></form:errors>

        <form:label path="value"><spring:message code="promotion.promotionList.value"/>:</form:label>
        <form:input path="value" cssClass="text ui-widget-content ui-corner-all"></form:input>
        <form:errors path="value" cssClass="error"></form:errors>

        <form:label path="promotionDaysNumber"><spring:message code="promotion.promotionList.days"/>:</form:label>
        <form:input path="promotionDaysNumber" cssClass="text ui-widget-content ui-corner-all"></form:input>
        <form:errors path="promotionDaysNumber" cssClass="error"></form:errors>

        <!-- Allow form submission with keyboard without duplicating the dialog button -->
        <input type="submit" value="<spring:message code="common.button.submit"/>" tabindex="-1"
               style="position:absolute; top:-1000px">
    </form:form>
</div>

<div class="table">
    <table id="tabb">
        <tr>
            <td>
                <spring:message code="promotion.promotionList.name"/>
            </td>
            <td>
                <spring:message code="promotion.promotionList.value"/>
            </td>
            <td>
                <spring:message code="promotion.promotionList.days"/>
            </td>
            <td></td>
            <td></td>
        </tr>
        <c:forEach items="${promotionList}" var="prom">
            <tr>
                <td><c:out value="${prom.name}"/></td>
                <td><c:out value="${prom.value}"/></td>
                <td><c:out value="${prom.promotionDaysNumber}"/></td>
                <td>
                    <form name="editPromotion" class="editPromotion" action="<c:url value="promotions/edit"/>"
                          method="post">
                        <input type="hidden" name="id" value="${prom.id}"/>
                        <input type="submit" value="<spring:message code="common.button.edit"/>"
                               class="editPromotionBtn"/>
                    </form>
                </td>
                <td>
                    <form name="deletePromotion" class="deletePromotion" action="<c:url value="promotions/delete"/>"
                          method="POST">
                        <input type="hidden" name="id" value="${prom.id}"/>
                        <input type="submit" value="<spring:message code="common.button.delete"/>"
                               class="delPromotionBtn"/>
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
                <form name="firstlink" id="firstlink" action="<c:url value="promotions/page"/>" method="get">
                    <input type="hidden" name="currentPage" value="1"/>
                    <input type="submit" value="|<<" class="myButtonTwo"/>
                </form>
            </td>

            <td>
                <%--Displaying Previous link except for the 1st page--%>
                <form name="previouslink" id="previouslink" action="<c:url value="promotions/page"/>" method="get">
                    <input type="hidden" name="currentPage" value="${currentPage - 1}"/>
                    <input type="submit" value="<" class="myButtonTwo"/>
                </form>
            </td>

            <%--Displaying Page numbers--%>
            <td>
                <div id="page">
                    <c:out value="${currentPage}"/>/<c:out value="${noOfPages}"/>
                </div>
            </td>

            <td>
                <%--Displaying Next link--%>
                <form name="nextlink" id="nextlink" action="<c:url value="promotions/page"/>" method="get">
                    <input type="hidden" name="currentPage" value="${currentPage + 1}"/>
                    <input type="submit" value=">" class="myButtonTwo"/>
                </form>
            </td>

            <td>
                <%--Displaying Last link--%>
                <form name="lastlink" id="lastlink" action="<c:url value="promotions/page"/>" method="get">
                    <input type="hidden" name="currentPage" value="${noOfPages}"/>
                    <input type="submit" value=">>|" class="myButtonTwo"/>
                </form>
            </td>
        </tr>
    </table>
</div>
</br>

<button id="create-promotion" value="<c:url value="promotions/new"/>" class="myButton"><spring:message
        code="promotion.button.newpromotion"/></button>
</br>

<c:url value="promotions/list" var="adres"/>

<script>
    $(document).ready(function () {

        initPaginationButtons("${noOfPages}");
        InitActionButtons.init();
    });
</script>