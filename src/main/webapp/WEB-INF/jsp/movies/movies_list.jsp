<%@ include file="/WEB-INF/jsp/include.jsp" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@page import="pl.radek.dvd.model.Client, pl.radek.dvd.model.Constants, pl.radek.dvd.utils.JspMethods, java.util.List"%>
<%@page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="cons" class="pl.radek.dvd.model.Constants"/>

<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="langs">
		<c:url var="englishLocaleUrl" value="/emp/movies/movieslist.htm">
		<c:param name="lang" value="en" />
		</c:url>
		<c:url var="polishLocaleUrl" value="/emp/movies/movieslist.htm">
		<c:param name="lang" value="pl" />
		</c:url>
		<a href="${englishLocaleUrl}">EN</a>
		<a href="${polishLocaleUrl}">PL</a>
	</tiles:putAttribute>
    <tiles:putAttribute name="content">
	
		<h2>
   			<spring:message code="movies.moviesList.list"/>:
   		</h2>
	
	<div class="table">
               <table> 
                 <tr>
                  <td>
					<c:url value="movieslist.htm" var = "paginationURL"/>
					<a href="#" class="link"><spring:message code="movies.moviesList.title"/></a>
				  </td>
                  <td>
					<c:url value="movieslist.htm" var = "paginationURL"/>
					<a href="#" class="link"><spring:message code="movies.moviesList.director"/></a>
				  </td>
                  <td>
					<c:url value="movieslist.htm" var = "paginationURL"/>
					<a href="#" class="link"><spring:message code="movies.moviesList.productionYear"/></a>
				  </td>
                  <td>
					<c:url value="movieslist.htm" var = "paginationURL"/>
					<a href="#" class="link"><spring:message code="movies.moviesList.genre"/></a>
				  </td>
                  <td>
					<c:url value="movieslist.htm" var = "paginationURL"/>
					<a href="#" class="link"><spring:message code="movies.moviesList.promotion"/></a>
				  </td>
				  <td></td>
                 </tr>
					<c:forEach items="${moviesList}" var="movie">
						<tr>
							<td>  <c:out value="${movie.title}"/>  </td>
							<td>  <c:out value="${movie.director}"/>  </td>
							<td>  <c:out value="${movie.productionYear}"/>  </td>
							<td>  <c:out value="${movie.genre}"/>  </td>
							<td>  <c:out value="${movie.name}"/>  </td>
							<td> 
							  <form name="moviedetails" action=" <c:url value="moviedetails.htm"/>" method="get">
                                  <input type="hidden" name="id" value="${movie.id}" />
                                  <input type="submit" value="<spring:message code="common.button.details"/>" class = "myButton"/>
                              </form>
							</td>
						</tr>
					</c:forEach>   
               </table>
	</div>
	</br>
	<div id="page_numbers">
			<table> <tr>
			   <td>
			<%--Displaying First link except for the 1st page--%>   
			   <c:if test="${currentPage != 1}">
				<c:choose>
				<c:when test="${param.order != null && param.field != null}">
			   <form name="firstlink" action=" <c:url value="clientslist.htm"/>" method="get">
			   <c:if test="${!empty param.firstName || !empty param.lastName || !empty param.pesel}">
				 <input type="hidden" name="firstName" value = "${param.firstName}" />
				 <input type="hidden" name="lastName" value = "${param.lastName}" />
				 <input type="hidden" name="pesel" value = "${param.pesel}" />
				 </c:if>
                 <input type="hidden" name="order" value = "${param.order}" />
				 <input type="hidden" name="field" value = "${param.field}" />
				 <input type="hidden" name="currentPage" value = "1" />
                 <input type="submit" value="|<<" class = "myButtonTwo"/>
               </form>
				</c:when>
				<c:otherwise>
			   <form name="firstlink" action="<c:url value="clientslist.htm"/>" method="get">
			   <c:if test="${!empty param.firstName || !empty param.lastName || !empty param.pesel}">
				 <input type="hidden" name="firstName" value = "${param.firstName}" />
				 <input type="hidden" name="lastName" value = "${param.lastName}" />
				 <input type="hidden" name="pesel" value = "${param.pesel}" />
				 </c:if>
				 <input type="hidden" name="currentPage" value = "1" />
                 <input type="submit" value="|<<" class = "myButtonTwo"/>
               </form>
				</c:otherwise>
				</c:choose>
				</c:if>
			   </td>

			   <td>
			<%--Displaying Previous link except for the 1st page--%>
			   <c:if test="${currentPage != 1}">
				<c:choose>
				<c:when test="${param.order != null && param.field != null}">
			   <form name="previouslink" action="<c:url value="clientslist.htm"/>" method="get">
			   <c:if test="${!empty param.firstName || !empty param.lastName || !empty param.pesel}">
			   <input type="hidden" name="firstName" value = "${param.firstName}" />
				 <input type="hidden" name="lastName" value = "${param.lastName}" />
				 <input type="hidden" name="pesel" value = "${param.pesel}" />
				 </c:if>
                 <input type="hidden" name="order" value = "${param.order}" />
				 <input type="hidden" name="field" value = "${param.field}" />
				 <input type="hidden" name="currentPage" value = "${currentPage - 1}" />
                 <input type="submit" value="<" class = "myButtonTwo"/>
               </form>
				</c:when>
				<c:otherwise>
			   <form name="previouslink" action="<c:url value="clientslist.htm"/>" method="get">
			   <c:if test="${!empty param.firstName || !empty param.lastName || !empty param.pesel}">
			   <input type="hidden" name="firstName" value = "${param.firstName}" />
				 <input type="hidden" name="lastName" value = "${param.lastName}" />
				 <input type="hidden" name="pesel" value = "${param.pesel}" />
				 </c:if>
				 <input type="hidden" name="currentPage" value = "${currentPage - 1}" />
                 <input type="submit" value="<" class = "myButtonTwo"/>
               </form>
				</c:otherwise>
				</c:choose>
				</c:if>
			   </td>
 
			<%--Displaying Page numbers--%>
            <td> <c:out value="${currentPage}"/>/<c:out value="${noOfPages}"/> </td>

				<td>
			<%--Displaying Next link--%>
			   <c:if test="${currentPage < noOfPages}">
				<c:choose>
				<c:when test="${param.order != null && param.field != null}">
			   <form name="nextlink" action="<c:url value="clientslist.htm"/>" method="get">
			   <c:if test="${!empty param.firstName || !empty param.lastName || !empty param.pesel}">
			   <input type="hidden" name="firstName" value = "${param.firstName}" />
				 <input type="hidden" name="lastName" value = "${param.lastName}" />
				 <input type="hidden" name="pesel" value = "${param.pesel}" />
				 </c:if> 
                 <input type="hidden" name="order" value = "${param.order}" />
				 <input type="hidden" name="field" value = "${param.field}" />
				 <input type="hidden" name="currentPage" value = "${currentPage + 1}" />
                 <input type="submit" value=">" class = "myButtonTwo"/>
               </form>
				</c:when>
				<c:otherwise>
			   <form name="nextlink" action="<c:url value="clientslist.htm"/>" method="get">
			   <c:if test="${!empty param.firstName || !empty param.lastName || !empty param.pesel}">
			   <input type="hidden" name="firstName" value = "${param.firstName}" />
				 <input type="hidden" name="lastName" value = "${param.lastName}" />
				 <input type="hidden" name="pesel" value = "${param.pesel}" />
				 </c:if>
				 <input type="hidden" name="currentPage" value = "${currentPage + 1}" />
                 <input type="submit" value=">" class = "myButtonTwo"/>
               </form>
				</c:otherwise>
				</c:choose>
				</c:if>
			   </td>

			   <td>
			<%--Displaying Last link--%>
			   <c:if test="${currentPage < noOfPages}">
				<c:choose>
				<c:when test="${param.order != null && param.field != null}">
			   <form name="lastlink" action="<c:url value="clientslist.htm"/>" method="get">
				<c:if test="${!empty param.firstName || !empty param.lastName || !empty param.pesel}">
					<input type="hidden" name="firstName" value = "${param.firstName}" />
					<input type="hidden" name="lastName" value = "${param.lastName}" />
					<input type="hidden" name="pesel" value = "${param.pesel}" />
				</c:if>
                 <input type="hidden" name="order" value = "${param.order}" />
				 <input type="hidden" name="field" value = "${param.field}" />
				 <input type="hidden" name="currentPage" value = "${noOfPages}" />
                 <input type="submit" value=">>|" class = "myButtonTwo"/>
               </form>
				</c:when>
				<c:otherwise>
			   <form name="lastlink" action="<c:url value="clientslist.htm"/>" method="get">
				<c:if test="${!empty param.firstName || !empty param.lastName || !empty param.pesel}">
					<input type="hidden" name="firstName" value = "${param.firstName}" />
					<input type="hidden" name="lastName" value = "${param.lastName}" />
					<input type="hidden" name="pesel" value = "${param.pesel}" />
				</c:if>
				 <input type="hidden" name="currentPage" value = "${noOfPages}" />
                 <input type="submit" value=">>|" class = "myButtonTwo"/>
               </form>
				</c:otherwise>
				</c:choose>
				</c:if>
			   </td>
			   </tr>
			</table>
	</div>
	
	</tiles:putAttribute>
</tiles:insertDefinition>