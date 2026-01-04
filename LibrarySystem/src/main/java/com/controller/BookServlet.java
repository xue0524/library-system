package com.controller;

import com.model.Book;
import com.service.BookService;
import com.service.impl.BookServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@WebServlet("/book/*")
public class BookServlet extends HttpServlet {
    private BookService bookService = new BookServiceImpl();
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");

        String path = req.getPathInfo();
        if ("/add".equals(path)) {
            // 跳转到新增图书页面
            req.getRequestDispatcher("/jsp/admin/book_manage.jsp").forward(req, resp);
        } else if ("/search".equals(path)) {
            // 图书查询
            String keyword = req.getParameter("keyword");
            String type = req.getParameter("type");
            List<Book> bookList = bookService.searchBooks(keyword, type);
            req.setAttribute("bookList", bookList);
            req.setAttribute("keyword", keyword);
            req.getRequestDispatcher("/jsp/user/book_search.jsp").forward(req, resp);
        } else if ("/list".equals(path)) {
            // 管理员查询所有图书
            List<Book> bookList = bookService.listAllBooks();
            req.setAttribute("bookList", bookList);
            req.getRequestDispatcher("/jsp/admin/book_manage.jsp").forward(req, resp);
        } else if ("/delete".equals(path)) {
            // 删除图书
            Integer id = Integer.parseInt(req.getParameter("id"));
            boolean result = bookService.deleteBook(id);
            if (result) {
                resp.sendRedirect(req.getContextPath() + "/book/list");
            } else {
                req.setAttribute("errorMsg", "删除失败");
                req.getRequestDispatcher("/jsp/admin/book_manage.jsp").forward(req, resp);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");

        String path = req.getPathInfo();
        if ("/addSubmit".equals(path)) {
            // 新增图书提交
            Book book = new Book();
            book.setIsbn(req.getParameter("isbn"));
            book.setBookName(req.getParameter("bookName"));
            book.setAuthor(req.getParameter("author"));
            book.setPublisher(req.getParameter("publisher"));
            try {
                book.setPublishDate(sdf.parse(req.getParameter("publishDate")));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            book.setCategory(req.getParameter("category"));
            book.setStock(Integer.parseInt(req.getParameter("stock")));

            boolean result = bookService.addBook(book);
            if (result) {
                resp.sendRedirect(req.getContextPath() + "/book/list");
            } else {
                req.setAttribute("errorMsg", "ISBN重复，新增失败");
                req.getRequestDispatcher("/jsp/admin/book_manage.jsp").forward(req, resp);
            }
        } else if ("/updateSubmit".equals(path)) {
            // 更新图书提交
            Book book = new Book();
            book.setId(Integer.parseInt(req.getParameter("id")));
            book.setIsbn(req.getParameter("isbn"));
            book.setBookName(req.getParameter("bookName"));
            book.setAuthor(req.getParameter("author"));
            book.setPublisher(req.getParameter("publisher"));
            try {
                book.setPublishDate(sdf.parse(req.getParameter("publishDate")));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            book.setCategory(req.getParameter("category"));
            book.setStock(Integer.parseInt(req.getParameter("stock")));

            boolean result = bookService.updateBook(book);
            if (result) {
                resp.sendRedirect(req.getContextPath() + "/book/list");
            } else {
                req.setAttribute("errorMsg", "更新失败");
                req.getRequestDispatcher("/jsp/admin/book_manage.jsp").forward(req, resp);
            }
        }
    }
}
