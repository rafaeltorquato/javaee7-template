<%--
  Created by IntelliJ IDEA.
  User: torquato
  Date: 12/22/20
  Time: 5:14 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Person list</title>

    <style>
        .demo {
            border: 1px solid #C0C0C0;
            border-collapse: collapse;
            padding: 5px;
        }

        .demo th {
            border: 1px solid #C0C0C0;
            padding: 5px;
            background: #F0F0F0;
        }

        .demo td {
            border: 1px solid #C0C0C0;
            padding: 5px;
        }
    </style>
</head>
<body>
<h1>Person List </h1>
<a href="person/save">New Person</a>
<br />
<c:choose>
    <c:when test="${not empty list}">
        <table border="1" class="demo">
            <thead>
            <tr>
                <th>id</th>
                <th>Name</th>
                <th>Email</th>
                <th>Birthdate</th>
                <th>Delete</th>
                <th>Edit</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${list}" var="person">
                <tr>
                    <td>${person.id}</td>
                    <td>${person.name}</td>
                    <td>${person.email}</td>
                    <td>
                        <fmt:formatDate pattern="yyyy-MM-dd"
                                        value="${person.birthDate}"/>
                    </td>
                    <td><a href="person/delete?id=${person.id}">Delete</a></td>
                    <td><a href="person/edit?id=${person.id}">Edit</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:when>
    <c:otherwise>
        No one registered.
    </c:otherwise>
</c:choose>

</body>
</html>
