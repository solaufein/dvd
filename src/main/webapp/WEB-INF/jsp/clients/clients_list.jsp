<%@ include file="/WEB-INF/jsp/include.jsp" %>
<%@page import="pl.radek.dvd.model.Client, pl.radek.dvd.model.Constants, pl.radek.dvd.utils.JspMethods, java.util.List"%>
<%@page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="pl" lang="pl">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-2" />
<title><spring:message code="common.header.title" /></title>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/main.css" />" />
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/button.css" />" />
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/table.css" />" />

          <script>
              function ConfirmDelete()
              {
                var x = confirm("<spring:message code="common.question.deletion"/>");
                if (x)
                    return true;
                else
                  return false;
              }
          </script>
		  <style>
			a.link {
			    color: #FFF;
			    text-decoration: none;
			}

            a.link:hover {
                color: #FFF;
                text-decoration: underline;
            }
	      </style>
</head>
<body>
   <div id="container">
   	<div id="header">
   		<h1>
   			<spring:message code="common.header" />
   		</h1>
   	</div>
   	<div id="login_info">
    		<div class="location">Location: Clients > List</div>
    		<a href="<c:url value="/j_spring_security_logout"/>" class="logout">Logout ${pageContext.request.userPrincipal.name}</a>
    	</div>
   	<div id="navigation">
   		<ul>
   			<li><a href="#"><spring:message code="menu.home" /></a></li>
   			<li><a href="#"><spring:message code="menu.clients" /></a></li>
   			<li><a href="#"><spring:message code="menu.movies" /></a></li>
   			<li><a href="#"><spring:message code="menu.raports" /></a></li>
   			<li><a href="#"><spring:message code="menu.administration" /></a></li>
   		</ul>	
   	</div>
	<!-- Header right begin-->
	<div id="langs">
		<c:url var="englishLocaleUrl" value="/emp/clients.htm">
			<c:param name="lang" value="en" />
		</c:url>
		<c:url var="polishLocaleUrl" value="/emp/clients.htm">
			<c:param name="lang" value="pl" />
		</c:url>	
		<a href="${englishLocaleUrl}">EN</a>
		<a href="${polishLocaleUrl}">PL</a>
	</div>
	<!-- Header right end-->
   	<div id="content">
   		<h2>
   			<spring:message code="clients.clientsList.list"/>:
   		</h2>
		     <%
			 String order = request.getParameter(Constants.ORDER);
			 String field = request.getParameter(Constants.FIELD);
			 String generatedLink;
             %>
	<div class="table">
               <table> 
                 <tr>
                  <td>	
					<% generatedLink = JspMethods.generateLink(order,Constants.FIRSTNAME,field); %>
					<a href="<c:url value="clients.htm"/><%=generatedLink%>" class="link"><spring:message code="clients.clientsList.firstName"/></a>
				  </td>
                  <td>
					<% generatedLink = JspMethods.generateLink(order,Constants.LASTNAME,field); %>
					<a href="<c:url value="clients.htm"/><%=generatedLink%>" class="link"><spring:message code="clients.clientsList.lastName"/></a>
				  </td>
                  <td>
					<% generatedLink = JspMethods.generateLink(order,Constants.PESEL,field); %>
					<a href="<c:url value="clients.htm"/><%=generatedLink%>" class="link"><spring:message code="clients.clientsList.pesel"/></a>
				  </td>
                  <td>
					<% generatedLink = JspMethods.generateLink(order,Constants.CITY,field); %>
					<a href="<c:url value="clients.htm"/><%=generatedLink%>" class="link"><spring:message code="clients.clientsList.city"/></a>
				  </td>
                  <td>
					<% generatedLink = JspMethods.generateLink(order,Constants.STREET,field); %>
					<a href="<c:url value="clients.htm"/><%=generatedLink%>" class="link"><spring:message code="clients.clientsList.street"/></a>
				  </td>
                  <td>
					<% generatedLink = JspMethods.generateLink(order,Constants.PHONENUMBER,field); %>
					<a href="<c:url value="clients.htm"/><%=generatedLink%>" class="link"><spring:message code="clients.clientsList.phoneNumber"/></a>
				  </td>
                  <td>
					<% generatedLink = JspMethods.generateLink(order,Constants.EMAIL,field); %>
					<a href="<c:url value="clients.htm"/><%=generatedLink%>" class="link"><spring:message code="clients.clientsList.email"/></a>
				  </td>
				  <td></td>
				  <td></td>
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
							<td> <form action=" <c:url value="delete.htm"/>" method="post" onsubmit="return ConfirmDelete();">
                                  <input type="hidden" name="id" value="${client.id}" />
                                  <input type="submit" value="<spring:message code="common.button.delete"/>" class = "myButton"/>
                              </form></td>
                         <td> <form name="editclient" action=" <c:url value="controller.htm"/>" method="post">
                                  <input type="hidden" name="id" value="${client.id}" />
                                  <input type="submit" value="<spring:message code="common.button.edit"/>" class = "myButton"/>
                              </form></td>
						</tr>
					</c:forEach>   
               </table>
	</div>
               </br>
			 <div id="page_numbers">
			 <table>  <tr>
			   <td>
	<%--Displaying First link --%>
	<c:choose>
		<c:when test="${currentPage != 1}">
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
		</c:when>
		<c:otherwise>
			<c:choose>
                <c:when test="${param.order != null && param.field != null}">
                    <form name="firstlink" action=" <c:url value="clients.htm"/>" method="get">
                    <input type="hidden" name="order" value = "${param.order}" />
                    <input type="hidden" name="field" value = "${param.field}" />
                    <input type="hidden" name="currentPage" value = "1" />
                    <input type="submit" value="|<<" class = "myButtonDisabled" disabled="disabled"/>
                    </form>
                </c:when>
				<c:otherwise>
                    <form name="firstlink" action="<c:url value="clients.htm"/>" method="get">
                    <input type="hidden" name="currentPage" value = "1" />
                    <input type="submit" value="|<<" class = "myButtonDisabled" disabled="disabled"/>
                    </form>
                </c:otherwise>
             </c:choose>
		</c:otherwise>
	</c:choose>
			   </td>
			   <td>
			<%--Displaying Previous link --%>
			<c:choose>
		<c:when test="${currentPage != 1}">
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
		</c:when>
		<c:otherwise>
			<c:choose>
				<c:when test="${param.order != null && param.field != null}">
			   <form name="previouslink" action="<c:url value="clients.htm"/>" method="get">
                 <input type="hidden" name="order" value = "${param.order}" />
				 <input type="hidden" name="field" value = "${param.field}" />
				 <input type="hidden" name="currentPage" value = "${currentPage - 1}" />
                 <input type="submit" value="<" class = "myButtonDisabled" disabled="disabled"/>
               </form>
				</c:when>
				<c:otherwise>
			   <form name="previouslink" action="<c:url value="clients.htm"/>" method="get">
				 <input type="hidden" name="currentPage" value = "${currentPage - 1}" />
                 <input type="submit" value="<" class = "myButtonDisabled" disabled="disabled"/>
               </form>
				</c:otherwise>
				</c:choose>
		</c:otherwise>
	</c:choose>
			   </td>
 
			<%--Displaying Page numbers--%>
            <td> <c:out value="${currentPage}"/> </td>

				<td>
			<%--Displaying Next link--%>
	<c:choose>
		<c:when test="${currentPage < noOfPages}">
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
		</c:when>
		<c:otherwise>
			<c:choose>
				<c:when test="${param.order != null && param.field != null}">
			   <form name="nextlink" action="<c:url value="clients.htm"/>" method="get">
                 <input type="hidden" name="order" value = "${param.order}" />
				 <input type="hidden" name="field" value = "${param.field}" />
				 <input type="hidden" name="currentPage" value = "${currentPage + 1}" />
                 <input type="submit" value=">" class = "myButtonDisabled" disabled="disabled"/>
               </form>
				</c:when>
				<c:otherwise>
			   <form name="nextlink" action="<c:url value="clients.htm"/>" method="get">
				 <input type="hidden" name="currentPage" value = "${currentPage + 1}" />
                 <input type="submit" value=">" class = "myButtonDisabled" disabled="disabled"/>
               </form>
				</c:otherwise>
				</c:choose>
		</c:otherwise>
	</c:choose>	
			   </td>

			   <td>
			<%--Displaying Last link--%>
			<c:choose>
		<c:when test="${currentPage < noOfPages}">
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
		</c:when>
		<c:otherwise>
			<c:choose>
				<c:when test="${param.order != null && param.field != null}">
			   <form name="lastlink" action="<c:url value="clients.htm"/>" method="get">
                 <input type="hidden" name="order" value = "${param.order}" />
				 <input type="hidden" name="field" value = "${param.field}" />
				 <input type="hidden" name="currentPage" value = "${noOfPages}" />
                 <input type="submit" value=">>|" class = "myButtonDisabled" disabled="disabled"/>
               </form>
				</c:when>
				<c:otherwise>
			   <form name="lastlink" action="<c:url value="clients.htm"/>" method="get">
				 <input type="hidden" name="currentPage" value = "${noOfPages}" />
                 <input type="submit" value=">>|" class = "myButtonDisabled" disabled="disabled"/>
               </form>
				</c:otherwise>
				</c:choose>
		</c:otherwise>
	</c:choose>
			
			   </td>
			   </tr>
			   </table>
			   </div>

			   </br>

               <form name="newclient" action="<c:url value="controller.htm"/>" method="post">
                 <input type="hidden" name="id" value = "new" />
                 <input type="submit" value="<spring:message code="clients.button.newclient"/>" class = "myButton"/>
               </form>

               </br>



   	</div>
   	<div id="footer">
   		<spring:message code="common.footer"/>
   	</div>
   </div>
</body>
</html>
