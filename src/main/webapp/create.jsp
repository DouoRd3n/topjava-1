<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Serg
  Date: 07.06.2021
  Time: 17:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<meta charset="utf-8" t/>

    <title>addMeals</title>

</head>
<body>
<h3><a href="index.html">home</a>  </h3>
<hr>
<h2>Edit Meal</h2>
<form method="post">

    <input type="hidden" name="uuid" value="${meal.uuid}">

    <dl>
      Дата : <input type="datetime-local" name="date" size=55 value="${meal.dateTime}">
    </dl>
    <dl>
        Description: <input type="text" name="description" size="55" value="${meal.description}">
    </dl>
    <dl>
        Calories : <input type="number" name="calories" size="30" value="${meal.calories}">
    </dl>

    <p>
        <button type="submit">Отправить</button>
    </p>
</form>

</body>
</html>
