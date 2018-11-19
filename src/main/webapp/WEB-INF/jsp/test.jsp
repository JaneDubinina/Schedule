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
    <!--<link href="C:\Schedule\src\main\webapp\css\bootstrap.css" rel="stylesheet">-->
</head>

<body class="span2">
<form action="/test" method="POST">
    <p class="text-danger">Here we will add the student(first name, second name)</p>
    <input type="text" name="firstName">
    <input type="text" name="secondName" class="text-input">
    <input type="submit" value="OK" class="btn btn-default btn-lg">
    <p> Here we will add the subject with the teacher(subject, teacher)</p>
    <input type="text" name="Name">
    <input type="text" name="Subject">
    <input type="submit" value="ADD" class="btn btn-default btn-lg">
    <p> Input name and surname of the student you want to delete</p>
    <input type="text" name="NameToDelete">
    <input type="text" name="SubjectToDelete">
    <input type="submit" value="Delete" class="btn btn-default btn-lg">
    <br>
    <p>Mask for students</p>
    <input type="text" name="maskForStudents">
</form>

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
<table>
    <thead>
    <tr>
        <th>First name </th>
        <th>Second name </th>

    </tr>
    </thead>
    <tbody id="tbody"></tbody>
</table>

<table class = "table">
    <thead>
    <tr>
        <th>Professor</th>
        <th>Subject</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${professors}" var="professor">
        <tr>
            <td>${professor.name}</td>
            <td>${professor.subject}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<table class = "table">
    <thead>
    <tr>
        <th>GROUp</th>
        <th>AVG_MARK</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${groups}" var="professor">
        <tr>
            <td>${professor.groupNumber}</td>
            <td>${professor.avhMark}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<table id="books">
</table>
<script type="text/javascript">

    var xhr = new XMLHttpRequest();
    xhr.open('GET', '/Schedule/test?json', false);
    xhr.send();

    if (xhr.status != 200) {
        alert( xhr.status + ': ' + xhr.statusText );
    }
    else
    {
            var data = JSON.parse( xhr.responseText);
    }

    var body, tab, tr, td, tn, row, col;

    body = document.getElementsByTagName('tbody')[0];

    for (row = 0; row < data.length; row++){

    tr = document.createElement('tr');
    var ob = data[row];
    td = document.createElement('td');
    tn = document.createTextNode(ob.firstName);
    td.appendChild(tn);
    tr.appendChild(td);

    td = document.createElement('td');
    tn = document.createTextNode(ob.secondName);
    td.appendChild(tn);
    tr.appendChild(td);
    body.appendChild(tr);
    }

    new Ajax.Request( 'books.xml', {
        method: 'get',
        onSuccess: function( transport ) {
            var bookTags = transport.responseXML.getElementsByTagName( 'book' );

            for( var b = 0; b < bookTags.length; b++ ) {
                var author = bookTags[b].getElementsByTagName('author')[0].firstChild.nodeValue;
                var title = bookTags[b].getElementsByTagName('title')[0].firstChild.nodeValue;
                var publisher =
                    bookTags[b].getElementsByTagName('publisher')[0].firstChild.nodeValue;

                var elTR = $('books').insertRow( -1 );
                var elTD1 = elTR.insertCell( -1 );
                elTD1.innerHTML = author;
                var elTD2 = elTR.insertCell( -1 );
                elTD2.innerHTML = title;
                var elTD3 = elTR.insertCell( -1 );
                elTD3.innerHTML = publisher;
            }
        }
    } );
</script>

</body>
</html>