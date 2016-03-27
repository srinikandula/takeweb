<%@ page import="com.web.model.KeerthiAccount" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: CrazyNaveen
  Date: 3/11/16
  Time: 10:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title></title>
    <link rel="stylesheet" href="js/ui/1.11.4/themes/smoothness/jquery-ui.css">
    <script src="js/jquery-1.10.2.js"></script>
    <script src="js/ui/1.11.4/jquery-ui.js"></script>
    <link rel="stylesheet" href="/resources/demos/style.css">
    <script>
        $(function() {
            $( "#tabs" ).tabs();
        });
    </script>
    <style>
        #tab1 {

            border: 1px solid black;
        }
        tr,td{
            padding: 5px;
        }
        #tab1 tr:nth-child(odd){
            background-color: #eee;
        }
        #tab1 tr:nth-child(even){
            background-color: #fff;
        }

    </style>
</head>
<body>

<%--
<div id="tabs">
    <ul>
        <li><a href="#tabs-1">Create Account</a></li>

    </ul>
    <div id="tabs-1">
        <p>
--%>
       <form action="keerthiUpdate" >
            <table id="tab1" align="center">
                <%
                    KeerthiAccount account = (KeerthiAccount) request.getAttribute("account");
                    //for (KeerthiAccount account : acc){
                        out.print("<tr>");
                        out.print("<td>Id:</td>");
                        out.print("<td> <input type='text' name='id' readonly value="+account.getId() +"></td>");
                        out.print("</tr>");
                        out.print("<tr>");
                        out.print("<td>User Name:</td>");
                        out.print("<td> <input  type=\"text\" name=\"uname\" value="+account.getUserName() +"></td>");
                        out.print("</tr>");
                        out.print("<tr>");
                        out.print("<td>Account Number:</td>");
                        out.print("<td> <input type=\"text\" name=\"accNum\" value="+account.getAccNumber()+"> </td>");
                        out.print("</tr>");
                        out.print("<tr>");
                        out.print("<td>Balance:</td>");
                        out.print("<td> <input type=\"text\" name=\"balance\" value="+account.getBalance()+"></td>");
                        out.print("</tr>");
                        out.print("</tr>");
                        out.print("<td colspan='2'> <input type=\"submit\" name=\"submit\" value=\"Update\"> </td>");
                        out.print("</tr>");

                   // }
                %>

                    <%--<%
                        List<KeerthiAccount> list = (List<KeerthiAccount>)request.getAttribute("accounts");
                        for (KeerthiAccount account:list){
                            out.print("<tr>");
                            out.print("<td>" +account.getId() +"</td>");
                            out.print("<td>" +account.getUserName() +"</td>");
                            out.print("<td>" +account.getAccNumber() + "</td>");
                            out.print("<td>" +account.getBalance() +"</td>");
                            out.print("<td><a href='keerthiUpdate?id="+account.getId()+ "&uname="+ account.getUserName()+"&accNum="+account.getAccNumber()+"&balance="+account.getBalance()+"'>Update</a></td>");
                            //out.print("<td><a href='keerthiDelete?id="+account.getId()+"'>Delete</a></td>");
                            out.print("</tr>");
                        }
                    %>--%>
            </table>

       <%-- </form>
        </p>
    </div>

</div>--%>
</body>

</html>
