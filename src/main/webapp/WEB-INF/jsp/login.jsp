<%@ include file="/WEB-INF/jsp/include.jsp" %>
<!DOCTYPE html>
<head>
  <meta charset="utf-8">
  <title>Dvd Rentals Login Page</title>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/login.css" />" />
</head>
<body>
  <div class="container">

        <c:if test="${not empty error}">
  			<div class="error">${error}</div>
  		</c:if>
  		<c:if test="${not empty msg}">
  			<div class="msg">${msg}</div>
  		</c:if>

    <c:url var="loginUrl" value="/j_spring_security_check"></c:url>
	<form id="login" name="loginForm" action="${loginUrl}" method="POST">
		<div class="header">
		<h3>@ Dvd Rentals </h3>
		<p> Fill in fields below to login </p>
		</div>
		<div class="separator"></div>
		<div class="inputs">
			<input type="text" name="j_username" value="" placeholder="Username" autofocus/>
			<input type="password" name="j_password" placeholder="Password"/>
			<!--div class="remember">
				<input name="checky" id="checky" value="1" type="checkbox"/>
				<label class="terms"> Remember me for 1day </label>
			</div-->
			<input name="submit" type="submit" value="Log in" />
		</div>
	</form>
  </div>
</body>
</html>