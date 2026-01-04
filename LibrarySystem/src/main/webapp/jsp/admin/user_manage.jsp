<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>用户管理</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <div class="header">
        <h1>用户管理</h1>
        <div class="nav">
            <a href="${pageContext.request.contextPath}/book/list">图书管理</a>
            <a href="${pageContext.request.contextPath}/user/list">用户管理</a>
            <a href="${pageContext.request.contextPath}/borrow/list">借阅管理</a>
            <a href="${pageContext.request.contextPath}/stat/month">统计分析</a>
            <a href="${pageContext.request.contextPath}/login">退出登录</a>
        </div>
    </div>

    <div class="user-list">
        <table border="1">
            <tr>
                <th>ID</th>
                <th>用户名</th>
                <th>真实姓名</th>
                <th>学号/身份证号</th>
                <th>手机号</th>
                <th>角色</th>
                <th>状态</th>
                <th>操作</th>
            </tr>
            <c:forEach items="${userList}" var="user">
                <tr>
                    <td>${user.id}</td>
                    <td>${user.username}</td>
                    <td>${user.realName}</td>
                    <td>${user.idCard}</td>
                    <td>${user.phone}</td>
                    <td>${user.role}</td>
                    <td>
                        <c:choose>
                            <c:when test="${user.status == 0}">未激活</c:when>
                            <c:when test="${user.status == 1}">正常</c:when>
                            <c:when test="${user.status == 2}">注销</c:when>
                        </c:choose>
                    </td>
                    <td>
                        <c:choose>
                            <c:when test="${user.status == 0}">
                                <a href="${pageContext.request.contextPath}/user/status?id=${user.id}&status=1">激活</a>
                            </c:when>
                            <c:when test="${user.status == 1}">
                                <a href="${pageContext.request.contextPath}/user/status?id=${user.id}&status=2">注销</a>
                            </c:when>
                            <c:when test="${user.status == 2}">
                                <a href="${pageContext.request.contextPath}/user/status?id=${user.id}&status=1">恢复</a>
                            </c:when>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${empty userList}">
                <tr><td colspan="8">暂无用户数据</td></tr>
            </c:if>
        </table>
        <c:if test="${not empty errorMsg}">
            <div class="error">${errorMsg}</div>
        </c:if>
    </div>
</body>
</html>