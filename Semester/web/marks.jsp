<%@ page import="java.util.LinkedList" %>
<%@ page import="ru.itis.inform.models.*" %><%--
  Created by IntelliJ IDEA.
  User: Иван
  Date: 04.12.2016
  Time: 17:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Marks</title>
    <meta charset="UTF-8">
    <link href="bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
    <script src="bootstrap-3.3.7-dist/js/jquery-3.1.1.js"></script>
    <link rel="stylesheet" href="bootstrap-3.3.7-dist/css/style1.css" media="screen" type="text/css" />
    <link rel="icon" href="http://vladmaxi.net/favicon.ico" type="image/x-icon">
    <link rel="shortcut icon" href="http://vladmaxi.net/favicon.ico" type="image/x-icon">
</head>
<body>
<% LinkedList<Mark> marks = (LinkedList) request.getAttribute("marks");%>
<table  cellspacing="0">
    <tr>
        <th>Mark</th>
        <th>Country</th>
        <th>Year</th>
        <th>Dvigatel</th>
        <th>Transmission</th>
    </tr>
    <%String korobka;String toplivo;%>
    <%for (int i = 0; i< marks.size(); i++){
    if(marks.get(i).isautomatic()){
        korobka = "Automatical";
    }
    else{
        korobka = "Mechanical";
    }
    if (marks.get(i).isonpetrol()){
        toplivo="Petrol";
    }
    else{
        toplivo="Diesel";
    }

    %>
    <tr><td><%=marks.get(i).getMarkName()%></td><td><%=marks.get(i).getCountry()%></td><td><%=marks.get(i).getYear()%></td><td><%=toplivo%></td><td><%=korobka%></td></tr>
    <%}%>


</table>

<p><a href="/home" class="btn btn-danger">Back(<%=request.getAttribute("current_user")%>)</a></p>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script src="bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
</body>
</html>