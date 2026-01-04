<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>登录</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <div class="container">
        <h2>图书馆管理系统-登录</h2>
        <form action="${pageContext.request.contextPath}/login" method="post">
            <div class="form-item">
                <label>用户名：</label>
                <input type="text" name="username" required>
            </div>
            <div class="form-item">
                <label>密码：</label>
                <input type="password" name="password" required>
            </div>
            <div class="form-item">
                <button type="submit">登录</button>
                <a href="${pageContext.request.contextPath}/register">注册</a>
            </div>
        </form>
        <c:if test="${not empty errorMsg}">
            <div class="error">${errorMsg}</div>
        </c:if>
        <c:if test="${not empty successMsg}">
            <div class="success">${successMsg}</div>
        </c:if>
    </div>
</body>
</html>