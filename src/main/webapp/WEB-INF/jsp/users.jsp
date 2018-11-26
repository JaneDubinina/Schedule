<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>

<%@ page isELIgnored = 'false' %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Some Title</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
</head>

<body class="span2">
<form action="/professors" method="POST">
    <input type="submit" value="Show Professors" class="btn btn-default btn-lg">
</form>
<br>
<form action="/groups">
    <input type="submit" value="Show Groups" class="btn btn-default btn-lg">
    <br>
</form>
<br>
<br>
<form action="/addUser" method="POST">
    <p class="text-danger">Here we will add the student(first name, second name)</p>
    <input type="text" name="Login" class="text-input">
    <input type="text" name="Password" class="text-input">
    <input type="submit" value="OK" class="btn btn-default btn-lg">

</form>
<table id="table" class="table table-bordered">
    <thead>
    <tr>
        <th>User </th>
        <th>Password</th>

    </tr>
    </thead>
    <tbody id="tbody"></tbody>
</table>
<script type="text/javascript">

    var xhr = new XMLHttpRequest();
    xhr.open('GET', 'http://localhost:8081/addUser?json', false);
    xhr.send();

    if (xhr.status != 200) {

        alert( xhr.status + ':' + xhr.statusText );
    } else {

        var data = JSON.parse( xhr.responseText);


    }

    var body, tab, tr, td, tn, row, col;

    body = document.getElementById('tbody');

    for (row = 0; row < data.length; row++){

        tr = document.createElement('tr');
        var ob = data[row];
        td = document.createElement('td');
        tn = document.createTextNode(ob.firstName);
        td.appendChild(tn);
        tr.appendChild(td);

        td = document.createElement('td');
        tn = document.createTextNode(ob.lastName);
        td.appendChild(tn);
        tr.appendChild(td);
        body.appendChild(tr);
    }


</script>
</body>
</html>