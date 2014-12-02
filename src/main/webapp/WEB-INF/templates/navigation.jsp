<%@ include file="/WEB-INF/jsp/include.jsp" %>

<ul>
   			<li class="home"><a href="<c:url value="/home/" context="/dvd/emp"/>"><spring:message code="menu.home"/></a></li>
   			<li class="clients"><a href="<c:url value="/clientslist" context="/dvd/emp/clients"/>"><spring:message code="menu.clients"/></a></li>
   			<li class="movies"><a href="<c:url value="/movieslist" context="/dvd/emp/movies"/>"><spring:message code="menu.movies" /></a></li>
   			<li class="raports"><a href="#"><spring:message code="menu.raports" /></a></li>
   			<li class="administration"><a href="<c:url value="/administration" context="/dvd/emp/administration"/>"><spring:message code="menu.administration" /></a></li>
</ul>