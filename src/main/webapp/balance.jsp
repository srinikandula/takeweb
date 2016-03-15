<%--
  Created by IntelliJ IDEA.
  User: devendra
  Date: 3/11/2016
  Time: 1:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<%
    String balance = request.getAttribute("balance").toString();

    out.println("The account balance is "+ balance);

%>

</body>
</html>
