package com.controller;

import com.model.User;
import com.service.BorrowService;
import com.service.StatService;
import com.service.impl.BorrowServiceImpl;
import com.service.impl.StatServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet("/borrow/*")
public class BorrowServlet extends HttpServlet {
    private BorrowService borrowService = new BorrowServiceImpl();
    private StatService statService = new StatServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");

        String path = req.getPathInfo();
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        if ("/add".equals(path)) {
            // 借阅图书
            Integer bookId = Integer.parseInt(req.getParameter("bookId"));
            boolean result = borrowService.borrowBook(user.getId(), bookId);
            if (result) {
                // 统计当日借阅量
                String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
                statService.statBorrowCount(today);
                resp.sendRedirect(req.getContextPath() + "/borrow/my");
            } else {
                req.setAttribute("errorMsg", "借阅失败，库存不足");
                req.getRequestDispatcher("/jsp/user/book_search.jsp").forward(req, resp);
            }
        } else if ("/my".equals(path)) {
            // 我的借阅记录
            List<com.model.Borrow> borrowList = borrowService.listBorrowByUserId(user.getId());
            req.setAttribute("borrowList", borrowList);
            req.getRequestDispatcher("/jsp/user/my_borrow.jsp").forward(req, resp);
        } else if ("/return".equals(path)) {
            // 归还图书
            Integer borrowId = Integer.parseInt(req.getParameter("borrowId"));
            boolean result = borrowService.returnBook(borrowId);
            if (result) {
                resp.sendRedirect(req.getContextPath() + "/borrow/my");
            } else {
                req.setAttribute("errorMsg", "归还失败");
                req.getRequestDispatcher("/jsp/user/my_borrow.jsp").forward(req, resp);
            }
        } else if ("/list".equals(path)) {
            // 管理员查询所有借阅记录
            List<com.model.Borrow> borrowList = borrowService.listAllBorrows();
            req.setAttribute("borrowList", borrowList);
            req.getRequestDispatcher("/jsp/admin/borrow_manage.jsp").forward(req, resp);
        } else if ("/overdue".equals(path)) {
            // 管理员查询超期未还记录
            List<com.model.Borrow> overdueList = borrowService.listOverdueBorrows();
            req.setAttribute("overdueList", overdueList);
            req.getRequestDispatcher("/jsp/admin/borrow_manage.jsp").forward(req, resp);
        }
    }
}