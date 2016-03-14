
<%@ page import="com.web.model.AmarAccount" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Amar
  Date: 3/11/2016
  Time: 4:18 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>AmarATM</title>
    <link rel="stylesheet" href="js/ui/1.11.4/themes/smoothness/jquery-ui.css">
    <script src="js/jquery-1.10.2.js"></script>
    <script src="js/ui/1.11.4/jquery-ui.js"></script>
    <link rel="stylesheet" href="/resources/demos/style.css">
    <script>
        $(function() {
            $( "#tabs" ).tabs();
        });
    </script>
</head>
<body>
<div id="tabs" style="background-color:#00ff00">
    <ul>
        <li><a href="#tabs-1">Update Account</a></li>
    </ul>
    <div id="tabs-1" style="background-color:#ff0000">
        <p>
        <form action="updateAccountServletAmar">
            <table>
                <%
                 AmarAccount accounts = (AmarAccount) request.getAttribute("accounts");
                %>
                <tr>
                    <td>Account Number: </td>
                    <td><input type="text" name="accnum" value="<%=accounts.getAccountNumber()%>" readonly></td>
                </tr>
                <tr>
                    <td>First Name : </td>
                    <td><input type="text" name="fname" value="<%=accounts.getFirstName()%>"></td>
                </tr>
                <tr>
                    <td>Last Name : </td>
                    <td> <input type="text" name="lname" value="<%=accounts.getLastName()%>"></td>
                </tr>
                <tr>
                    <td> Balance :</td>
                    <td><input type="text" name="balance" value="<%=accounts.getBalance()%>"></td>
                </tr>
                <tr>
                    <td colspan="2"><input type="submit" value="Update"/></td>
                </tr>
            </table>
        </form>
        </p>
    </div>
</body>
</html>
