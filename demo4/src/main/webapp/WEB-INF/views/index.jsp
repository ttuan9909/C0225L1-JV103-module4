<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Chuyển đổi USD → VND</title>
    <style>
        body { font-family: Arial, sans-serif; background:#f6f8fa; padding: 40px; }
        .card { max-width: 500px; margin: auto; background:#fff; padding: 24px; border-radius: 12px; box-shadow:0 4px 16px rgba(0,0,0,.06); }
        label { display:block; margin-top:12px; font-weight:600; }
        input { width:100%; padding:10px; margin-top:6px; border:1px solid #ddd; border-radius:8px; }
        button { margin-top:18px; padding:12px 16px; width:100%; border:0; border-radius:10px; font-weight:700; cursor:pointer; }
        .primary { background:#2563eb; color:#fff; }
        .error { color:#dc2626; margin-top:10px; }
    </style>
</head>
<body>
<div class="card">
    <h2>Chuyển đổi USD → VND</h2>

    <c:if test="${not empty error}">
        <div class="error">${error}</div>
    </c:if>

    <form action="${pageContext.request.contextPath}/convert" method="post">
        <label for="rate">Tỉ giá (VND cho 1 USD)</label>
        <input type="number" step="0.01" min="0" id="rate" name="rate" placeholder="Ví dụ: 24500" required>

        <label for="usd">Số USD cần đổi</label>
        <input type="number" step="0.01" min="0" id="usd" name="usd" placeholder="Ví dụ: 100" required>

        <button class="primary" type="submit">Tính VND</button>
    </form>
</div>
</body>
</html>
