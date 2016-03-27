<%@ page import="com.web.model.Order" %>
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
  <script>
      </script>
</head>
<body>
<table border="1" style="width:100%">
  <%
    List<Order> orders = (List<Order>)request.getAttribute("orders");

    for(Order order:orders) {
      out.print("<tr>");
      out.print("<td>" + order.getOrderId() + "</td>");
      out.print("<td>" + order.getQuantity() + "</td>");
      out.print("<td>" + order.getOrderprice() + "</td>");
      out.print("<td>" + order.getAmount() + "</td>");
      out.print("<td>" + order.getUserName()+ "</td>");
      out.print("<td><a href='UpdateOrder.jsp?orderId=" + order.getOrderId() + "'>Update</a></td>");
      out.print("<td><a href='createOrder.jsp'>Create</a></td>");
      out.print("<td><a href='deleteOrder?orderId=" + order.getOrderId() + "'>Delete</a></td>");

      out.print("</tr>");

    }
  %>
</table>

</body>
</html>
