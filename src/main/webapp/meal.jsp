


<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<br>
<h2>Meals</h2>

<h3>addMeals</h3>

<table border="1" cellpadding="8" cellspacing="0">
    <tr>
        <th>Date</th>
        <th>Description</th>
        <th>Calories</th>
        <th></th>
        <th></th>
    </tr>



            <c:forEach var="meals" items="${meals}" >

            <jsp:useBean id="meal" type="ru.javawebinar.topjava.model.Meal"/>
                <tr>
                    <td>${meal.date}</td>
                    <td>${meal.description}</td>
                    <td>${meal.calories}</td>
                </tr>

            </c:forEach>

</table>
</body>
</html>
