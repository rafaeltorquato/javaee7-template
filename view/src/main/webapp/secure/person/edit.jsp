<%--
  Created by IntelliJ IDEA.
  User: torquato
  Date: 12/22/20
  Time: 8:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Edit Person</title>
</head>
<body>
<h1>Editing ${person.name}&nbsp;${person.surname}</h1>

<a href="/view/secure/person">Back</a>

<c:if test="${not empty errorMessage}">
    <c:out value="${errorMessage}"/>
</c:if>

<form action="edit?id=${person.id}" method="post">
    <fieldset>
        <legend>Edit person form</legend>
        <label for="name">Name:</label>
        <input name="name" id="name" type="text" value="${person.name}" /><br/>
        <label for="surname">Surname:</label>
        <input name="surname" id="surname" type="text" value="${person.surname}" /><br/>
        <label for="email">E-mail:</label>
        <input name="email" id="email" type="email" value="${person.email}" /><br/>
        <label for="birthDate">Birth Date:</label>
        <input name="birthDate" id="birthDate" type="date" value="<fmt:formatDate value="${person.birthDate}" pattern="yyyy-MM-dd" />"/><br/>
        <input type="hidden" name="id" value="${person.id}" />
        <input type="hidden" name="version" value="${person.version}" />
        <input type="submit" value="Edit"/>
    </fieldset>
</form>
<!--TODO Phone list and save/delete phone-->
<c:choose>
    <c:when test="${not empty person.phones}">
        <table>
            <thead>
            <tr>
                <th></th>
                <th></th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${person.phones}" var="phone">
                <tr>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:when>
    <c:otherwise>
        No phones registered
    </c:otherwise>
</c:choose>

<!--TODO Address list and add/remove address-->


<!--TODO Relationship list and save/remove relationship-->

</body>
</html>
