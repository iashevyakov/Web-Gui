<%@ page import="ru.itis.inform.errors.Err" %><%--
  Created by IntelliJ IDEA.
  User: Иван
  Date: 04.12.2016
  Time: 14:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add a Mark</title>
    <meta charset="UTF-8">
    <link href="bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
    <script src="bootstrap-3.3.7-dist/js/jquery-3.1.1.js"></script>
    <link rel="stylesheet" href="bootstrap-3.3.7-dist/css/style.css" media="screen" type="text/css" />
    <link rel="icon" href="http://vladmaxi.net/favicon.ico" type="image/x-icon">
    <link rel="shortcut icon" href="http://vladmaxi.net/favicon.ico" type="image/x-icon">
    <style type="text/css">
        output.out {
            color: #ffffff;
            font-family: "Comic Sans MS";
            font-size: medium;
        }
        div.auth{
            position: absolute;
            left: 565px;
            bottom: 10px;
        }
    </style>
</head>
<body>

<div id="login" class="auth">
    <form id="1" action="/addMark" method="post">
        <fieldset class="clearfix" >
            <p><span class="glyphicon-pencil"></span><input type="text" name="mark" value="Mark" onBlur="if(this.value == '') this.value = 'Mark'" onFocus="if(this.value == 'Mark') this.value = ''" required></p> <!-- JS because of IE support; better: placeholder="Username" -->
            <p><span class="glyphicon-pencil"></span><input type="text" name="country" value="Country" onBlur="if(this.value == '') this.value = 'Country'" onFocus="if(this.value == 'Country') this.value = ''" required></p> <!-- JS because of IE support; better: placeholder="Username" -->
            <p><span class="glyphicon-pencil"></span><input type="text" name="year" value="Year : YYYY" onBlur="if(this.value == '') this.value = 'Year : YYYY'" onFocus="if(this.value == 'Year : YYYY') this.value = ''" required></p> <!-- JS because of IE support; better: placeholder="Username" -->
            <p><select  class="styled-select" name="petrol" id="petrol">

                <option value="petrol">Petrol</option>

                <option value="diesel">Diesel</option>

            </select>
            </p>
            <p><select  class="styled-select" name="automatic" id="automatic">

                <option value="automatic">Automatical</option>

                <option value="mechanic">Mechanical</option>

            </select>
            </p>
            <p><input type="submit" value="Add"></p>

        </fieldset>
        <%if (!Err.message.equals("")){%>
        <output class="out text-center"><%=Err.message%></output>
        <%Err.message="";}%>
    </form>


</div>
<p><a href="/home" class="btn btn-danger">Back(<%=request.getAttribute("current_user")%>)</a></p>



<script src="http://code.jquery.com/jquery-latest.js"></script>
<script src="bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
</body>
</html>