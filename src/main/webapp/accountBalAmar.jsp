<%--
  Created by IntelliJ IDEA.
  User: Amar
  Date: 3/10/2016
  Time: 4:57 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>AmarATM</title>
</head>
<body>
<%
    String balance = request.getAttribute("bal").toString();

    out.println("The account balance is "+"$"+ balance);

%>
</body>
</html>
