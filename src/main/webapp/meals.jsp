<%--
  Created by IntelliJ IDEA.
  User: Serg
  Date: 07.06.2021
  Time: 16:43
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html" name="Home">Hame</a> </h3>
<table border="1">
    <tr>
        <th>Date</th>
        <th>Description</th>
        <th>Calories</th>
        <th></th>
        <th></th>
    </tr>
    <c:forEach var="meal" items="${meals}">
        <jsp:useBean id="meal" type="ru.javawebinar.topjava.model.MealTo"/>
        <tr>
            <td>&{mea} </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
