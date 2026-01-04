<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>我的借阅</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <div class="header">
        <h1>我的借阅</h1>
        <div class="nav">
            <a href="${pageContext.request.contextPath}/book/search">图书查询</a>
            <a href="${pageContext.request.contextPath}/borrow/my">我的借阅</a>
            <a href="${pageContext.request.contextPath}/user/center">个人中心</a>
            <a href="${pageContext.request.contextPath}/login">退出登录</a>
        </div>
    </div>

    <div class="borrow-list">
        <table border="1">
            <tr>
                <th>图书ID</th>
                <th>借阅日期</th>
                <th>预计归还日期</th>
                <th>实际归还日期</th>
                <th>罚金(元)</th>
                <th>状态</th>
                <th>操作</th>
            </tr>
            <c:forEach items="${borrowList}" var="borrow">
                <tr>
                    <td>${borrow.bookId}</td>
                    <td><fmt:formatDate value="${borrow.borrowDate}" pattern="yyyy-MM-dd"/></td>
                    <td><fmt:formatDate value="${borrow.dueDate}" pattern="yyyy-MM-dd"/></td>
                    <td>${borrow.returnDate == null ? '未归还' : borrow.returnDate}</td>
                    <td>${borrow.fine}</td>
                    <td>
                        <c:choose>
                            <c:when test="${borrow.status == 0}">未归还</c:when>
                            <c:when test="${borrow.status == 1}">已归还</c:when>
                            <c:when test="${borrow.status == 2}">超期未还</c:when>
                        </c:choose>
                    </td>
                    <td>
                        <c:if test="${borrow.status == 0}">
                            <a href="${pageContext.request.contextPath}/borrow/return?borrowId=${borrow.id}">归还</a>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${empty borrowList}">
                <tr><td colspan="7">暂无借阅记录</td></tr>
            </c:if>
        </table>
        <c:if test="${not empty errorMsg}">
            <div class="error">${errorMsg}</div>
        </c:if>
    </div>
</body>
</html>