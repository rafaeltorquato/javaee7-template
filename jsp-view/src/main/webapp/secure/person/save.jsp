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
    <title>Save new person</title>
</head>
<body>
<h1>Save new person</h1>

<a href="/view/secure/person">Back</a>

<c:if test="${not empty errorMessage}">
    <c:out value="${errorMessage}"/>
</c:if>

<c:set var="disabled" value="${not empty person ? 'disabled' : ''}" />

<form action="save" method="post">
    <fieldset>
        <legend>Edit person form</legend>
        <label for="name">Name:</label>
        <input name="name" id="name" type="text" value="${person.name}" ${disabled} /><br/>
        <label for="surname">Surname:</label>
        <input name="surname" id="surname" type="text" value="${person.surname}" ${disabled} /><br/>
        <label for="email">E-mail:</label>
        <input name="email" id="email" type="email" value="${person.email}" ${disabled} /><br/>
        <label for="birthDate">Birth Date:</label>
        <input name="birthDate" id="birthDate" type="date" value="<fmt:formatDate value="${person.birthDate}" pattern="yyyy-MM-dd" />" ${disabled} /><br/>
        <input type="submit" value="Save" ${disabled} />
    </fieldset>
</form>

</body>
</html>
