<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>图书查询</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <div class="header">
        <h1>图书查询</h1>
        <div class="nav">
            <a href="${pageContext.request.contextPath}/book/search">图书查询</a>
            <a href="${pageContext.request.contextPath}/borrow/my">我的借阅</a>
            <a href="${pageContext.request.contextPath}/user/center">个人中心</a>
            <a href="${pageContext.request.contextPath}/login">退出登录</a>
        </div>
    </div>

    <div class="search-container">
        <form action="${pageContext.request.contextPath}/book/search" method="get">
            <input type="text" name="keyword" placeholder="输入ISBN、书名或作者" value="${keyword}">
            <select name="type">
                <option value="all">全部</option>
                <option value="isbn">ISBN</option>
                <option value="name">书名</option>
                <option value="author">作者</option>
            </select>
            <button type="submit">查询</button>
        </form>
    </div>

    <div class="book-list">
        <table border="1">
            <tr>
                <th>ISBN</th>
                <th>书名</th>
                <th>作者</th>
                <th>出版社</th>
                <th>分类</th>
                <th>库存</th>
                <th>操作</th>
            </tr>
            <c:forEach items="${bookList}" var="book">
                <tr>
                    <td>${book.isbn}</td>
                    <td>${book.bookName}</td>
                    <td>${book.author}</td>
                    <td>${book.publisher}</td>
                    <td>${book.category}</td>
                    <td>${book.stock > 0 ? book.stock : '无库存'}</td>
                    <td>
                        <c:if test="${book.stock > 0}">
                            <a href="${pageContext.request.contextPath}/borrow/add?bookId=${book.id}">借阅</a>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${empty bookList}">
                <tr>
                    <td colspan="7" align="center">暂无图书数据</td>
                </tr>
            </c:if>
        </table>
    </div>
</body>
</html>