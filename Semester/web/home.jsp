<%@ page import="ru.itis.inform.models.User" %><%--
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
<h1>Welcome, <%=request.getAttribute("current_user")%> !</h1>
<br><br>
<%User user = (User) request.getAttribute("current_user");
    if (user.getIs_admin()){
%>

<div id="login">
    <form id="1" action="/home" method="post">
        <fieldset class="clearfix" >
            <p><span class="fontawesome-user"></span><input type="text" name="name" value="Имя" onBlur="if(this.value == '') this.value = 'Имя'" onFocus="if(this.value == 'Имя') this.value = ''" required></p> <!-- JS because of IE support; better: placeholder="Username" -->
            <p><span class="fontawesome-user"></span><input type="text" name="username"  value="Имя пользователя" onBlur="if(this.value == '') this.value = 'Имя пользователя'" onFocus="if(this.value == 'Имя пользователя') this.value = ''" required></p> <!-- JS because of IE support; better: placeholder="Password" -->
            <p><span class="fontawesome-lock"></span><input type="text" name="password"  value="Пароль" onBlur="if(this.value == '') this.value = 'Пароль'" onFocus="if(this.value == 'Пароль') this.value = ''" required></p>
            <p><span class="fontawesome-lock"></span><input type="text" name="password2"  value="Повторите пароль" onBlur="if(this.value == '') this.value = 'Повторите пароль'" onFocus="if(this.value == 'Повторите пароль') this.value = ''" required></p>
            <p><select  class="styled-select" name="work" id="work">

                <option value="workman">Рабочий</option>

                <option value="wholesaler">Оптовик</option>

            </select>
            </p>
            <p><input type="submit" value="Зарегистрировать"></p>
        </fieldset>
    </form>

</div>


<center><p><a href="/logout" class="btn btn-danger">LogOut(<%=request.getAttribute("current_user")%>)</a></p></center>
<%if (request.getAttribute("success")!=null){%>
<div class="div2"><%=request.getAttribute("success")%></div>
<%}%>
<%if (request.getAttribute("fail")!=null){%>
<div class="div2"><%=request.getAttribute("fail")%></div>
<%}%>
<%}
    else {%>
    <%if (user.getIs_workman()){%>
<div id="login">
    <form action="/home" method="post" >
        <fieldset class="clearfix">
            <p><span class="glyphicon-pencil"></span><input type="text" name="country" value="Страна" onBlur="if(this.value == '') this.value = 'Страна'" onFocus="if(this.value == 'Страна') this.value = ''" required></p> <!-- JS because of IE support; better: placeholder="Username" -->
            <p><span class="glyphicon-pencil"></span><input type="text" name="firm"  value="Фирма" onBlur="if(this.value == '') this.value = 'Фирма'" onFocus="if(this.value == 'Фирма') this.value = ''" required></p> <!-- JS because of IE support; better: placeholder="Password" -->
            <p><span class="glyphicon-pencil"></span><input type="text" name="mark"  value="Марка" onBlur="if(this.value == '') this.value = 'Марка'" onFocus="if(this.value == 'Марка') this.value = ''" required></p>
            <p><span class="glyphicon-pencil"></span><input type="text" name="unit"  value="Агрегат" onBlur="if(this.value == '') this.value = 'Агрегат'" onFocus="if(this.value == 'Агрегат') this.value = ''" required></p>
            <p><span class="glyphicon-pencil"></span><input type="text" name="node"  value="Узел" onBlur="if(this.value == '') this.value = 'Узел'" onFocus="if(this.value == 'Узел') this.value = ''" required></p>
            <p><span class="glyphicon-pencil"></span><input type="text" name="detail"  value="Деталь" onBlur="if(this.value == '') this.value = 'Деталь'" onFocus="if(this.value == 'Деталь') this.value = ''" required></p>
            <p><span class="glyphicon-pencil"></span><input type="text" name="number"  value="Количество" onBlur="if(this.value == '') this.value = 'Количество'" onFocus="if(this.value == 'Количество') this.value = ''" required></p>
            <p><input type="submit" value="Продать"></p>
        </fieldset>
    </form>
</div>
<%if (request.getAttribute("success")!=null){%>
<div class="div3"><%=request.getAttribute("success")%></div>
<%}%>
<%if (request.getAttribute("fail")!=null){%>
<div class="div3"><%=request.getAttribute("fail")%></div>
<%}%>


<center><p><a href="/logout" class="btn btn-danger">LogOut(<%=request.getAttribute("current_user")%>)</a></p></center>
<%}else {%>
<div id="login">
    <form action="/home" method="post" >
        <fieldset class="clearfix">
            <p><span class="glyphicon-pencil"></span><input type="text" name="country" value="Страна" onBlur="if(this.value == '') this.value = 'Страна'" onFocus="if(this.value == 'Страна') this.value = ''" required></p> <!-- JS because of IE support; better: placeholder="Username" -->
            <p><span class="glyphicon-pencil"></span><input type="text" name="firm"  value="Фирма" onBlur="if(this.value == '') this.value = 'Фирма'" onFocus="if(this.value == 'Фирма') this.value = ''" required></p> <!-- JS because of IE support; better: placeholder="Password" -->
            <p><span class="glyphicon-pencil"></span><input type="text" name="mark"  value="Марка" onBlur="if(this.value == '') this.value = 'Марка'" onFocus="if(this.value == 'Марка') this.value = ''" required></p>
            <p><span class="glyphicon-pencil"></span><input type="text" name="unit"  value="Агрегат" onBlur="if(this.value == '') this.value = 'Агрегат'" onFocus="if(this.value == 'Агрегат') this.value = ''" required></p>
            <p><span class="glyphicon-pencil"></span><input type="text" name="node"  value="Узел" onBlur="if(this.value == '') this.value = 'Узел'" onFocus="if(this.value == 'Узел') this.value = ''" required></p>
            <p><span class="glyphicon-pencil"></span><input type="text" name="detail"  value="Деталь" onBlur="if(this.value == '') this.value = 'Деталь'" onFocus="if(this.value == 'Деталь') this.value = ''" required></p>
            <p><span class="glyphicon-pencil"></span><input type="text" name="number"  value="Количество" onBlur="if(this.value == '') this.value = 'Количество'" onFocus="if(this.value == 'Количество') this.value = ''" required></p>
            <p><input type="submit" value="Отправить деталь"></p>
        </fieldset>
    </form>
</div>

<center><p><a href="/logout" class="btn btn-danger">LogOut(<%=request.getAttribute("current_user")%>)</a></p></center>
<%if (request.getAttribute("success")!=null){%>
<div class="div1"> <%=request.getAttribute("success")%></div>
<%}%>
<%if (request.getAttribute("fail")!=null){%>
<div class="div1"> <%=request.getAttribute("fail")%></div>
<%}%>
    <%}%>
<%}%>



<script src="http://code.jquery.com/jquery-latest.js"></script>
<script src="bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
</body>
</html>
