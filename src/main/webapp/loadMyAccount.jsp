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

<table border ="2" style="width: 20%">
    <form action="/updateMyAccountServlet" method ="post">
<%

        MyAccount account = (MyAccount) request.getAttribute("accounts");
        //List<MyAccount> accounts =(List<MyAccount>)request.getAttribute("accounts");

            out.print("<tr>");
            out.print("<td>Id:</td>");
            out.print("<td><input type='text' name='id' readonly value="+account.getId()+"></td>");
            out.print("</tr>");
            out.print("<tr>");
        out.print("<td>FirstName:</td>");
            out.print("<td> <input type='text' name='FirstName' value="+account.getFirstName()+"></td>" );
            out.print("</tr>");


    out.print("<tr>");
    out.print("<td>LastName:</td>");
            out.print("<td> <input type='text' name='LastName' value="+account.getLastName()+"></td>" );
            out.print("</tr>");

            out.print("<tr>");
    out.print("<td>Balance:</td>");
            out.print("<td> <input type='text' name='Balance' value="+account.getBalance()+"></td>" );
            out.print("</tr>");

            out.print("<tr>");
    out.print("<td>BankName:</td>");
            out.print("<td> <input type='text' name='BankName' value="+account.getBankName()+"></td>" );
            out.print("</tr>");
            out.print("<tr>");
            out.print("<td colspan = '4'> <input type='submit' value='submit'></td>");
            out.print("</tr>");

    %>
    </form>
</body>
</html>
