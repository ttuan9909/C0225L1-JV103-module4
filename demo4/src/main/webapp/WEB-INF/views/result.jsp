<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html lang="vi">
<head>
  <meta charset="UTF-8">
  <title>Kết quả</title>
  <style>
    body { font-family: Arial, sans-serif; background:#f6f8fa; padding: 40px; }
    .card { max-width: 520px; margin: auto; background:#fff; padding: 24px; border-radius: 12px; box-shadow:0 4px 16px rgba(0,0,0,.06); }
    a { text-decoration:none; color:#2563eb; }
    .num { font-weight:700; }
  </style>
</head>
<body>
<div class="card">
  <h2>Kết quả chuyển đổi</h2>
  <p>Tỉ giá: <span class="num"><fmt:formatNumber value="${rate}" type="number" /></span> VND/USD</p>
  <p>Số USD: <span class="num"><fmt:formatNumber value="${usd}" type="number" /></span> USD</p>
  <hr/>
  <p>Số tiền quy đổi:
    <span class="num">
       <fmt:formatNumber value="${vnd}" type="number" pattern="#,##0"/> VND
     </span>
  </p>
  <p><a href="${pageContext.request.contextPath}/">← Tính lại</a></p>
</div>
</body>
</html>
