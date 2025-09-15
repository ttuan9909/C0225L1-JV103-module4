
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %><!DOCTYPE html>
<html>
<head>
    <title>Kết quả</title>
</head>
<body>
<h2>Bạn đã chọn:</h2>
<ul>
    <c:forEach var="c" items="${condiments}">
        <li>${c}</li>
    </c:forEach>
</ul>
</body>
</html>