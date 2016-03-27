<%--
  Created by IntelliJ IDEA.
  User: njonnala
  Date: 3/22/2016
  Time: 6:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title></title>
    <link rel="stylesheet" href="bower_components/bootstrap/dist/css/bootstrap.css">
    <style>
        .center_div{
            margin: 0 auto;
            width:80% /* value of your choice which suits your alignment */
        }
        .invalid{
            color:red;
            font-size:20px;
        }
    </style>
    <script>
        function verifyPassword(){
            var a = document.getElementById("inputPassword");
            var b = document.getElementById("verifypassword");


            if (a.value == b.value){
                return true;
            }
            else{
                alert("password doesnot match");
                //document.write("doesnot match");
                return false;
            }
        }

    </script>
</head>
<body>
<div class="container center_div">

    <div class="row">
        <div class="col-xs-6"/>
        <div class="col-xs-6">
            <form class="form-signin" action="signup" method="post"  onsubmit="return verifyPassword();">
                <h2 class="form-signin-heading">Please sign Up here</h2>
                <!-- <label for="inputEmail" class="sr-only">Email address</label>!-->
                <input type="text" id="inputfirstname" class="form-control" placeholder="FirstName" required="" autofocus="" name="firstname"><br/>
                <input type="text" id="inputlastname" class="form-control" placeholder="LastName" required="" autofocus="" name="lastname"><br/>
                <input type="text" id="inputEmail" class="form-control" placeholder="username" required="" autofocus="" name="username"><br/>
                <br/>
                <label for="inputPassword" class="sr-only">Password</label>
                <input type="password" id="inputPassword" class="form-control" placeholder="Password" required="" name="password"><br/>
                <input type="password" id="verifypassword" class="form-control" placeholder="VerifyPassword" required="" name="password">
                <div class="checkbox">
                </div>
                <button class="btn btn-lg btn-primary btn-block" type="submit">Sign Up</button>

            </form>
            <%

                String message =  (String)request.getAttribute("message");
                System.out.println(message);
                if(message != null) {
                    if (message.equals("Invalid")) {
                        out.println("<p class=\"invalid\"> Invalid Login details </p> ");
                    }
                }



            %>
        </div>
    </div>

</div>
</body>
</html>
