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
    <title>DU Node</title>
    <meta charset="UTF-8">
    <link href="bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
    <script src="bootstrap-3.3.7-dist/js/jquery-3.1.1.js"></script>
    <link rel="stylesheet" href="bootstrap-3.3.7-dist/css/style.css" media="screen" type="text/css" />
    <link rel="icon" href="http://vladmaxi.net/favicon.ico" type="image/x-icon">
    <link rel="shortcut icon" href="http://vladmaxi.net/favicon.ico" type="image/x-icon">
</head>
<body>

<div id="login">
    <form id="1" action="/deleteNode" method="post">
        <fieldset class="clearfix" >
            <p><span class="fontawesome-user"></span><input type="text" name="node" value="Node" onBlur="if(this.value == '') this.value = 'Node'" onFocus="if(this.value == 'Node') this.value = ''" required></p> <!-- JS because of IE support; better: placeholder="Username" -->
            <p><select  class="styled-select" name="option" id="work">
                <option value="delete">DELETE</option>
                <option value="update">UPDATE</option>
            </select>
            </p>
            <p><input type="submit" value="PERFORM"></p>
        </fieldset>
    </form>

    <%if (!Err.message.equals("")){%>
    <div class="div2"><%=Err.message%></div>
    <%Err.message="";}%>

</div>
<p><a href="/home" class="btn btn-danger">Back(<%=request.getAttribute("current_user")%>)</a></p>



<script src="http://code.jquery.com/jquery-latest.js"></script>
<script src="bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
</body>
</html>