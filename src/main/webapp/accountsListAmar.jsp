<%@ page import="com.web.model.AmarAccount" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: Amar
  Date: 3/11/2016
  Time: 10:23 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>AmarATM</title>
</head>
<body>
<table border="1" style="width:100%">
    <%
        List<AmarAccount> accounts = (List<AmarAccount>)request.getAttribute("accounts");

        for(AmarAccount account:accounts){
            out.print("<tr>");
            out.print("<td>"+account.getAccountNumber()+"</td>");
            out.print("<td>"+account.getFirstName()+"</td>");
            out.print("<td>"+account.getLastName()+"</td>");
            out.print("<td>"+account.getBalance()+"</td>");
            out.print("<td><a href='loadAccountServletAmar?accnum="+account.getAccountNumber()+"&fname="+account.getFirstName()+"&lname="+account.getLastName()+"&balance="+account.getBalance()+"'>Update</a></td>");
            out.print("<td><a href='deleteAccountServletAmar?accnum="+account.getAccountNumber()+"'>Delete</a></td>");

            out.print("</tr>");
        }

    %>
</table>
</body>
</html>
