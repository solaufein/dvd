<%@ include file="/jsp/include.jsp" %>
<%@page import="pl.radek.dvd.model.Client, pl.radek.dvd.model.Constants, java.util.List"%>
<%@page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Add New Client</title>
<link rel="stylesheet" type="text/css" href="jsp/css/main.css" />
<link rel="stylesheet" type="text/css" href="jsp/css/button.css" />
<link rel="stylesheet" type="text/css" href="jsp/css/form.css" />

   <script language="JavaScript" src="jsp/js/gen_validatorv4.js" type="text/javascript" xml:space="preserve">
   </script>
</head>
<body>
<div id="container">
<div id = "content">

<% String contextPath = request.getContextPath(); %>

<c:choose>
<c:when test="${param.id =='new'}">
   <form method="post" action="<%=contextPath%>/addclient.htm">
   <fieldset>
   <legend> Add New Client </legend>
       <div id = "formWrapper">
	       <input type="hidden" name="id" value = "new"/>
		   <label> Firstname:</label>
           <input type="text" name="first_name" value="${param.first_name}" class="inputs"/> <span class="error">${errors.first_name}</span> <br><br>
		   <label> Lastname:</label>
           <input type="text" name="last_name" value="${param.last_name}" class="inputs"/> <span class="error">${errors.last_name}</span> <br><br>
		   <label> Pesel:</label>
           <input type="text" name="pesel" value="${param.pesel}"class="inputs"/> <span class="error">${errors.pesel}</span> <br><br>
		   <label> City:</label>
           <input type="text" name="city" value="${param.city}"class="inputs"/> <span class="error">${errors.city}</span> <br><br>
		   <label> Street:</label>
           <input type="text" name="street" value="${param.street}"class="inputs"/> <span class="error">${errors.street}</span> <br><br>
		   <label> Phone number:</label>
           <input type="text" name="phone_number" value="${param.phone_number}"class="inputs"/> <span class="error">${errors.phone_number}</span> <br><br>
		   <label> Email:</label>
           <input type="text" name="email" value="${param.email}"class="inputs"/> <span class="error">${errors.email}</span> <br><br>
       </div>
           <input type="submit" value="Add" class = "myButton"/>
     </form>
	 
     <form name="cancel" action="<%=contextPath%>/clients.htm" method="get">
           <input type="submit" value="Cancel" class = "myButton"/>
     </form>
</fieldset>
</c:when>
<c:otherwise>
   <form method="post" action="<%=contextPath%>/updateclient.htm">
   <fieldset>
   <legend> Edit Client </legend>
       <div id = "formWrapper">
           <input type="hidden" name="id" value="${client.id}" />
           <label> Firstname:</label>
           <input type="text" name="first_name" value= "${client.firstName}" class="inputs"/> <span class="error">${errors.first_name}</span> <br><br>
           <label> Lastname:</label>
           <input type="text" name="last_name" value= "${client.lastName}" class="inputs"/> <span class="error">${errors.last_name}</span> <br><br>
           <label> Pesel:</label>
           <input type="text" name="pesel" value= "${client.pesel}" class="inputs"/> <span class="error">${errors.pesel}</span> <br><br>
           <label> City:</label>
           <input type="text" name="city" value= "${client.city}" class="inputs"/> <span class="error">${errors.city}</span> <br><br>
           <label> Street:</label>
           <input type="text" name="street" value= "${client.street}" class="inputs"/> <span class="error">${errors.street}</span> <br><br>
           <label> Phone number:</label>
           <input type="text" name="phone_number" value= "${client.phoneNumber}" class="inputs"/> <span class="error">${errors.phone_number}</span> <br><br>
           <label> Email:</label>
           <input type="text" name="email" value= "${client.email}" class="inputs"/> <span class="error">${errors.email}</span> <br><br>
       </div>
           <input type="submit" value="Update" class = "myButton"/>
     </form>

     <form name="cancel" action="<%=contextPath%>/clients.htm" method="get">
           <input type="submit" value="Cancel" class = "myButton"/>
     </form>
</fieldset>
</c:otherwise>
</c:choose>
</div></div>
</body>
</html>