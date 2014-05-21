<%@ include file="/WEB-INF/jsp/include.jsp" %>

<a href="<c:url value="/j_spring_security_logout"/>" class="logout">Logout ${pageContext.request.userPrincipal.name}</a>