<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"><title>Settings</title>
    <style>
        body{font-family:Arial, sans-serif; padding:24px;}
        .container{max-width:720px; margin:auto;}
        .row{display:flex; align-items:center; margin:12px 0;}
        .row>div:first-child{width:160px; font-weight:bold;}
        textarea{width:100%; height:160px;}
        .btn{padding:8px 18px; border:0; border-radius:6px; cursor:pointer}
        .btn-primary{background:#1d9bf0; color:#fff;} .btn-outline{background:#fff; border:1px solid #999;}
        .msg{background:#ecfeff; color:#0369a1; padding:8px 12px; border-radius:6px; margin-bottom:12px;}
        .error{color:#dc2626;}
    </style>
</head>
<body>
<div class="container">
    <h1>Settings</h1>

    <c:if test="${not empty message}">
        <div class="msg">${message}</div>
    </c:if>

    <form:form method="post" modelAttribute="settings">
        <div class="row">
            <div>Languages</div>
            <div>
                <form:select path="language">
                    <form:options items="${languages}"/>
                </form:select>
                <form:errors path="language" cssClass="error"/>
            </div>
        </div>

        <div class="row">
            <div>Page Size:</div>
            <div>
                Show
                <form:select path="pageSize">
                    <form:options items="${pageSizes}"/>
                </form:select>
                emails per page
                <form:errors path="pageSize" cssClass="error"/>
            </div>
        </div>

        <div class="row">
            <div>Spams filter:</div>
            <div><label><form:checkbox path="spamFilter"/> Enable spams filter</label></div>
        </div>

        <div class="row" style="align-items:flex-start">
            <div>Signature:</div>
            <div>
                <form:textarea path="signature"/>
                <form:errors path="signature" cssClass="error"/>
            </div>
        </div>

        <div>
            <button class="btn btn-primary" type="submit">Update</button>
        </div>
    </form:form>

    <form action="${pageContext.request.contextPath}/settings/cancel" method="post" style="margin-top:8px;">
        <button class="btn btn-outline" type="submit">Cancel</button>
    </form>
</div>
</body>
</html>
