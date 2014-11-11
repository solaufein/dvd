<%@ include file="/WEB-INF/jsp/include.jsp" %>

<table id="tabb">
    <tr>
        <td>
            <spring:message code="employee.employeeList.firstName"/>
        </td>
        <td>
            <spring:message code="employee.employeeList.lastName"/>
        </td>
        <td>
            <spring:message code="employee.employeeList.phoneNumber"/>
        </td>
        <td>
            <spring:message code="employee.employeeList.email"/>
        </td>
        <td></td>
        <td></td>
    </tr>
    <c:forEach items="${employeeList}" var="employee">
        <tr>
            <td><c:out value="${employee.firstName}"/></td>
            <td><c:out value="${employee.lastName}"/></td>
            <td><c:out value="${employee.phoneNumber}"/></td>
            <td><c:out value="${employee.email}"/></td>
            <td>
                <form name="editemployee" class="editemp" action="<c:url value="employees/edit"/>" method="post">
                    <input type="hidden" name="id" value="${employee.id}"/>
                    <input type="submit" value="<spring:message code="common.button.edit"/>" class="editempbtn"/>
                </form>
            </td>
            <td>
                <!--a href="<c:url value="employees/delete/${employee.id}"/>.json" id = "delemp" class = "myButton">Delete</a-->
                <form name="deleteemployee" class="delemp" action="<c:url value="employees/delete"/>" method="POST">
                    <input type="hidden" name="id" value="${employee.id}"/>
                    <input type="submit" value="<spring:message code="common.button.delete"/>" class="delempbtn"/>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>