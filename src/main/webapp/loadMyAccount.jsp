<%@ page import="com.web.MyAccount" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: devendra
  Date: 3/13/2016
  Time: 13:55
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
            out.print("<td>Id:</td>");
            out.print("<td><input type='text' name='id' readonly value="+account.getId()+"></td>");
            out.print("</tr>");
            out.print("<tr>");
            out.print("<td> <input type='text' name='FirstName' value="+account.getFirstName()+"></td>" );
            out.print("</tr>");
            out.print("<tr>");
            out.print("<td> <input type='text' name='LastName' value="+account.getLastName()+"></td>" );
            out.print("</tr>");
            out.print("<tr>");
            out.print("<td> <input type='text' name='Balance' value="+account.getBalance()+"></td>" );
            out.print("</tr>");
            out.print("<tr>");
            out.print("<td> <input type='text' name='BankName' value="+account.getBankName()+"></td>" );
            out.print("</tr>");
        }
    %>

</body>
</html>
