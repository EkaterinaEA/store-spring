<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Second Page</title>
</head>
<body>
Введенное имя: ${userJSP.name};
Введенное имя: <c:out value="${userJSP.name}"/>;
<br/>
Введенный пароль: ${userJSP.password};
<br/>
</body>
</html>
