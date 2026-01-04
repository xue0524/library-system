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
import java.util.List;

@WebServlet("/user/*")
public class UserServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");

        String path = req.getPathInfo();
        HttpSession session = req.getSession();
        User loginUser = (User) session.getAttribute("user");

        if ("/list".equals(path)) {
            // 管理员查询所有用户
            List<User> userList = userService.listAllUsers();
            req.setAttribute("userList", userList);
            req.getRequestDispatcher("/jsp/admin/user_manage.jsp").forward(req, resp);
        } else if ("/status".equals(path)) {
            // 管理员修改用户状态
            Integer id = Integer.parseInt(req.getParameter("id"));
            Integer status = Integer.parseInt(req.getParameter("status"));
            boolean result = userService.updateUserStatus(id, status);
            if (result) {
                resp.sendRedirect(req.getContextPath() + "/user/list");
            } else {
                req.setAttribute("errorMsg", "状态修改失败");
                req.getRequestDispatcher("/jsp/admin/user_manage.jsp").forward(req, resp);
            }
        } else if ("/center".equals(path)) {
            // 普通用户个人中心
            User user = userService.getUserById(loginUser.getId());
            req.setAttribute("userInfo", user);
            req.getRequestDispatcher("/jsp/user/user_center.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");

        String path = req.getPathInfo();
        HttpSession session = req.getSession();
        User loginUser = (User) session.getAttribute("user");

        if ("/update".equals(path)) {
            // 普通用户修改个人信息
            User user = new User();
            user.setId(loginUser.getId());
            user.setRealName(req.getParameter("realName"));
            user.setIdCard(req.getParameter("idCard"));
            user.setPhone(req.getParameter("phone"));

            boolean result = userService.updateUserInfo(user);
            if (result) {
                // 更新Session中的用户信息
                session.setAttribute("user", userService.getUserById(loginUser.getId()));
                req.setAttribute("successMsg", "信息修改成功");
            } else {
                req.setAttribute("errorMsg", "信息修改失败");
            }
            req.getRequestDispatcher("/jsp/user/user_center.jsp").forward(req, resp);
        }
    }
}