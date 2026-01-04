<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>个人中心</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <div class="header">
        <h1>个人中心</h1>
        <div class="nav">
            <a href="${pageContext.request.contextPath}/book/search">图书查询</a>
            <a href="${pageContext.request.contextPath}/borrow/my">我的借阅</a>
            <a href="${pageContext.request.contextPath}/user/center">个人中心</a>
            <a href="${pageContext.request.contextPath}/login">退出登录</a>
        </div>
    </div>

    <div class="user-info">
        <h3>个人信息修改</h3>
        <form action="${pageContext.request.contextPath}/user/update" method="post">
            <div class="form-item">
                <label>用户名：</label>
                <input type="text" name="username" value="${userInfo.username}" disabled>
            </div>
            <div class="form-item">
                <label>真实姓名：</label>
                <input type="text" name="realName" value="${userInfo.realName}" required>
            </div>
            <div class="form-item">
                <label>学号/身份证号：</label>
                <input type="text" name="idCard" value="${userInfo.idCard}" required>
            </div>
            <div class="form-item">
                <label>手机号：</label>
                <input type="tel" name="phone" value="${userInfo.phone}" required>
            </div>
            <div class="form-item">
                <button type="submit">保存修改</button>
            </div>
        </form>
        <c:if test="${not empty successMsg}">
            <div class="success">${successMsg}</div>
        </c:if>
        <c:if test="${not empty errorMsg}">
            <div class="error">${errorMsg}</div>
        </c:if>
    </div>
</body>
</html>