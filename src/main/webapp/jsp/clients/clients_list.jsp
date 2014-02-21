<%@page import="pl.radek.dvd.model.Client, pl.radek.dvd.model.Constants, pl.radek.dvd.utils.JspMethods, java.util.List"%>
<%@page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Clients</title>
<link rel="stylesheet" type="text/css" href="jsp/css/main.css" />
<link rel="stylesheet" type="text/css" href="jsp/css/button.css" />
<link rel="stylesheet" type="text/css" href="jsp/css/table.css" />

          <script>
              function ConfirmDelete()
              {
                var x = confirm("Are you sure you want to delete?");
                if (x)
                    return true;
                else
                  return false;
              }
          </script>
		  
		  <style>
   
			a.link {
			color:white;
			}
   
	      </style>

</head>

<body>


   <div id="container">
   	<div id="header">
   		<h1>
   			Dvd <b>R</b>entals
   		</h1>
   	</div>
   	<div id="navigation">
   		<ul>
   			<li><a href="#">Home</a></li>
   			<li><a href="#">Clients</a></li>
   			<li><a href="#">Movies</a></li>
   			<li><a href="#">Raports</a></li>
   			<li><a href="#">Administration</a></li>
   		</ul>
   	</div>
   	<div id="content">
   		<h2>
   			Clients list:
   		</h2>
		     <%
             List<Client> list = (List<Client>) request.getAttribute(Constants.CLIENTLIST);
			 String order = request.getParameter(Constants.ORDER);
			 String field = request.getParameter(Constants.FIELD);
			 String contextPath = request.getContextPath();
			 String generatedLink;
             %>
	<div class="table">
               <table> 
                 <tr>
                  <td>	
					<% generatedLink = JspMethods.generateLink("clients",order,Constants.FIRSTNAME,field); %>
					<a href="<%=contextPath%>/<%=generatedLink%>" class="link">Firstname</a>
				  </td>
                  <td>
					<% generatedLink = JspMethods.generateLink("clients",order,Constants.LASTNAME,field); %>
					<a href="<%=contextPath%>/<%=generatedLink%>" class="link">Lastname</a>
				  </td>
                  <td>
					<% generatedLink = JspMethods.generateLink("clients",order,Constants.PESEL,field); %>
					<a href="<%=contextPath%>/<%=generatedLink%>" class="link">Pesel</a>
				  </td>
                  <td>
					<% generatedLink = JspMethods.generateLink("clients",order,Constants.CITY,field); %>
					<a href="<%=contextPath%>/<%=generatedLink%>" class="link">City</a>
				  </td>
                  <td>
					<% generatedLink = JspMethods.generateLink("clients",order,Constants.STREET,field); %>
					<a href="<%=contextPath%>/<%=generatedLink%>" class="link">Street</a>
				  </td>
                  <td>
					<% generatedLink = JspMethods.generateLink("clients",order,Constants.PHONENUMBER,field); %>
					<a href="<%=contextPath%>/<%=generatedLink%>" class="link">Phone Number</a>
				  </td>
                  <td>
					<% generatedLink = JspMethods.generateLink("clients",order,Constants.EMAIL,field); %>
					<a href="<%=contextPath%>/<%=generatedLink%>" class="link">Email</a>
				  </td>
				  <td></td>
				  <td></td>
                 </tr>
            
                 <% for(Client c : list) { %>
                       <tr>
                         <td>  <%   out.println(c.getFirstName());     %>  </td>
                         <td>  <%   out.println(c.getLastName());      %>  </td>
                         <td>  <%   out.println(c.getPesel());         %>  </td>
                         <td>  <%   out.println(c.getCity());          %>  </td>
                         <td>  <%   out.println(c.getStreet());        %>  </td>
                         <td>  <%   out.println(c.getPhoneNumber());   %>  </td>
                         <td>  <%   out.println(c.getEmail());         %>  </td>
                         <td> <form action="<%=contextPath%>/delete" method="post" onsubmit="return ConfirmDelete();">
                                  <input type="hidden" name="id" value="<%= c.getId() %>" />
                                  <input type="submit" value="Delete" class = "myButton"/>
                              </form></td>
                         <td> <form name="editclient" action="<%=contextPath%>/controller" method="post">
                                  <input type="hidden" name="id" value="<%= c.getId() %>" />
                                  <input type="submit" value="Edit" class = "myButton"/>
                              </form></td>
                       </tr>
                 <% } %>
               </table>
	</div>
               </br>
			   
			 <table>  <tr>
			   <td>
			<%--Displaying First link except for the 1st page--%>   
			   <% if (Integer.parseInt(request.getAttribute(Constants.CURRENTPAGE).toString()) != 1) { %>
			   <% if (request.getParameter(Constants.ORDER) != null && request.getParameter(Constants.FIELD) != null){ %>
			   <form name="firstlink" action="<%=contextPath%>/clients" method="get">
                 <input type="hidden" name="order" value = "<%=order%>" />
				 <input type="hidden" name="field" value = "<%=field%>" />
				 <input type="hidden" name="currentPage" value = "1" />
                 <input type="submit" value="|<<" class = "myButtonTwo"/>
               </form>
			   <%} else {%>
			   <form name="firstlink" action="<%=contextPath%>/clients" method="get">
				 <input type="hidden" name="currentPage" value = "1" />
                 <input type="submit" value="|<<" class = "myButtonTwo"/>
               </form>
			   <% } %>
			   <% } %>
			   </td>
			   
			   <td>
			<%--Displaying Previous link except for the 1st page--%>
			<% if (Integer.parseInt(request.getAttribute(Constants.CURRENTPAGE).toString()) != 1) { %>
			   <% if (request.getParameter(Constants.ORDER) != null && request.getParameter(Constants.FIELD) != null){ %>
			   <form name="previouslink" action="<%=contextPath%>/clients" method="get">
                 <input type="hidden" name="order" value = "<%=order%>" />
				 <input type="hidden" name="field" value = "<%=field%>" />
				 <input type="hidden" name="currentPage" value = "${currentPage - 1}" />
                 <input type="submit" value="<" class = "myButtonTwo"/>
               </form>
			   <%} else {%>
			   <form name="previouslink" action="<%=contextPath%>/clients" method="get">
				 <input type="hidden" name="currentPage" value = "${currentPage - 1}" />
                 <input type="submit" value="<" class = "myButtonTwo"/>
               </form>
			   <% } %>
			   <% } %>
			   </td>
 
			<%--Displaying Page numbers--%>
            <td>  <%   out.println(request.getAttribute("currentPage"));   %>  </td>

				<td>
			<%--Displaying Next link--%>
			<% if (Integer.parseInt(request.getAttribute(Constants.CURRENTPAGE).toString()) < Integer.parseInt(request.getAttribute(Constants.NO_OF_PAGES).toString())) { %>
			   <% if (request.getParameter(Constants.ORDER) != null && request.getParameter(Constants.FIELD) != null){ %>
			   <form name="nextlink" action="<%=contextPath%>/clients" method="get">
                 <input type="hidden" name="order" value = "<%=order%>" />
				 <input type="hidden" name="field" value = "<%=field%>" />
				 <input type="hidden" name="currentPage" value = "${currentPage + 1}" />
                 <input type="submit" value=">" class = "myButtonTwo"/>
               </form>
			   <%} else {%>
			   <form name="nextlink" action="<%=contextPath%>/clients" method="get">
				 <input type="hidden" name="currentPage" value = "${currentPage + 1}" />
                 <input type="submit" value=">" class = "myButtonTwo"/>
               </form>
			   <% } %>
			   <% } %>
			   </td>

			   <td>
			<%--Displaying Last link--%>
			<% if (Integer.parseInt(request.getAttribute(Constants.CURRENTPAGE).toString()) < Integer.parseInt(request.getAttribute(Constants.NO_OF_PAGES).toString())) { %>
			   <% if (request.getParameter(Constants.ORDER) != null && request.getParameter(Constants.FIELD) != null){ %>
			   <form name="lastlink" action="<%=contextPath%>/clients" method="get">
                 <input type="hidden" name="order" value = "<%=order%>" />
				 <input type="hidden" name="field" value = "<%=field%>" />
				 <input type="hidden" name="currentPage" value = "${noOfPages}" />
                 <input type="submit" value=">>|" class = "myButtonTwo"/>
               </form>
			   <%} else {%>
			   <form name="lastlink" action="<%=contextPath%>/clients" method="get">
				 <input type="hidden" name="currentPage" value = "${noOfPages}" />
                 <input type="submit" value=">>|" class = "myButtonTwo"/>
               </form>
			   <% } %>
			   <% } %>
			   </td>
			   </tr>
			   </table>
			   
			   </br>

               <form name="newclient" action="<%=contextPath%>/controller" method="post">
                 <input type="hidden" name="id" value = "new" />
                 <input type="submit" value="New Client" class = "myButton"/>
               </form>

   	</div>
   	<div id="footer">
   		Copyright © Dvd Rentals, 2014
   	</div>
   </div>
</body>
</html>
