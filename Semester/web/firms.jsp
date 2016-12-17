<%@ page import="java.util.LinkedList" %>
<%@ page import="ru.itis.inform.models.User" %>
<%@ page import="ru.itis.inform.models.DetailsView" %>
<%@ page import="ru.itis.inform.models.Country" %>
<%@ page import="ru.itis.inform.models.Firm" %><%--
  Created by IntelliJ IDEA.
  User: Иван
  Date: 04.12.2016
  Time: 17:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Firms</title>
    <meta charset="UTF-8">
    <link href="bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
    <script src="bootstrap-3.3.7-dist/js/jquery-3.1.1.js"></script>
    <link rel="stylesheet" href="bootstrap-3.3.7-dist/css/style1.css" media="screen" type="text/css" />
    <link rel="icon" href="http://vladmaxi.net/favicon.ico" type="image/x-icon">
    <link rel="shortcut icon" href="http://vladmaxi.net/favicon.ico" type="image/x-icon">
</head>
<body>
<% LinkedList<Firm> firms = (LinkedList) request.getAttribute("firms");%>
<table  cellspacing="0">
    <tr>
        <th>Firm</th>
        <th>Country</th>
        <th>Foundation</th>
        <th>Owner</th>
    </tr>
    <%for (int i = 0; i< firms.size(); i++){%>
    <tr><td><%=firms.get(i).getFirmName()%></td><td><%=firms.get(i).getCountryName()%></td><td><%=firms.get(i).getFoundation().toString()%></td><td><%=firms.get(i).getOwner()%></td></tr>
    <%}%>


</table>

<p><a href="/home" class="btn btn-danger">Back(<%=request.getAttribute("current_user")%>)</a></p>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script src="bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
</body>
</html>