<%@ include file="/WEB-INF/jsp/include.jsp" %>

<ul>
   			<li><a href="#"><spring:message code="menu.home" /></a></li>
   			<li><a href="<c:url value="/clientslist.htm" context="/dvd/emp/clients"/>"><spring:message code="menu.clients" /></a></li>
   			<li><a href="<c:url value="/movieslist.htm" context="/dvd/emp/movies"/>"><spring:message code="menu.movies" /></a></li>
   			<li><a href="#"><spring:message code="menu.raports" /></a></li>
   			<li><a href="#"><spring:message code="menu.administration" /></a></li>
</ul>