<%@ page import="com.web.model.Account" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: skandula
  Date: 3/10/16
  Time: 8:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<table border="1" style="width:100%">
  <%
    List<Account> accounts = (List<Account>)request.getAttribute("accounts");

    for(Account account:accounts){
      out.print("<tr>");
      out.print("<td>"+account.getId()+"</td>");
      out.print("<td>"+account.getFirstName()+"</td>");
      out.print("<td>"+account.getLastName()+"</td>");
      out.print("<td>"+account.getBalance()+"</td>");
      out.print("<td><a href='updateAccountServlet'>Update</a></td>");
      out.print("<td><a href='deleteAccountServlet?id="+account.getId()+"'>Delete</a></td>");

      out.print("</tr>");
    }

  %>
</table>

</body>
</html>
