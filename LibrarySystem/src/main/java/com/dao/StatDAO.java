package com.dao;

import com.model.Stat;
import java.sql.Connection;
import java.util.List;

public interface StatDAO {
    // 新增统计记录
    int addStat(Connection conn, Stat stat) throws Exception;
    // 按日期查询统计
    Stat getStatByDate(Connection conn, String date) throws Exception;
    // 查询月度统计
    List<Stat> listMonthStat(Connection conn, String month) throws Exception;
    // 更新当日借阅量
    int updateBorrowCount(Connection conn, String date) throws Exception;
    // 更新当日用户数
    int updateUserCount(Connection conn, String date) throws Exception;
    // 查询热门图书TOP10
    List<Object[]> listHotBooks(Connection conn) throws Exception;
}