<%@ page import="ru.itis.inform.models.User" %>
<%@ page import="ru.itis.inform.errors.Err" %><%--
  Created by IntelliJ IDEA.
  User: Иван
  Date: 24.10.2016
  Time: 12:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<head>
    <title>Home</title>
    <meta charset="UTF-8">
    <link href="bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
    <script src="bootstrap-3.3.7-dist/js/jquery-3.1.1.js"></script>
    <link rel="stylesheet" href="bootstrap-3.3.7-dist/css/style.css" media="screen" type="text/css" />
    <link rel="icon" href="http://vladmaxi.net/favicon.ico" type="image/x-icon">
    <link rel="shortcut icon" href="http://vladmaxi.net/favicon.ico" type="image/x-icon">
    <style type="text/css">
        a.btn-primary{
            width: 130px;

        }
        a.btn-info{
            position: absolute;
            left: 50px;
            top: 100px;
            width: 118px;
        }
        table.table-bordered{
            position: absolute;
            left:475px;
        }
        div.auth{
            position: absolute;
            left: 575px;
            bottom: 10px;
        }
        div.divhome {
            position: absolute;
            font-family: "Comic Sans MS";
            bottom: 125px;
            left: 580px;
            color: #ffffff;
            font-size: medium;
        }
        output.out{
            color: #ffffff;
            font-family: "Comic Sans MS";
            font-size:medium;
        }
        input[type="submit"]{
            background-color: #ea4c88;
        }
    </style>

</head>

<br><br>
<%User user = (User) request.getAttribute("current_user");
    if (user.getIs_admin()){
%>

<table class="table table-bordered">

    <tr><td><p><a href="/duCountry" class="btn btn-warning">D/U a Country</a></p></td><td><p><a href="/addCountry" class="btn btn-success">Add a Country</a></p></td><td><p><a href="/countries" class="btn btn-primary">Show Countries</a></p></td></tr>
    <tr><td><p><a href="/duFirm" class="btn btn-warning">Delete a Firm</a></p></td><td><p><a href="/addFirm" class="btn btn-success">Add a Firm</a></p></td><td><p><a href="/firms" class="btn btn-primary">Show Firms</a></p></td></tr>
    <tr><td><p><a href="/duMark" class="btn btn-warning">Delete a Mark</a></p></td><td><p><a href="/addMark" class="btn btn-success">Add a Mark</a></p></td><td><p><a href="/marks" class="btn btn-primary">Show Marks</a></p></td></tr>
    <tr><td><p><a href="/duUnit" class="btn btn-warning">Delete an Unit</a></p></td><td><p><a href="/addUnit" class="btn btn-success">Add an Unit</a></p></td><td><p><a href="/units" class="btn btn-primary">Show Units</a></p></td></tr>
    <tr><td><p><a href="/duMarksUnits" class="btn btn-warning">Delete a MarkUnit</a></p></td><td><p><a href="/addMarksUnits" class="btn btn-success">Add a MarkUnit</a></p></td><td><p><a href="/marksunits" class="btn btn-primary">Show MarkUnit</a></p></td></tr>
    <tr><td><p><a href="/duNode" class="btn btn-warning">Delete a Node</a></p></td><td><p><a href="/addNode" class="btn btn-success">Add a Node</a></p></td><td><p><a href="/nodes" class="btn btn-primary">Show Nodes</a></p></td></tr>
    <tr><td><p><a href="/duDetail" class="btn btn-warning">Delete a Detail</a></p></td><td><p><a href="/addDetail" class="btn btn-success">Add a Detail</a></p></td><td><p><a href="/details" class="btn btn-primary">Show Catalog</a></p></td></tr>
    <tr><td><p><a href="/duUser" class="btn btn-warning">Delete an User</a></p></td><td><p><a href="/registration" class="btn btn-success">Add an User</a></p></td><td><p><a href="/users" class="btn btn-primary">Show Users</a></p></td></tr>
</table>

<p><a href="/logout" class="btn btn-danger">LogOut(<%=request.getAttribute("current_user")%>)</a></p>






<%}
    else {%>
    <%if (user.getIs_workman()){%>
<div id="login" class="auth">
    <form action="/home" method="post" >
        <fieldset class="clearfix">
            <p><span class="glyphicon-pencil"></span><input type="text" name="mark"  value="Mark" onBlur="if(this.value == '') this.value = 'Mark'" onFocus="if(this.value == 'Mark') this.value = ''" required></p>
            <p><span class="glyphicon-pencil"></span><input type="text" name="unit"  value="Unit" onBlur="if(this.value == '') this.value = 'Unit'" onFocus="if(this.value == 'Unit') this.value = ''" required></p>
            <p><span class="glyphicon-pencil"></span><input type="text" name="node"  value="Node" onBlur="if(this.value == '') this.value = 'Node'" onFocus="if(this.value == 'Node') this.value = ''" required></p>
            <p><span class="glyphicon-pencil"></span><input type="text" name="detail"  value="Detail" onBlur="if(this.value == '') this.value = 'Detail'" onFocus="if(this.value == 'Detail') this.value = ''" required></p>
            <p><span class="glyphicon-pencil"></span><input type="text" name="number"  value="Quantity" onBlur="if(this.value == '') this.value = 'Quantity'" onFocus="if(this.value == 'Quantity') this.value = ''" required></p>
            <p><input type="submit" value="SELL"></p>
            <%if (!Err.message.equals("")){%>
            <p><output class="out"><%=Err.message%></output></p>
            <%Err.message="";}%>
        </fieldset>
    </form>
</div>
<a href="/details" class="btn btn-info">Catalog</a>
<center><p><a href="/logout" class="btn btn-danger">LogOut(<%=request.getAttribute("current_user")%>)</a></p></center>
<%}else {%>
<div id="login" class="auth">
    <form action="/home" method="post" >
        <fieldset class="clearfix">

            <p><span class="glyphicon-pencil"></span><input type="text" name="mark"  value="Mark" onBlur="if(this.value == '') this.value = 'Mark'" onFocus="if(this.value == 'Mark') this.value = ''" required></p>
            <p><span class="glyphicon-pencil"></span><input type="text" name="unit"  value="Unit" onBlur="if(this.value == '') this.value = 'Unit'" onFocus="if(this.value == 'Unit') this.value = ''" required></p>
            <p><span class="glyphicon-pencil"></span><input type="text" name="node"  value="Node" onBlur="if(this.value == '') this.value = 'Node'" onFocus="if(this.value == 'Node') this.value = ''" required></p>
            <p><span class="glyphicon-pencil"></span><input type="text" name="detail"  value="Detail" onBlur="if(this.value == '') this.value = 'Detail'" onFocus="if(this.value == 'Detail') this.value = ''" required></p>
            <p><span class="glyphicon-pencil"></span><input type="text" name="number"  value="Quantity" onBlur="if(this.value == '') this.value = 'Quantity'" onFocus="if(this.value == 'Quantity') this.value = ''" required></p>
            <p><input type="submit" value="SEND"></p>
            <%if (!Err.message.equals("")){%>
            <p><output class="out"><%=Err.message%></output></p>
            <%Err.message="";}%>
        </fieldset>
    </form>
</div>
<a href="/details" class="btn btn-info">Catalog</a>
<center><p><a href="/logout" class="btn btn-danger">LogOut(<%=request.getAttribute("current_user")%>)</a></p></center>
<%if (!Err.message.equals("")){%>
<div class="divhome"> <%=Err.message%></div>
<%Err.message="";}%>

    <%}%>
<%}%>



<script src="http://code.jquery.com/jquery-latest.js"></script>
<script src="bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
</body>
</html>
