<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>注册</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <div class="container">
        <h2>图书馆管理系统-注册</h2>
        <form action="${pageContext.request.contextPath}/register" method="post">
            <div class="form-item">
                <label>用户名：</label>
                <input type="text" name="username" required>
            </div>
            <div class="form-item">
                <label>密码：</label>
                <input type="password" name="password" required>
            </div>
            <div class="form-item">
                <label>真实姓名：</label>
                <input type="text" name="realName" required>
            </div>
            <div class="form-item">
                <label>学号/身份证号：</label>
                <input type="text" name="idCard" required>
            </div>
            <div class="form-item">
                <label>手机号：</label>
                <input type="tel" name="phone" required>
            </div>
            <div class="form-item">
                <button type="submit">注册</button>
                <a href="${pageContext.request.contextPath}/login">返回登录</a>
            </div>
        </form>
        <c:if test="${not empty errorMsg}">
            <div class="error">${errorMsg}</div>
        </c:if>
    </div>
</body>
</html>