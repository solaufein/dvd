<%@ include file="/WEB-INF/jsp/include.jsp" %>
<%@page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<h2>
    <spring:message code="employee.employeeList.list"/>:
</h2>

<div id="dialog-form" title="Create new user">
    <p class="validateTips">All form fields are required.</p>

    <form:form method="POST" commandName="emp" action="new">
        <form:hidden path="id"></form:hidden>

        <form:label path="firstName"><spring:message code="employee.employeeList.firstName"/>:</form:label>
        <form:input path="firstName" cssClass="text ui-widget-content ui-corner-all"></form:input>
        <form:errors path="firstName" cssClass="error"></form:errors>


        <form:label path="lastName"><spring:message code="employee.employeeList.lastName"/>:</form:label>
        <form:input path="lastName" cssClass="text ui-widget-content ui-corner-all"></form:input>
        <form:errors path="lastName" cssClass="error"></form:errors>


        <form:label path="phoneNumber"><spring:message code="employee.employeeList.phoneNumber"/>:</form:label>
        <form:input path="phoneNumber" cssClass="text ui-widget-content ui-corner-all"></form:input>
        <form:errors path="phoneNumber" cssClass="error"></form:errors>

        <form:label path="email"><spring:message code="employee.employeeList.email"/>:</form:label>
        <form:input path="email" cssClass="text ui-widget-content ui-corner-all"></form:input>
        <form:errors path="email" cssClass="error"></form:errors>

        <form:label path="password"><spring:message code="employee.employeeList.password"/>:</form:label>
        <form:password path="password" cssClass="text ui-widget-content ui-corner-all"></form:password>
        <form:errors path="password" cssClass="error"></form:errors>

        <label for="repassword"><spring:message code="employee.employeeList.repassword"/></label>
        <input type="password" name="repassword" id="repassword" value="" class="text ui-widget-content ui-corner-all">

        <form:checkboxes items="${allRoles}" path="rolesSet" itemLabel="role" itemValue="id"/>
        <form:errors path="rolesSet" cssClass="error"/>

        <!-- Allow form submission with keyboard without duplicating the dialog button -->
        <input type="submit" value="<spring:message code="common.button.submit"/>" tabindex="-1"
               style="position:absolute; top:-1000px">
    </form:form>
</div>

<div class="table">
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
</div>
</br>
<div id="page_numbers">
    <table>
        <tr>
            <td>
                <%--Displaying First link except for the 1st page--%>
                <form name="firstlink" id="firstlink" action="<c:url value="employees/page"/>" method="get">
                    <input type="hidden" name="currentPage" value="1"/>
                    <input type="submit" value="|<<" class="myButtonTwo"/>
                </form>
            </td>

            <td>
                <%--Displaying Previous link except for the 1st page--%>
                <form name="previouslink" id="previouslink" action="<c:url value="employees/page"/>" method="get">
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
                <form name="nextlink" id="nextlink" action="<c:url value="employees/page"/>" method="get">
                    <input type="hidden" name="currentPage" value="${currentPage + 1}"/>
                    <input type="submit" value=">" class="myButtonTwo"/>
                </form>
            </td>

            <td>
                <%--Displaying Last link--%>
                <form name="lastlink" id="lastlink" action="<c:url value="employees/page"/>" method="get">
                    <input type="hidden" name="currentPage" value="${noOfPages}"/>
                    <input type="submit" value=">>|" class="myButtonTwo"/>
                </form>
            </td>
        </tr>
    </table>
</div>
</br>
<!--form name="newemployee" id="newemp" action="<c:url value="employees/new"/>" method="post">
<input type="hidden" name="id" value = "new" />
<input type="submit" value="<spring:message code="employee.button.newemployee"/>" class = "myButton"/>
</form-->

<button id="create-user" value="<c:url value="employees/new"/>" class="myButton"><spring:message
        code="employee.button.newemployee"/></button>
</br>

<c:url value="employees/list" var="adres"/>

<script>
    $(document).ready(function () {

        initPaginationButtons("${noOfPages}");
        InitEmpActionButtons.init();
    });
</script>