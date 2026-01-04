<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>统计分析</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <div class="header">
        <h1>统计分析</h1>
        <div class="nav">
            <a href="${pageContext.request.contextPath}/book/list">图书管理</a>
            <a href="${pageContext.request.contextPath}/user/list">用户管理</a>
            <a href="${pageContext.request.contextPath}/borrow/list">借阅管理</a>
            <a href="${pageContext.request.contextPath}/stat/month">统计分析</a>
            <a href="${pageContext.request.contextPath}/login">退出登录</a>
        </div>
        <div class="sub-nav">
            <a href="${pageContext.request.contextPath}/stat/month">月度统计</a>
            <a href="${pageContext.request.contextPath}/stat/hot">热门图书TOP10</a>
        </div>
    </div>

    <div class="stat-container">
        <!-- 月度统计 -->
        <c:if test="${not empty statList}">
            <h3>月度借阅/用户统计（${param.month}）</h3>
            <form action="${pageContext.request.contextPath}/stat/month" method="get">
                <input type="month" name="month" value="${param.month}" required>
                <button type="submit">查询</button>
            </form>
            <table border="1">
                <tr>
                    <th>日期</th>
                    <th>当日借阅量</th>
                    <th>当日新增用户数</th>
                </tr>
                <c:forEach items="${statList}" var="stat">
                    <tr>
                        <td><fmt:formatDate value="${stat.statDate}" pattern="yyyy-MM-dd"/></td>
                        <td>${stat.borrowCount}</td>
                        <td>${stat.userCount}</td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>

        <!-- 热门图书TOP10 -->
        <c:if test="${not empty hotBooks}">
            <h3>热门图书TOP10</h3>
            <table border="1">
                <tr>
                    <th>排名</th>
                    <th>图书名称</th>
                    <th>借阅次数</th>
                </tr>
                <c:forEach items="${hotBooks}" var="book" varStatus="vs">
                    <tr>
                        <td>${vs.index + 1}</td>
                        <td>${book[0]}</td>
                        <td>${book[1]}</td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>

        <!-- 默认显示月度统计查询入口 -->
        <c:if test="${empty statList and empty hotBooks}">
            <form action="${pageContext.request.contextPath}/stat/month" method="get">
                <input type="month" name="month" required>
                <button type="submit">查询月度统计</button>
            </form>
        </c:if>
    </div>
</body>
</html>