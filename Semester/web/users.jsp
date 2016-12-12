<%@ page import="java.util.LinkedList" %>
<%@ page import="ru.itis.inform.models.User" %><%--
  Created by IntelliJ IDEA.
  User: Иван
  Date: 04.12.2016
  Time: 17:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users</title>
    <meta charset="UTF-8">
    <link href="bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
    <script src="bootstrap-3.3.7-dist/js/jquery-3.1.1.js"></script>
    <link rel="stylesheet" href="bootstrap-3.3.7-dist/css/style.css" media="screen" type="text/css" />
    <link rel="icon" href="http://vladmaxi.net/favicon.ico" type="image/x-icon">
    <link rel="shortcut icon" href="http://vladmaxi.net/favicon.ico" type="image/x-icon">
</head>
<body>
<% LinkedList<User> list = (LinkedList) request.getAttribute("users");%>
<table class="table-bordered">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Username</th>
        <th>Password</th>
        <th>Profession</th>
    </tr>
    <%String profession;%>
    <%for (int i=0; i<list.size();i++){%>
    <%boolean admin = list.get(i).getIs_admin();
        boolean workman = list.get(i).getIs_workman();
        if(admin){profession = "Admin";}
        else{
            if(workman){profession="Workman";}
            else{profession="WholeSaler";}
        }
    %>
    <tr><td><%=list.get(i).getId()%></td><td><%=list.get(i).getName()%></td><td><%=list.get(i).getLogin()%></td><td><%=list.get(i).getPassword()%></td><td><%=profession%></td></tr>
    <%}%>


</table>


<script src="http://code.jquery.com/jquery-latest.js"></script>
<script src="bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
</body>
</html>
