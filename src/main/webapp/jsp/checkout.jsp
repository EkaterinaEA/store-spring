<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>checkout</title>

</head>
<body>
<h1><c:out value="${userName}"></c:out>, congratulations!</h1><br/>

<div class="form">

    <h2>Your order for the amount <c:out value="${totalPrice}"></c:out> is formed!</h2>

    <form method="post" action="<c:url value='/catalog'/>">
        <input type="submit" value="return to catalog">
    </form>

</div>

</body>
</html>
