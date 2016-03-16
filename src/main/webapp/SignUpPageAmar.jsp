<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: Amar  Date: 3/16/2016
  Time: 2:27 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>SignUpAmar</title>
    <link rel="stylesheet" type="text/css" href="cssAmar/signup.css" />
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
    <div id="div-regForm">
        <div class="form-title">Sign Up</div>
        <div class="form-sub-title">It's free and anyone can join</div>
        <form id="regForm" action="signUpServletAmar" method="post">
            <table>
                <tbody>
                <tr>
                    <td><label for="fname">First Name:</label></td>
                    <td><div class="input-container"><input name="fname" id="fname" type="text" /></div></td>
                </tr>
                <tr>
                    <td><label for="lname">Last Name:</label></td>
                    <td><div class="input-container"><input name="lname" id="lname" type="text" /></div></td>
                </tr>
                <tr>
                    <td><label for="email">Your Email:</label></td>
                    <td><div class="input-container"><input name="email" id="email" type="text" /></div></td>
                </tr>
                <tr>
                    <td><label for="pass">New Password:</label></td>
                    <td><div class="input-container"><input name="pass" id="pass" type="password" /></div></td>
                </tr>
                <tr>
                    <td><label for="sex-select">I am:</label></td>
                    <td>
                        <div class="input-container">
                            <select name="sex-select" id="sex-select">
                                <option value="0">Select Sex:</option>
                                <option value="1">Female</option>
                                <option value="2">Male</option>
                            </select>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td><label>Birthday:</label></td>
                    <td>
                        <div class="input-container">
                            <select name="month"><option value="0">Month:</option><?=generate_options(1,12,'callback_month')?></select>
                            <select name="day"><option value="0">Day:</option><?=generate_options(1,31)?></select>
                            <select name="year"><option value="0">Year:</option><?=generate_options(date('Y'),1900)?></select>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td><input type="submit" class="greenButton" value="Sign Up" /><img id="loading" src="img/ajax-loader.gif" alt="working.." />
                    </td>
                </tr>
                </tbody>
            </table>
        </form>
        <div id="error">
            &nbsp;
        </div>
    </div>


</body>
</html>
