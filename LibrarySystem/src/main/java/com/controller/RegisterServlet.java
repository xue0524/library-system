package com.controller;

import com.model.User;
import com.service.StatService;
import com.service.UserService;
import com.service.impl.StatServiceImpl;
import com.service.impl.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();
    private StatService statService = new StatServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String realName = req.getParameter("realName");
        String idCard = req.getParameter("idCard");
        String phone = req.getParameter("phone");

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setRealName(realName);
        user.setIdCard(idCard);
        user.setPhone(phone);

        boolean result = userService.register(user);
        if (result) {
            // 注册成功，统计当日用户数
            String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            statService.statUserCount(today);
            req.setAttribute("successMsg", "注册成功，请等待管理员激活账号");
            req.getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
        } else {
            req.setAttribute("errorMsg", "用户名已存在，注册失败");
            req.getRequestDispatcher("/jsp/register.jsp").forward(req, resp);
        }
    }
}