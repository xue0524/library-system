package com.service.impl;

import com.dao.StatDAO;
import com.dao.impl.StatDAOImpl;
import com.model.Stat;
import com.service.StatService;
import com.util.DBUtil;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

public class StatServiceImpl implements StatService {
    private StatDAO statDAO = new StatDAOImpl();

    @Override
    public boolean statBorrowCount(String date) {
        Connection conn = null;
        try {
            conn = DBUtil.getConnection();
            Stat stat = statDAO.getStatByDate(conn, date);
            if (stat == null) {
                // 新增当日统计记录
                stat = new Stat();
                stat.setStatDate(java.sql.Date.valueOf(date));
                stat.setBorrowCount(1);
                stat.setUserCount(0);
                statDAO.addStat(conn, stat);
            } else {
                // 更新借阅量
                statDAO.updateBorrowCount(conn, date);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            DBUtil.close(conn);
        }
    }

    @Override
    public boolean statUserCount(String date) {
        Connection conn = null;
        try {
            conn = DBUtil.getConnection();
            Stat stat = statDAO.getStatByDate(conn, date);
            if (stat == null) {
                // 新增当日统计记录
                stat = new Stat();
                stat.setStatDate(java.sql.Date.valueOf(date));
                stat.setBorrowCount(0);
                stat.setUserCount(1);
                statDAO.addStat(conn, stat);
            } else {
                // 更新用户数
                statDAO.updateUserCount(conn, date);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            DBUtil.close(conn);
        }
    }

    @Override
    public List<Stat> listMonthStat(String month) {
        Connection conn = null;
        try {
            conn = DBUtil.getConnection();
            return statDAO.listMonthStat(conn, month);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            DBUtil.close(conn);
        }
    }

    @Override
    public List<Object[]> listHotBooks() {
        Connection conn = null;
        try {
            conn = DBUtil.getConnection();
            return statDAO.listHotBooks(conn);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            DBUtil.close(conn);
        }
    }
}
