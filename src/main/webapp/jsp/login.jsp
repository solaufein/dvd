<%@ include file="/jsp/include.jsp" %>

<html>
<head>
<title>Login Page</title>
</head>
<body>
    <h3>Login with Username and Password</h3>
    <c:url var="loginUrl" value="/j_spring_security_check"></c:url>
    <form action="${loginUrl}" method="POST">
        <table>
            <tr>
                <td>Login:</td>
                <td><input type='text' name='j_username' /></td>
            </tr>
            <tr>
                <td>Password:</td>
                <td><input type='password' name='j_password' /></td>
            </tr>
            <tr>
                <td colspan='2'><input name="submit" type="submit"
                    value="Log in" /></td>
            </tr>
        </table>
    </form>
</body>
</html>