<%--
  Created by IntelliJ IDEA.
  User: Oleg
  Date: 13.10.2018
  Time: 18:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form method="post" action="meals" name="addMeal">
    Meal ID : <input type="text" readonly="readonly" name="id" value="${meal.id}"/><br/>
    Название : <input type="text" name="description" value="${meal.description}" /><br/>
    Время : <input type="datetime-local" name="datetime" value="${meal.dateTime}" /><br/>
    Калории : <input type="text" name="calories" value="${meal.calories}" /><br/>
    <input type="submit" value="Submit"/>
</form>
</body>
</html>