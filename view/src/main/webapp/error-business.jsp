<%--
  Created by IntelliJ IDEA.
  User: torquato
  Date: 12/22/20
  Time: 11:30 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>A business error occurred</title>
</head>
<body>
<h1>A business error occurred</h1>
<c:set var="exception" value="${requestScope['javax.servlet.error.exception']}"/>
<c:out value="${exception.businessExceptionClassName}" />
</body>
</html>