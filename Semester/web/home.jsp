<%@ page import="ru.itis.inform.models.User" %>
<%@ page import="ru.itis.inform.errors.Err" %><%--
  Created by IntelliJ IDEA.
  User: Иван
  Date: 24.10.2016
  Time: 12:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
    <meta charset="UTF-8">
    <link href="bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
    <script src="bootstrap-3.3.7-dist/js/jquery-3.1.1.js"></script>
    <link rel="stylesheet" href="bootstrap-3.3.7-dist/css/style.css" media="screen" type="text/css" />
    <link rel="icon" href="http://vladmaxi.net/favicon.ico" type="image/x-icon">
    <link rel="shortcut icon" href="http://vladmaxi.net/favicon.ico" type="image/x-icon">
</head>
<body>
<br><br>
<%User user = (User) request.getAttribute("current_user");
    if (user.getIs_admin()){
%>

<table class="table table-bordered">

    <tr><td><p><a href="/duCountry" class="btn btn-warning">D/U a Country</a></p></td><td><p><a href="/addCountry" class="btn btn-success">Add a Country</a></p></td></tr>
    <tr><td><p><a href="/duFirm" class="btn btn-warning">D/U a Firm</a></p></td><td><p><a href="/addFirm" class="btn btn-success">Add a Firm</a></p></td></tr>
    <tr><td><p><a href="/duMark" class="btn btn-warning">D/U a Mark</a></p></td><td><p><a href="/addMark" class="btn btn-success">Add a Mark</a></p></td></tr>
    <tr><td><p><a href="/duUnit" class="btn btn-warning">D/U an Unit</a></p></td><td><p><a href="/addUnit" class="btn btn-success">Add an Unit</a></p></td></tr>
    <tr><td><p><a href="/duNode" class="btn btn-warning">D/U a Node</a></p></td><td><p><a href="/addNode" class="btn btn-success">Add a Node</a></p></td></tr>
    <tr><td><p><a href="/duDetail" class="btn btn-warning">D/U a Detail</a></p></td><td><p><a href="/addDetail" class="btn btn-success">Add a Detail</a></p></td></tr>
    <tr><td><p><a href="/users" class="btn btn-warning">Show Users</a></p></td><td><p><a href="/registration" class="btn btn-success">Create an User</a></p></td></tr>
</table>

<p><a href="/logout" class="btn btn-danger">LogOut(<%=request.getAttribute("current_user")%>)</a></p>






<%}
    else {%>
    <%if (user.getIs_workman()){%>
<div id="login">
    <form action="/home" method="post" >
        <fieldset class="clearfix">
            <p><span class="glyphicon-pencil"></span><input type="text" name="mark"  value="Mark" onBlur="if(this.value == '') this.value = 'Mark'" onFocus="if(this.value == 'Mark') this.value = ''" required></p>
            <p><span class="glyphicon-pencil"></span><input type="text" name="unit"  value="Unit" onBlur="if(this.value == '') this.value = 'Unit'" onFocus="if(this.value == 'Unit') this.value = ''" required></p>
            <p><span class="glyphicon-pencil"></span><input type="text" name="node"  value="Node" onBlur="if(this.value == '') this.value = 'Node'" onFocus="if(this.value == 'Node') this.value = ''" required></p>
            <p><span class="glyphicon-pencil"></span><input type="text" name="detail"  value="Detail" onBlur="if(this.value == '') this.value = 'Detail'" onFocus="if(this.value == 'Detail') this.value = ''" required></p>
            <p><span class="glyphicon-pencil"></span><input type="text" name="number"  value="Quantity" onBlur="if(this.value == '') this.value = 'Quantity'" onFocus="if(this.value == 'Quantity') this.value = ''" required></p>
            <p><input type="submit" value="SELL"></p>
        </fieldset>
    </form>
</div>
<%if (!Err.message.equals("")){%>
<div class="div3"><%=Err.message%></div>
<%Err.message="";}%>



<center><p><a href="/logout" class="btn btn-danger">LogOut(<%=request.getAttribute("current_user")%>)</a></p></center>
<%}else {%>
<div id="login">
    <form action="/home" method="post" >
        <fieldset class="clearfix">

            <p><span class="glyphicon-pencil"></span><input type="text" name="mark"  value="Mark" onBlur="if(this.value == '') this.value = 'Mark'" onFocus="if(this.value == 'Mark') this.value = ''" required></p>
            <p><span class="glyphicon-pencil"></span><input type="text" name="unit"  value="Unit" onBlur="if(this.value == '') this.value = 'Unit'" onFocus="if(this.value == 'Unit') this.value = ''" required></p>
            <p><span class="glyphicon-pencil"></span><input type="text" name="node"  value="Node" onBlur="if(this.value == '') this.value = 'Node'" onFocus="if(this.value == 'Node') this.value = ''" required></p>
            <p><span class="glyphicon-pencil"></span><input type="text" name="detail"  value="Detail" onBlur="if(this.value == '') this.value = 'Detail'" onFocus="if(this.value == 'Detail') this.value = ''" required></p>
            <p><span class="glyphicon-pencil"></span><input type="text" name="number"  value="Quantity" onBlur="if(this.value == '') this.value = 'Quantity'" onFocus="if(this.value == 'Quantity') this.value = ''" required></p>
            <p><input type="submit" value="SEND"></p>
        </fieldset>
    </form>
</div>

<center><p><a href="/logout" class="btn btn-danger">LogOut(<%=request.getAttribute("current_user")%>)</a></p></center>
<%if (!Err.message.equals("")){%>
<div class="div1"> <%=Err.message%></div>
<%Err.message="";}%>

    <%}%>
<%}%>



<script src="http://code.jquery.com/jquery-latest.js"></script>
<script src="bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
</body>
</html>
