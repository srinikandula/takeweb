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
</head>
<body>
<div class="container center_div">

<div class="row">
    <div class="col-xs-6"/>
    <div class="col-xs-6">
        <form class="form-signin" action="login" method="post">
            <h2 class="form-signin-heading">Please sign in</h2>
           <!-- <label for="inputEmail" class="sr-only">Email address</label>!-->
            <input type="text" id="inputEmail" class="form-control" placeholder="username" required="" autofocus="" name="username">
            <br/>
            <label for="inputPassword" class="sr-only">Password</label>
            <input type="password" id="inputPassword" class="form-control" placeholder="Password" required="" name="password">
            <div class="checkbox">
                </div>
            <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button><br/>
            Not a member:<a href="SignUp.jsp">SignUpHere</a>
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