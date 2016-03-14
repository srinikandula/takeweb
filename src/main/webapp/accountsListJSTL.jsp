<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>

<%--
  Created by IntelliJ IDEA.
  User: skandula
  Date: 3/10/16
  Time: 8:48 PM
  To change this template use File | Settings | File Templates.
--%>

<html>
<head>
    <title></title>
</head>
<body>
<table border="1" style="width:100%">
  <c:forEach var="account" items="accounts">
    <tr><td><c:out value="#{account.id}"/></td></tr>
  </c:forEach>

</table>

</body>
</html>
