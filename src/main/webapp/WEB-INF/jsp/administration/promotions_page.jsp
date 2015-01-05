<%@ include file="/WEB-INF/jsp/include.jsp" %>
<%@page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

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
                    <input type="submit" value="<spring:message code="common.button.edit"/>" class="editPromotionBtn"/>
                </form>
            </td>
            <td>
                <form name="deletePromotion" class="deletePromotion" action="<c:url value="promotions/delete"/>"
                      method="POST">
                    <input type="hidden" name="id" value="${prom.id}"/>
                    <input type="submit" value="<spring:message code="common.button.delete"/>" class="delPromotionBtn"/>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>