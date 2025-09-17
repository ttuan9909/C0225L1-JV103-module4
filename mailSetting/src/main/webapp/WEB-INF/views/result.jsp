<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Mail Settings Result</title>
    <style>
        body { font-family: Arial, sans-serif; padding: 20px; background: #f9fafb; }
        .container { max-width: 700px; margin: auto; background: #fff; padding: 20px; border-radius: 8px;
            box-shadow: 0 2px 6px rgba(0,0,0,0.1);}
        h1 { color: #0369a1; margin-bottom: 20px; }
        .row { margin: 10px 0; }
        .label { font-weight: bold; width: 160px; display: inline-block; }
        pre { background: #f3f4f6; padding: 10px; border-radius: 6px; white-space: pre-wrap; }
        .msg { background: #dcfce7; color: #166534; padding: 10px; border-radius: 6px; margin-bottom: 15px; }
        a { text-decoration: none; color: #1d4ed8; }
    </style>
</head>
<body>
<div class="container">
    <h1>Mail Settings Updated</h1>

    <c:if test="${not empty message}">
        <div class="msg">${message}</div>
    </c:if>

    <div class="row">
        <span class="label">Language:</span>
        <span>${settings.language}</span>
    </div>

    <div class="row">
        <span class="label">Page Size:</span>
        <span>${settings.pageSize}</span>
    </div>

    <div class="row">
        <span class="label">Spam Filter:</span>
        <c:choose>
            <c:when test="${settings.spamFilter}">Enabled</c:when>
            <c:otherwise>Disabled</c:otherwise>
        </c:choose>
    </div>

    <div class="row">
        <span class="label">Signature:</span>
        <pre>${settings.signature}</pre>
    </div>

    <div class="row" style="margin-top:20px;">
        <a href="${pageContext.request.contextPath}/">← Back to Settings</a>
    </div>
</div>
</body>
</html>
