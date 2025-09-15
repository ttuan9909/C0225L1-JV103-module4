<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %><!DOCTYPE html>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Calculator</title>
</head>
<body>
<h1>Calculator</h1>

<form action="${pageContext.request.contextPath}/calculate" method="post">
    <input type="number" step="any" name="num1" placeholder="Số thứ nhất" value="${num1}" required>
    <input type="number" step="any" name="num2" placeholder="Số thứ hai" value="${num2}" required>
    <br><br>
    <button type="submit" name="operation" value="add">Addition(+)</button>
    <button type="submit" name="operation" value="sub">Subtraction(-)</button>
    <button type="submit" name="operation" value="mul">Multiplication(x)</button>
    <button type="submit" name="operation" value="div">Division(/)</button>
</form>

<hr/>

<c:if test="${not empty error}">
    <p style="color:red">${error}</p>
</c:if>

<c:if test="${not empty result}">
    <p>Kết quả ${operation} giữa ${num1} và ${num2} = <b>${result}</b></p>
</c:if>

</body>
</html>
