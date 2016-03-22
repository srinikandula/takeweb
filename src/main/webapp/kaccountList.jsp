    <%@ page import="java.util.List" %>
    <%@ page import="com.web.model.KeerthiAccount" %>
    <%@ page import="com.web.keerthi.KeerthiUser" %><%--
      Created by IntelliJ IDEA.
      User: CrazyNaveen
      Date: 3/10/16
      Time: 10:41 PM
      To change this template use File | Settings | File Templates.
    --%>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <!--<link rel = "stylesheet" type="text/css"/>-->
    <link rel="stylesheet" href="js/ui/1.11.4/themes/smoothness/jquery-ui.css">
    <script src="js/jquery-1.10.2.js"></script>
    <script src="js/ui/1.11.4/jquery-ui.js"></script>
    <html>
    <head>
        <title>List of Accounts</title>
        <style>
            #tab1 {
                border: 1px solid black;
                border-collapse: collapse;
                cellpadding:"1";
                cellspacing:"1";
                width:100%;
            }
            tr,td{
                padding: 10px;
                width: 65px;
            }
            #tab1 tr:nth-child(even){
                background-color: #eee;
            }
            #tab1 tr:nth-child(odd){
                background-color: #fff;
            }
        </style>
    </head>
    <body>
    <%
        Object obj = session.getAttribute("loggedinUser");
        if(obj != null){
            KeerthiUser user = (KeerthiUser) obj;
            out.print("<p> User logged in <b>"+user.getUserName()+"  <a href=\"keerthiLogin.html\">Logout</a></p>");
        }
    %>

   <!-- <p>User logged in <%=((KeerthiUser)session.getAttribute("loggedinUser")).getUserName()%> <a href="logoutServlet">Logout</a> </p>-->
    <table id="tab1">
    <%
         List<KeerthiAccount> list = (List<KeerthiAccount>)request.getAttribute("accounts");
       /* out.print("<tr>");
        out.print("<th>"+"Id"+ "<th>");
        out.print("<th>"+"Name"+ "<th>");
        out.print("<th>"+"Account Number"+"<th>");
        out.print("<th>"+"Balance"+ "<th>");
        out.print("<th>"+"CreatedBy"+ "<th>");
        out.print("<th>"+"Update"+ "<th>");
        out.print("<th>"+"Delete"+ "<th>");*/
       KeerthiUser user = (KeerthiUser)session.getAttribute("loggedinUser");

        out.print("</tr>");
        for (KeerthiAccount account:list){
            //account.setCreatedBy(user.getUserName());
            out.print("<tr>");
            out.print("<td>" +account.getId() + "</td>");
            out.print("<td>" +account.getUserName() + "</td>");
            out.print("<td>" +account.getAccNumber() + "</td>");
            out.print("<td>" +account.getBalance() + "</td>");
           // out.print("<td>" +account.getCreatedBy() + "</td>");
            out.print("<td><a href='keeLoadList?id="+account.getId()+"'>Update</a></td>");
            out.print("<td><a href='keerthiDelete?id="+account.getId()+"'>Delete</a></td>");
            out.print("</tr>");
        }
    %>
    </table>
    </body>
    </html>
