<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>User order</title>
</head>
<body>

<h2><c:out value="Hello! ${userName} ${userSurname}"/></h2>
<br>


<table>
    Items in order:
    <tr>
        <th>ITEM CODE</th>
        <th>NAME</th>
        <th>PRICE</th>
        <th>AMOUNT</th>
        <th>DELETE</th>
    </tr>
    <c:forEach items="${orderItems}" var="item">
        <form action="order-item", method="post">
            <tr>
                <input type="text" name="orderId" value="${orderId}" hidden/>
                <input type="text" name="itemId" value="${item.id}" hidden/>
                <td><c:out value="${item.itemCode}"/></td>
                <td><c:out value="${item.name}"/></td>
                <td><c:out value="${item.price}"/></td>
                <td><c:out value="${item.amount}"/></td>
                <td><input type="submit" value="DELETE"></td>
            </tr>
        </form>
    </c:forEach>


<div>
    <form action="order" metod="post">
        <input type="text" name="action" value="closeOrder" hidden>
        <input type="text" name="userId" value="${userId}" hidden>
        <input type="submit" value="BUY" hidden>
    </form>
</div>
<br>
<br>

</table>

</body>
</html>
