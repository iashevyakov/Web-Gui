<%@ page import="java.util.LinkedList" %>
<%@ page import="java.sql.*" %>
<%@ page import="ru.itis.inform.User" %><%--
<%@ page import =
  Created by IntelliJ IDEA.
  User: Иван
  Date: 28.10.2016
  Time: 12:17
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Control</title>
</head>
<body>
<% Connection connection = null;
%>
<%Class.forName("org.postgresql.Driver");%>
<%String url = "jdbc:postgresql://127.0.0.1:5432/autostudents";%>
<% String name = "postgres";%>
<% String password = "ronaldo777";%>
<%  try {

    Class.forName("org.postgresql.Driver");

    connection = DriverManager.getConnection(url, name, password);


} catch (ClassNotFoundException e) {
    e.printStackTrace();
} catch (SQLException e) {
    e.printStackTrace();
}
    ResultSet resultSet;
    LinkedList<User> list = new LinkedList();
    Statement statement = connection.createStatement();
    resultSet=statement.executeQuery("SELECT * FROM users ");
    User user;

    while (resultSet.next())
    {

        user = new User(resultSet.getString("name"),resultSet.getString("city"),resultSet.getInt("age"));

        list.addLast(user);

    }

    request.setAttribute("list",list);

    %>
    <c:set var="llist" scope="request" value="${list}"/>
    <c:forEach var="num" items="${llist})">
    <p>${num.toString()}</p>
    </c:forEach>


</body>
</html>
