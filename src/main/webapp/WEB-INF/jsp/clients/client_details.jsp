<%@ include file="/WEB-INF/jsp/include.jsp" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@page import="pl.radek.dvd.model.Client, pl.radek.dvd.model.Constants, pl.radek.dvd.utils.JspMethods, java.util.List"%>
<%@page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="cons" class="pl.radek.dvd.model.Constants"/>

<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="langs">
		<c:url var="englishLocaleUrl" value="/emp/clients/clientdetails.htm">
		<c:param name="id" value="${param.id}" />
		<c:param name="lang" value="en" />
		</c:url>
		<c:url var="polishLocaleUrl" value="/emp/clients/clientdetails.htm">
		<c:param name="id" value="${param.id}" />
		<c:param name="lang" value="pl" />
		</c:url>
		
		<a href="${englishLocaleUrl}">EN</a>
		<a href="${polishLocaleUrl}">PL</a>
	</tiles:putAttribute>
    <tiles:putAttribute name="content">
	<h2>
   		<spring:message code="clients.client.details"/>:
	</h2>
   		<div id = "myform">
		<div class="filtreheader">
			<p> <spring:message code="clients.client.history"/> </p>
		</div>
		<div class="separator"></div>
		<div class="inputs">
			<ul>
				<li><label> <spring:message code="clients.clientsList.firstName"/>: </label> <c:out value="${client.firstName}"/>  </li>
				<li><label> <spring:message code="clients.clientsList.lastName"/>: </label>  <c:out value="${client.lastName}"/>  </li>
				<li><label> <spring:message code="clients.clientsList.pesel"/>: </label>  <c:out value="${client.pesel}"/>  </li>
				<li><label> <spring:message code="clients.clientsList.city"/>: </label>  <c:out value="${client.city}"/>  </li>
				<li><label> <spring:message code="clients.clientsList.street"/>: </label>  <c:out value="${client.street}"/>  </li>
				<li><label> <spring:message code="clients.clientsList.phoneNumber"/>: </label>  <c:out value="${client.phoneNumber}"/>  </li>
				<li><label> <spring:message code="clients.clientsList.email"/>: </label>  <c:out value="${client.email}"/>  </li>
			</ul>
		</div> 
		</div>
		<div class="rentMovieButton">
			<form name="rentMovie" action=" <c:url value="clients/clientdetails.htm"/>" method="get">
				<input type="hidden" name="id" value="${param.id}" />
				<input type="submit" value="<spring:message code="common.button.rentMovie"/>" class = "myButton"/>
			</form>
		</div>
			<div class="table">
               <table> 
                 <tr>
                  <td>
					<spring:message code="clients.clientHistoryList.title"/>
				  </td>
                  <td>
					<spring:message code="clients.clientHistoryList.serialNumber"/>
				  </td>
                  <td>
					<spring:message code="clients.clientHistoryList.rentDate"/>
				  </td>
                  <td>
					<spring:message code="clients.clientHistoryList.returnDate"/>
				  </td>
                  <c:if test="${not empty details.returnDate}"><td></td></c:if>
                  <c:if test="${empty details.returnDate}"><td></td></c:if>
				 </tr>
					<c:forEach items="${clientDetails}" var="details">
						<tr>
							<td>  <c:out value="${details.title}"/>  </td>
							<td>  <c:out value="${details.serialNumber}"/>  </td>
							<td>  <c:out value="${details.rentDate}"/>  </td>
							<td>  <c:out value="${details.returnDate}"/>  </td>
							 <c:if test="${not empty details.returnDate}">
							 <td>
							  <form name="printReceipt" action=" <c:url value="printreceipt.htm"/>" method="post">
                                  <input type="hidden" name="registryid" value="${details.id}" />
								  <input type="hidden" name="clientid" value="${client.id}" />
                                  <input type="submit" value="<spring:message code="common.button.print"/>" class = "myButton"/>
                              </form>
							  </td>
							 </c:if>
							 <c:if test="${empty details.returnDate}">
							 <td>
							  <form name="returnMovie" action=" <c:url value="clientdetails.htm"/>" method="get">
                                  <input type="hidden" name="id" value="${details.id}" />
                                  <input type="submit" value="<spring:message code="common.button.return"/>" class = "myButton"/>
                              </form>
							  </td>
							 </c:if>
						</tr>
					</c:forEach>   
               </table>
	</div>
		</br>
		<div id="page_numbers">
			  <table>  <tr>
			   <td>
			<%--Displaying First link except for the 1st page--%>   
			   <c:if test="${currentPage != 1}">
			   <form name="firstlink" action="<c:url value="clientdetails.htm"/>" method="get">
				 <input type="hidden" name="currentPage" value = "1" />
                 <input type="submit" value="|<<" class = "myButtonTwo"/>
               </form>
				</c:if>
			   </td>

			   <td>
			<%--Displaying Previous link except for the 1st page--%>
			   <c:if test="${currentPage != 1}">
			   <form name="previouslink" action="<c:url value="clientdetails.htm"/>" method="get">
				 <input type="hidden" name="currentPage" value = "${currentPage - 1}" />
                 <input type="submit" value="<" class = "myButtonTwo"/>
               </form>
				</c:if>
			   </td>
 
			<%--Displaying Page numbers--%>
            <td> <c:out value="${currentPage}"/>/<c:out value="${noOfPages}"/> </td>

				<td>
			<%--Displaying Next link--%>
			   <c:if test="${currentPage < noOfPages}">
			   <form name="nextlink" action="<c:url value="clientdetails.htm"/>" method="get">
				 <input type="hidden" name="currentPage" value = "${currentPage + 1}" />
                 <input type="submit" value=">" class = "myButtonTwo"/>
               </form>
				</c:if>
			   </td>

			   <td>
			<%--Displaying Last link--%>
			   <c:if test="${currentPage < noOfPages}">
			   <form name="lastlink" action="<c:url value="clientdetails.htm"/>" method="get">
				 <input type="hidden" name="currentPage" value = "${noOfPages}" />
                 <input type="submit" value=">>|" class = "myButtonTwo"/>
               </form>
				</c:if>
			   </td>
			   </tr>
			   </table>
			   </div>
			   </br>
		
		<form name="back" action="<c:url value="clientslist.htm"/>" method="get">
		<table>
		<tr>
			<td colspan="2">
				<input type="submit" value="<spring:message code="common.button.back"/>" class = "myButton"/>
			</td>
			<td></td>
			<td></td>
		</tr>
		</table>
		</form>
		
</tiles:putAttribute>
</tiles:insertDefinition>

