<%@ include file="/WEB-INF/jsp/include.jsp" %>

     <c:out value="${olek}"/>

	 <c:forEach items="${owoce}" var="owoc">
        <c:out value="${owoc}"/>
	 </c:forEach>
