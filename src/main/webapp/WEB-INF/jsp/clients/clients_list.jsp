<%@ include file="/WEB-INF/jsp/include.jsp" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@page import="pl.radek.dvd.model.Client, pl.radek.dvd.model.Constants, pl.radek.dvd.utils.JspMethods, java.util.List"%>
<%@page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="cons" class="pl.radek.dvd.model.Constants"/>

<tiles:insertDefinition name="defaultTemplate">

    <tiles:putAttribute name="content">

   		<h2>
   			<spring:message code="clients.clientsList.list"/>:
   		</h2>
		
		<div id = "myform">
		<div class="filtreheader">
			<p> <spring:message code="common.fillin.filtre"/> </p>
		</div>
		<div class="separator"></div>
		<form:form method="GET" commandName="client" action="clients.htm" >	 
			<div class="inputs">
			<table>
				<tbody>
				<tr>
					<td><form:hidden path="id"></form:hidden></td>
				</tr>
				<tr>
					<td><form:label path="firstName"><spring:message code="clients.clientsList.firstName"/>:</form:label></td>
					<td><form:input path="firstName" cssClass = "inputs"></form:input></td>
					<td><form:errors path="firstName" cssClass="error"></form:errors></td>
				</tr>
				<tr>
					<td><form:label path="lastName"><spring:message code="clients.clientsList.lastName"/>:</form:label></td>
					<td><form:input path="lastName" cssClass = "inputs"></form:input></td>
					<td><form:errors path="lastName" cssClass="error"></form:errors></td>
				</tr>
				<tr>
					<td><form:label path="pesel"><spring:message code="clients.clientsList.pesel"/>:</form:label></td>
					<td><form:input path="pesel" cssClass = "inputs"></form:input></td>
					<td><form:errors path="pesel" cssClass="error"></form:errors></td>
				</tr>
				<tr><td></td></tr>
				<tr>
					<td colspan="2">
						<input type="submit" value="<spring:message code="common.button.filtre"/>" >
					</td>
					<td></td>
					<td></td>
				</tr>
			</tbody></table> 
		</form:form>
			</div> 
		</div>
		 
	<div class="table">
               <table> 
                 <tr>
                  <td>
					<c:choose>
						<c:when test="${param.order == cons.desc && param.field == cons.firstname}">
							<c:url value="clients.htm" var="paginationURL">
								<c:param name="order"  value="${cons.asc}" />
								<c:param name="field"  value="${cons.firstname}" />
							</c:url>
						</c:when>
						<c:otherwise>
							<c:url value="clients.htm" var="paginationURL">
								<c:param name="order"   value="${cons.desc}" />
								<c:param name="field"    value="${cons.firstname}" />
							</c:url>
						</c:otherwise>
					  </c:choose>

					<a href="${paginationURL}" class="link"><spring:message code="clients.clientsList.firstName"/></a>
				  </td>
                  <td>
					  <c:choose>
						<c:when test="${param.order == cons.desc && param.field == cons.lastname}">
							<c:url value="clients.htm" var="paginationURL">
								<c:param name="order"   value="${cons.asc}" />
								<c:param name="field"    value="${cons.lastname}" />
							</c:url>
						</c:when>
						<c:otherwise>
							<c:url value="clients.htm" var="paginationURL">
								<c:param name="order"   value="${cons.desc}" />
								<c:param name="field"    value="${cons.lastname}" />
							</c:url>
						</c:otherwise>
					  </c:choose>

					<a href="${paginationURL}" class="link"><spring:message code="clients.clientsList.lastName"/></a>
				  </td>
                  <td>
					  <c:choose>
						<c:when test="${param.order == cons.desc && param.field == cons.pesel}">
							<c:url value="clients.htm" var="paginationURL">
								<c:param name="order"   value="${cons.asc}" />
								<c:param name="field"    value="${cons.pesel}" />
							</c:url>
						</c:when>
						<c:otherwise>
							<c:url value="clients.htm" var="paginationURL">
								<c:param name="order"   value="${cons.desc}" />
								<c:param name="field"    value="${cons.pesel}" />
							</c:url>
						</c:otherwise>
					  </c:choose>

					<a href="${paginationURL}" class="link"><spring:message code="clients.clientsList.pesel"/></a>
				  </td>
                  <td>
					  <c:choose>
						<c:when test="${param.order == cons.desc && param.field == cons.city}">
							<c:url value="clients.htm" var="paginationURL">
								<c:param name="order"   value="${cons.asc}" />
								<c:param name="field"    value="${cons.city}" />
							</c:url>
						</c:when>
						<c:otherwise>
							<c:url value="clients.htm" var="paginationURL">
								<c:param name="order"   value="${cons.desc}" />
								<c:param name="field"    value="${cons.city}" />
							</c:url>
						</c:otherwise>
					  </c:choose>

					<a href="${paginationURL}" class="link"><spring:message code="clients.clientsList.city"/></a>
				  </td>
                  <td>
					  <c:choose>
						<c:when test="${param.order == cons.desc && param.field == cons.street}">
							<c:url value="clients.htm" var="paginationURL">
								<c:param name="order"   value="${cons.asc}" />
								<c:param name="field"    value="${cons.street}" />
							</c:url>
						</c:when>
						<c:otherwise>
							<c:url value="clients.htm" var="paginationURL">
								<c:param name="order"   value="${cons.desc}" />
								<c:param name="field"    value="${cons.street}" />
							</c:url>
						</c:otherwise>
					  </c:choose>

					<a href="${paginationURL}" class="link"><spring:message code="clients.clientsList.street"/></a>
				  </td>
                  <td>
					  <c:choose>
						<c:when test="${param.order == cons.desc && param.field == cons.phonenumber}">
							<c:url value="clients.htm" var="paginationURL">
								<c:param name="order"   value="${cons.asc}" />
								<c:param name="field"    value="${cons.phonenumber}" />
							</c:url>
						</c:when>
						<c:otherwise>
							<c:url value="clients.htm" var="paginationURL">
								<c:param name="order"   value="${cons.desc}" />
								<c:param name="field"    value="${cons.phonenumber}" />
							</c:url>
						</c:otherwise>
					  </c:choose>

					<a href="${paginationURL}" class="link"><spring:message code="clients.clientsList.phoneNumber"/></a>
				  </td>
                  <td>
					  <c:choose>
						<c:when test="${param.order == cons.desc && param.field == cons.email}">
							<c:url value="clients.htm" var="paginationURL">
								<c:param name="order"   value="${cons.asc}" />
								<c:param name="field"    value="${cons.email}" />
							</c:url>
						</c:when>
						<c:otherwise>
							<c:url value="clients.htm" var="paginationURL">
								<c:param name="order"   value="${cons.desc}" />
								<c:param name="field"    value="${cons.email}" />
							</c:url>
						</c:otherwise>
					  </c:choose>

					<a href="${paginationURL}" class="link"><spring:message code="clients.clientsList.email"/></a>
				  </td>
				  <td></td>
				  <sec:authorize ifAnyGranted="ROLE_ADMIN">
				  <td></td>
				  <td></td>
				  </sec:authorize>
                 </tr>
					<c:forEach items="${clientList}" var="client">
						<tr>
							<td>  <c:out value="${client.firstName}"/>  </td>
							<td>  <c:out value="${client.lastName}"/>  </td>
							<td>  <c:out value="${client.pesel}"/>  </td>
							<td>  <c:out value="${client.city}"/>  </td>
							<td>  <c:out value="${client.street}"/>  </td>
							<td>  <c:out value="${client.phoneNumber}"/>  </td>
							<td>  <c:out value="${client.email}"/>  </td>
							<td> 
							  <form name="clientdetails" action=" <c:url value="clientdetails.htm"/>" method="post">
                                  <input type="hidden" name="id" value="${client.id}" />
                                  <input type="submit" value="<spring:message code="common.button.details"/>" class = "myButton"/>
                              </form>
							</td>
							<sec:authorize ifAnyGranted="ROLE_ADMIN">
							<td> 
							  <form name="editclient" action=" <c:url value="controller.htm"/>" method="post">
                                  <input type="hidden" name="id" value="${client.id}" />
                                  <input type="submit" value="<spring:message code="common.button.edit"/>" class = "myButton"/>
                              </form>
							</td>
							<td> 
							  <form action=" <c:url value="delete.htm"/>" method="post" onsubmit="return ConfirmDelete();">
                                  <input type="hidden" name="id" value="${client.id}" />
                                  <input type="submit" value="<spring:message code="common.button.delete"/>" class = "myButton"/>
                              </form>
							</td>
							</sec:authorize>
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
				<c:choose>
				<c:when test="${param.order != null && param.field != null}">
			   <form name="firstlink" action=" <c:url value="clients.htm"/>" method="get">
                 <input type="hidden" name="order" value = "${param.order}" />
				 <input type="hidden" name="field" value = "${param.field}" />
				 <input type="hidden" name="currentPage" value = "1" />
                 <input type="submit" value="|<<" class = "myButtonTwo"/>
               </form>
				</c:when>
				<c:otherwise>
			   <form name="firstlink" action="<c:url value="clients.htm"/>" method="get">
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
			   <form name="previouslink" action="<c:url value="clients.htm"/>" method="get">
                 <input type="hidden" name="order" value = "${param.order}" />
				 <input type="hidden" name="field" value = "${param.field}" />
				 <input type="hidden" name="currentPage" value = "${currentPage - 1}" />
                 <input type="submit" value="<" class = "myButtonTwo"/>
               </form>
				</c:when>
				<c:otherwise>
			   <form name="previouslink" action="<c:url value="clients.htm"/>" method="get">
				 <input type="hidden" name="currentPage" value = "${currentPage - 1}" />
                 <input type="submit" value="<" class = "myButtonTwo"/>
               </form>
				</c:otherwise>
				</c:choose>
				</c:if>
			   </td>
 
			<%--Displaying Page numbers--%>
            <td> <c:out value="${currentPage}"/> </td>

				<td>
			<%--Displaying Next link--%>
			   <c:if test="${currentPage < noOfPages}">
				<c:choose>
				<c:when test="${param.order != null && param.field != null}">
			   <form name="nextlink" action="<c:url value="clients.htm"/>" method="get">
                 <input type="hidden" name="order" value = "${param.order}" />
				 <input type="hidden" name="field" value = "${param.field}" />
				 <input type="hidden" name="currentPage" value = "${currentPage + 1}" />
                 <input type="submit" value=">" class = "myButtonTwo"/>
               </form>
				</c:when>
				<c:otherwise>
			   <form name="nextlink" action="<c:url value="clients.htm"/>" method="get">
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
			   <form name="lastlink" action="<c:url value="clients.htm"/>" method="get">
                 <input type="hidden" name="order" value = "${param.order}" />
				 <input type="hidden" name="field" value = "${param.field}" />
				 <input type="hidden" name="currentPage" value = "${noOfPages}" />
                 <input type="submit" value=">>|" class = "myButtonTwo"/>
               </form>
				</c:when>
				<c:otherwise>
			   <form name="lastlink" action="<c:url value="clients.htm"/>" method="get">
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

			   </br>
		 <sec:authorize ifAnyGranted="ROLE_ADMIN">
               <form name="newclient" action="<c:url value="controller.htm"/>" method="post">
                 <input type="hidden" name="id" value = "new" />
                 <input type="submit" value="<spring:message code="clients.button.newclient"/>" class = "myButton"/>
               </form>
		</sec:authorize>
               </br>
			   


</tiles:putAttribute>
</tiles:insertDefinition>
