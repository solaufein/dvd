<%@ include file="/WEB-INF/jsp/include.jsp" %>
<!DOCTYPE html>
<head>
    <meta charset="utf-8">
    <title>Dvd Rentals Password Recovery Page</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/remind.css" />"/>
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
    <c:url var="remindUrl" value="/remind"></c:url>
    <form id="remind" name="remindForm" action="${remindUrl}" method="POST">
        <div class="header">
            <h3>@ Dvd Rentals </h3>

            <p> Fill in fields below to recover lost password </p>
        </div>
        <div class="separator"></div>
        <div class="inputs">
            <input type="text" name="email" value="" placeholder="E-mail" autofocus/>

            <input name="submit" type="submit" value="Recover"/>

            <div class="loginpage">
                <a href="${loginUrl}">Back to Login Page</a>
            </div>
        </div>
    </form>
</div>
<script>
    $(document).ready(function () {
        jQuery.validator.setDefaults({
            success: "valid"
        });

        $("#remind").validate({
            rules: {
                email: {
                    required: true,
                    email: true
                }
            }
        });
    });
</script>

</body>
</html>