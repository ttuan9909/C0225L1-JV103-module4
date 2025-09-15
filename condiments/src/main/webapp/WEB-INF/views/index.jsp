<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %><!DOCTYPE html>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Chọn gia vị Sandwich</title>
</head>
<body>
<h2>Chọn gia vị cho Sandwich</h2>

<form action="${pageContext.request.contextPath}/save" method="post">
    <label>
        <input type="checkbox" name="condiment" value="Lettuce"> Rau xà lách
    </label><br>
    <label>
        <input type="checkbox" name="condiment" value="Tomato"> Cà chua
    </label><br>
    <label>
        <input type="checkbox" name="condiment" value="Cheese"> Phô mai
    </label><br>
    <label>
        <input type="checkbox" name="condiment" value="Mayo"> Mayonnaise
    </label><br>
    <label>
        <input type="checkbox" name="condiment" value="Mustard"> Mù tạt
    </label><br>

    <button type="submit">Lưu lựa chọn</button>
</form>

</body>
</html>
