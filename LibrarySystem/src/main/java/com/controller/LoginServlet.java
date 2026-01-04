package com.controller;

import com.model.User;
import com.service.UserService;
import com.service.impl.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 解决中文乱码
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        User user = userService.login(username, password);
        if (user != null) {
            // 登录成功，存入Session
            HttpSession session = req.getSession();
            session.setAttribute("user", user);
            // 区分管理员/普通用户跳转
            if ("admin".equals(user.getRole())) {
                resp.sendRedirect(req.getContextPath() + "/jsp/admin/stat_manage.jsp");
            } else {
                resp.sendRedirect(req.getContextPath() + "/jsp/user/book_search.jsp");
            }
        } else {
            // 登录失败
            req.setAttribute("errorMsg", "用户名/密码错误或账号未激活");
            req.getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
        }
    }
}
