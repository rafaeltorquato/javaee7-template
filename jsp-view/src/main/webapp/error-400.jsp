<%--
  Created by IntelliJ IDEA.
  User: torquato
  Date: 12/23/20
  Time: 4:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>An business error occurred</title>
</head>
<body>
    <h1>An business error occurred</h1>
</body>
<c:set var="exception" value="${requestScope['javax.servlet.error.exception']}" />
<jsp:scriptlet>
  exception.printStackTrace(new java.io.PrintWriter(out));
</jsp:scriptlet>
</html>
