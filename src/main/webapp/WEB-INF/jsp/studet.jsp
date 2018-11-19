<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>

<%@ page isELIgnored = 'false' %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Some Title</title>
    <!--<style type="text/css">
        th{
            min-width: 150px;
        }
        table{
            border-color: black;
            border-width: 3px;
        }
    </style>-->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <!--<link href="C:\Schedule\src\main\webapp\css\bootstrap.css" rel="stylesheet">-->
</head>

<body>


<table class="table">
    <thead>
    <tr>
        <th>First name</th>
        <th>Second name</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${students}" var="student">
        <tr>
            <td>${student.firstName}</td>
            <td>${student.secondName}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>


</body>
</html>