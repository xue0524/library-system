package com.controller;

import com.service.StatService;
import com.service.impl.StatServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/stat/*")
public class StatServlet extends HttpServlet {
    private StatService statService = new StatServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");

        String path = req.getPathInfo();
        if ("/month".equals(path)) {
            // 月度统计
            String month = req.getParameter("month");
            List<com.model.Stat> statList = statService.listMonthStat(month);
            req.setAttribute("statList", statList);
            req.getRequestDispatcher("/jsp/admin/stat_manage.jsp").forward(req, resp);
        } else if ("/hot".equals(path)) {
            // 热门图书TOP10
            List<Object[]> hotBooks = statService.listHotBooks();
            req.setAttribute("hotBooks", hotBooks);
            req.getRequestDispatcher("/jsp/admin/stat_manage.jsp").forward(req, resp);
        }
    }
}
