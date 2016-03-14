<%@ page import="com.web.MyAccount" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: devendra
  Date: 3/12/2016
  Time: 1:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>

</head>
<body>

<table border ="2" style="width: 100%">
<%
    List<MyAccount> accounts =(List<MyAccount>)request.getAttribute("accounts");
    for(MyAccount account:accounts){
        out.print("<tr>");
        out.print("<td>"+account.getId()+"</td>");
        out.print("<td>"+account.getFirstName()+"</td>");
        out.print("<td>"+account.getLastName()+"</td>");
        out.print("<td>"+account.getBalance()+"</td>");
        out.print("<td>"+account.getBankName()+"</td>");
        out.print("<td><a href='deleteMyAccountServlet?id="+account.getId()+"'>Delete</a></td>");
        out.print("<td><a href='LoadMyServlet?id="+account.getId()+"'>Update</a></td>");
        out.print("</tr>");
    }
%>

</table>
</body>
</html>
