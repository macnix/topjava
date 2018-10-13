<%--
  Created by IntelliJ IDEA.
  User: Oleg
  Date: 13.10.2018
  Time: 14:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.List" %>
<%@ page import="ru.javawebinar.topjava.model.MealWithExceed" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<a href="index.html">Back to main menu</a>

<table width="80%" border="1" cellpadding="4" cellspacing="0" bgcolor="#faebd7">
    <div>
        <tr>
            <th>ID</th>
            <th>Название</th>
            <th>Дата</th>
            <th>Калории</th>
            <th colspan=2>Action</th>

        </tr>
    </div>

    <c:forEach items="${mealList}" var="meal">
        <tr style="${meal.exceed ? 'color:red':'color:green'}">
            <td>${meal.id}</td>
            <td>${meal.description}</td>
            <td>${formatter.format(meal.dateTime)}</td>
            <td>${meal.calories}</td>
            <td><a href="meals?action=edit&id=${meal.id}">Update</a></td>
            <td><a href="meals?action=delete&id=${meal.id}">Delete</a></td>
        </tr>
    </c:forEach>

</table>
<p><a href="meals?action=insert">ADD meal</a> </p>
</body>
</html>