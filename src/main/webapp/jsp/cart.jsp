<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

</head>
<body>
<h1>Shopping cart</h1><br/>

<form method="post" action="<c:url value='/catalog'/>">
    <input type="submit" value="return to catalog">
</form>

<div class="form">

    <h2>Number of goods: <c:out value="${itemsfromcartsize}"></c:out></h2>

    <c:if test="${cartempty == 1}">
    <p>
    <h3>Cart is empty</h3>
    <p>
        </c:if>

        <c:if test="${cartempty != 1}">
    <p>
        <c:forEach var="item" items="${itemsfromcart}">
    <ul>
        <li><c:out value="${item.name}"/></li>
        <li>Price: <c:out value="${item.price}"/></li>
        <li>Id: <c:out value="${item.itemId}"/></li>
        <li>url: <c:out value="${item.url}"/></li>
        <li>imageUrl: <c:out value="${item.imageUrl}"/></li>

        <form method="get" action="<c:url value='/cart'/>">
            <input type="submit" value="Remove from cart">
            <input type="number" hidden name="userId" value="${userId}">
            <input type="number" hidden name="productIdDelete" value="${item.productId}">
            <input type="number" hidden name="cartId" value="${cartId}">
        </form>

    </ul>
    <hr/>
    </c:forEach>
    <h2>Total price: <c:out value="${totalPrice}"></c:out></h2>
    <form method="post" action="<c:url value='/checkout'/>">
        <input type="submit" value="Make an order">
        <input type="number" hidden name="userId" value="${userId}">
        <input type="number" hidden name="productId" value="${item.productId}">
        <input type="number" hidden name="cartId" value="${cartId}">
        <input type="number" hidden name="totalPrice" value="${totalPrice}">
    </form>
    <p>
        </c:if>

</div>

</body>
</html>