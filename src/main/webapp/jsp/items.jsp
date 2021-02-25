<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Items to sell</title>
</head>
<body>

<h2><c:out value="Hello! ${userName} ${userSurname}"/></h2>
<br>
<div>
    <form action="/open-order">
        <input type="text" name="userId" value="${userId}" hidden>
        <input type="submit" value="GO TO OPEN CART">
    </form>
</div>
<br>
<br>

<table>
    Items for sell:
    <tr>
        <th>ITEM CODE</th>
        <th>NAME</th>
        <th>PRICE</th>
        <th>INIT PRICE</th>
        <th>AMOUNT</th>
        <th>BUY</th>
    </tr>
    <c:forEach items="${items}" var="item">
        <form action="/order-item-add", method="post">
            <tr>
                <input type="text" name="userId" value="${userId}" hidden/>
                <input type="text" name="itemId" value="${item.id}" hidden/>
                <td><c:out value="${item.itemCode}"/></td>
                <td><c:out value="${item.name}"/></td>
                <td><c:out value="${item.price}"/></td>
                <td><c:out value="${item.initPrice}"/></td>
                <td><input type="text" name="amount" size="5"></td>
                <td><input type="submit" value="BUY"></td>
            </tr>
        </form>
    </c:forEach>
</table>

</body>
</html>
