<%@ include file="/WEB-INF/jsp/include.jsp" %>

<div id="navigation_left">
    <ul>
        <li class="promotions"><a href="<c:url value="promotions/list"/>"><spring:message code="menu.promotions"/></a>
        </li>
        <li class="employees"><a href="<c:url value="employees/list"/>"><spring:message code="menu.employees"/></a></li>
    </ul>
</div>