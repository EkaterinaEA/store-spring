<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Catalog</title>

</head>
<body>
<h1>Hello, <c:out value="${userName}"></c:out>!</h1><br/>

<div class="form">

    <h2>Catalog</h2>

    <form method="post" action="<c:url value='/cart'/>">
        <input type="number" hidden name="userId" value="${userId}">
        <input type="submit" value="My cart">
    </form>

    <c:forEach var="item" items="${items}">
        <ul>
            <li><c:out value="${item.name}"/></li>
            <li>Price: <c:out value="${item.price}"/></li>
            <li>Id: <c:out value="${item.itemId}"/></li>
            <li>url: <c:out value="${item.url}"/></li>
            <li>imageUrl: <c:out value="${item.imageUrl}"/></li>

            <form method="post" action="<c:url value='/cart'/>">
                <input type="submit" value="Add to cart">
                <input type="number" hidden name="userId" value="${userId}">
                <input type="number" hidden name="productId" value="${item.productId}">
            </form>
        </ul>
        <hr />
    </c:forEach>
</div>

</body>
</html>

