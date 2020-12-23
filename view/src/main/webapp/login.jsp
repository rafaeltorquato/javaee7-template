<%--
  Created by IntelliJ IDEA.
  User: torquato
  Date: 12/20/20
  Time: 7:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Authentication</title>
</head>
<body>
<h1>Authentication form</h1>
<form method="post" action="j_security_check">
    <fieldset>
        <label for="username">Username:</label>
        <input id="username" name="j_username" type="text" />
        <label for="password">Password:</label>
        <input id="password" name="j_password" type="password" />
    </fieldset>
    <input type="submit" value="Enter">&nbsp;
    <input type="reset" value="Reset">
</form>
</body>
</html>
