<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>借阅管理</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <div class="header">
        <h1>借阅管理</h1>
        <div class="nav">
            <a href="${pageContext.request.contextPath}/book/list">图书管理</a>
            <a href="${pageContext.request.contextPath}/user/list">用户管理</a>
            <a href="${pageContext.request.contextPath}/borrow/list">借阅管理</a>
            <a href="${pageContext.request.contextPath}/stat/month">统计分析</a>
            <a href="${pageContext.request.contextPath}/login">退出登录</a>
        </div>
        <div class="sub-nav">
            <a href="${pageContext.request.contextPath}/borrow/list">所有借阅</a>
            <a href="${pageContext.request.contextPath}/borrow/overdue">超期未还</a>
        </div>
    </div>

    <div class="borrow-list">
        <table border="1">
            <tr>
                <th>记录ID</th>
                <th>用户ID</th>
                <th>图书ID</th>
                <th>借阅日期</th>
                <th>预计归还日期</th>
                <th>实际归还日期</th>
                <th>罚金(元)</th>
                <th>状态</th>
            </tr>
            <c:choose>
                <c:when test="${not empty borrowList}">
                    <c:forEach items="${borrowList}" var="borrow">
                        <tr>
                            <td>${borrow.id}</td>
                            <td>${borrow.userId}</td>
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
                        </tr>
                    </c:forEach>
                </c:when>
                <c:when test="${not empty overdueList}">
                    <c:forEach items="${overdueList}" var="borrow">
                        <tr>
                            <td>${borrow.id}</td>
                            <td>${borrow.userId}</td>
                            <td>${borrow.bookId}</td>
                            <td><fmt:formatDate value="${borrow.borrowDate}" pattern="yyyy-MM-dd"/></td>
                            <td><fmt:formatDate value="${borrow.dueDate}" pattern="yyyy-MM-dd"/></td>
                            <td>未归还</td>
                            <td>${borrow.fine}</td>
                            <td style="color:red;">超期未还</td>
                        </tr>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <tr><td colspan="8">暂无借阅记录</td></tr>
                </c:otherwise>
            </c:choose>
        </table>
    </div>
</body>
</html>