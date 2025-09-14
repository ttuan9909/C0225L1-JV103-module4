<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Từ điển Anh–Việt</title>
    <style>
        body{font-family:Arial,sans-serif;background:#f6f8fa;padding:40px}
        .card{max-width:560px;margin:auto;background:#fff;padding:24px;border-radius:12px;box-shadow:0 4px 16px rgba(0,0,0,.06)}
        label{font-weight:600}
        input{width:100%;padding:10px;margin-top:6px;border:1px solid #ddd;border-radius:8px}
        button{margin-top:14px;padding:10px 16px;border:0;border-radius:8px;background:#2563eb;color:#fff;cursor:pointer}
        .result{margin-top:18px;padding:12px 14px;border-radius:8px;background:#f1f5f9}
        .notfound{color:#dc2626}
    </style>
</head>
<body>
<div class="card">
    <h2>Từ điển Anh–Việt</h2>
    <form action="${pageContext.request.contextPath}/lookup" method="get">
        <label for="q">Nhập từ tiếng Anh:</label>
        <input id="q" name="q" placeholder="vd: hello" value="${query != null ? query : ''}" required/>
        <button type="submit">Tra cứu</button>
    </form>

    <c:if test="${not empty query}">
        <div class="result">
            <c:choose>
                <c:when test="${found}">
                    <b>${query}</b> → ${meaning}
                </c:when>
                <c:otherwise>
                    <span class="notfound">Không tìm thấy từ “${query}”.</span>
                </c:otherwise>
            </c:choose>
        </div>
    </c:if>
</div>
</body>
</html>
