<%--
  Created by IntelliJ IDEA.
  User: njonnala
  Date: 3/14/2016
  Time: 5:05 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update Your Order</title>
</head>
<body>

    <table>
        <form action="updateOrder">
            <tr>
                <td> Order ID : </td>
                <td> <input type="text"  name="orderId" value="<%request.getParameter("orderId");%>"></td>  </tr>

            <tr><td> Update Quantity :  </td>
              <td>  <input type="text" name="quantity"></td></tr>
              <tr> <td> Update Order price : </td>
                  <td><input type="text" name="orderprice"></td> </tr>
            <tr><td colspan="2"><input type="submit"/></tr>

        </form>

    </table>

</body>
</html>
