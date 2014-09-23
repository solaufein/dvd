<%@ include file="/WEB-INF/jsp/include.jsp" %>
<!DOCTYPE html>
<head>
  <meta charset="utf-8">
  <title>Dvd Rentals Password Change Page</title>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/change.css" />" />
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script src="http://jqueryvalidation.org/files/dist/jquery.validate.min.js"></script>
<script src="http://jqueryvalidation.org/files/dist/additional-methods.min.js"></script>
</head>
<body>
  <div class="container">

  		<c:if test="${not empty msg}">
  			<div class="msg">${msg}</div>
  		</c:if>

    <c:url var="loginUrl" value="/login"></c:url>
    <c:url var="changeURL" value="/change"></c:url>
	<form id="change" name="remindForm" action="${changeURL}" method="POST">
		<div class="header">
		<h3>@ Dvd Rentals </h3>
		<p> Enter the new password twice </p>
		</div>
		<div class="separator"></div>
		<div class="inputs">
			<input type="password" name="pw" value="" placeholder="New Password" autofocus/>
			<input type="password" name="repw" value="" placeholder="Repeat New Password"/>
			
			<input type="submit" name="submit" value="Change" />

			<div class="loginpage">
                <a href="${loginUrl}">Back to Login Page</a>
            </div>
		</div>
	</form>
  </div>

</body>
</html>