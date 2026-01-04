<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>图书管理</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <div class="header">
        <h1>图书管理</h1>
        <div class="nav">
            <a href="${pageContext.request.contextPath}/book/list">图书管理</a>
            <a href="${pageContext.request.contextPath}/user/list">用户管理</a>
            <a href="${pageContext.request.contextPath}/borrow/list">借阅管理</a>
            <a href="${pageContext.request.contextPath}/stat/month">统计分析</a>
            <a href="${pageContext.request.contextPath}/login">退出登录</a>
        </div>
    </div>

    <div class="book-add">
        <h3>新增图书</h3>
        <form action="${pageContext.request.contextPath}/book/addSubmit" method="post">
            <div class="form-item">
                <label>ISBN：</label>
                <input type="text" name="isbn" required>
            </div>
            <div class="form-item">
                <label>书名：</label>
                <input type="text" name="bookName" required>
            </div>
            <div class="form-item">
                <label>作者：</label>
                <input type="text" name="author" required>
            </div>
            <div class="form-item">
                <label>出版社：</label>
                <input type="text" name="publisher" required>
            </div>
            <div class="form-item">
                <label>出版日期：</label>
                <input type="date" name="publishDate" required>
            </div>
            <div class="form-item">
                <label>分类：</label>
                <input type="text" name="category" required>
            </div>
            <div class="form-item">
                <label>库存：</label>
                <input type="number" name="stock" min="0" required>
            </div>
            <div class="form-item">
                <button type="submit">新增</button>
            </div>
        </form>
        <c:if test="${not empty errorMsg}">
            <div class="error">${errorMsg}</div>
        </c:if>
    </div>

    <div class="book-list">
        <h3>图书列表</h3>
        <table border="1">
            <tr>
                <th>ID</th>
                <th>ISBN</th>
                <th>书名</th>
                <th>作者</th>
                <th>出版社</th>
                <th>出版日期</th>
                <th>分类</th>
                <th>库存</th>
                <th>操作</th>
            </tr>
            <c:forEach items="${bookList}" var="book">
                <tr>
                    <td>${book.id}</td>
                    <td>${book.isbn}</td>
                    <td>${book.bookName}</td>
                    <td>${book.author}</td>
                    <td>${book.publisher}</td>
                    <td><fmt:formatDate value="${book.publishDate}" pattern="yyyy-MM-dd"/></td>
                    <td>${book.category}</td>
                    <td>${book.stock}</td>
                    <td>
                        <a href="javascript:void(0);" onclick="editBook(${book.id})">编辑</a>
                        <a href="${pageContext.request.contextPath}/book/delete?id=${book.id}" onclick="return confirm('确认删除？')">删除</a>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${empty bookList}">
                <tr><td colspan="9">暂无图书数据</td></tr>
            </c:if>
        </table>
    </div>

    <!-- 编辑图书弹窗（简易版，可结合JS实现） -->
    <div id="editModal" style="display:none;">
        <form action="${pageContext.request.contextPath}/book/updateSubmit" method="post">
            <input type="hidden" name="id" id="editId">
            <!-- 同新增表单字段，略 -->
            <button type="submit">保存修改</button>
        </form>
    </div>

    <script src="${pageContext.request.contextPath}/js/common.js"></script>
</body>
</html>