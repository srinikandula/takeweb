<%--
  Created by IntelliJ IDEA.
  User: skandula
  Date: 3/9/16
  Time: 9:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>

<%
  String balance = request.getAttribute("balance").toString();

  out.println("The account balance is "+ balance);

%>

</body>
</html>
