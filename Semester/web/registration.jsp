<%@ page import="ru.itis.inform.errors.Err" %><%--
  Created by IntelliJ IDEA.
  User: Иван
  Date: 04.12.2016
  Time: 16:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>

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
<div id="login">
    <form id="1" action="/registration" method="post">
        <fieldset class="clearfix" >
            <p><span class="fontawesome-user"></span><input type="text" name="name" value="Name" onBlur="if(this.value == '') this.value = 'Name'" onFocus="if(this.value == 'Name') this.value = ''" required></p> <!-- JS because of IE support; better: placeholder="Username" -->
            <p><span class="fontawesome-user"></span><input type="text" name="username"  value="Username" onBlur="if(this.value == '') this.value = 'Username'" onFocus="if(this.value == 'Username') this.value = ''" required></p> <!-- JS because of IE support; better: placeholder="Password" -->
            <p><span class="fontawesome-lock"></span><input type="text" name="password"  value="Password" onBlur="if(this.value == '') this.value = 'Password'" onFocus="if(this.value == 'Password') this.value = ''" required></p>
            <p><select  class="styled-select" name="work" id="work">

                <option value="workman">Рабочий</option>

                <option value="wholesaler">Оптовик</option>

            </select>
            </p>
            <p><input type="submit" value="Зарегистрировать"></p>
            <%if (!Err.message.equals("")){%>
            <p><output class="out"><%=Err.message%></output></p>
            <%Err.message="";}%>
        </fieldset>
    </form>

    <p><a href="/home" class="btn btn-danger">Back(<%=request.getAttribute("current_user")%>)</a></p>
</div>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script src="bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
</body>
</html>
