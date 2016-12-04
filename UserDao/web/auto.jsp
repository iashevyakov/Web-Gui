<%@ page import="ru.itis.inform.Auto" %>
<%@ page import="java.util.LinkedList" %><%--
  Created by IntelliJ IDEA.
  User: Иван
  Date: 25.09.2016
  Time: 22:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Auto</title>

</head>
<body>
<%
    LinkedList<Auto> list = (LinkedList<Auto>) request.getAttribute("autos");
    for (int i = 0; i < list.size(); i++) {
%>
<%=list.get(i).getModel()%>

<br>
<%
    }
%>
</body>
</html>
