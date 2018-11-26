<%--
  Created by IntelliJ IDEA.
  User: Женя Дубинина
  Date: 19.11.2018
  Time: 10:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

</head>
<body>
<form action="/Login" method="POST" style="align-items: center;margin-left: 40%;margin-top: 20%">
    <p class="text-info"><h3>Input name and password</h3></p>
    <p>Login</p>
    <input type="text" name="firstName">
    <br>
    <br>
    <p>Password</p>

    <input type="text" name="secondName" class="text-input">
    <br>
    <br>
    <input type="submit" value="OK" class="btn btn-default btn-lg">

</form>
</body>
</html>
