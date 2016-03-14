    <%@ page import="java.util.List" %>
    <%@ page import="com.web.model.KeerthiAccount" %><%--
      Created by IntelliJ IDEA.
      User: CrazyNaveen
      Date: 3/10/16
      Time: 10:41 PM
      To change this template use File | Settings | File Templates.
    --%>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <html>
    <head>
        <title>Title</title>
    </head>
    <body>
    <table style="border: 2px groove darkolivegreen;" cellpadding="2" cellspacing="2">
    <%
         List<KeerthiAccount> list = (List<KeerthiAccount>)request.getAttribute("accounts");
        for (KeerthiAccount account:list){
            out.print("<tr>");
            out.print("<td>" +account.getId() +"</td>");
            out.print("<td>" +account.getUserName() +"</td>");
            out.print("<td>" +account.getAccNumber() + "</td>");
            out.print("<td>" +account.getBalance() +"</td>");
            out.print("<td><a href='keeLoadList?id="+account.getId()+"'>Update</a></td>");
            out.print("<td><a href='keerthiDelete?id="+account.getId()+"'>Delete</a></td>");
            out.print("</tr>");
        }
    %>
    </table>
    </body>
    </html>
