package com.dao.impl;

import com.dao.StatDAO;
import com.model.Stat;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;

import java.sql.Connection;
import java.util.List;

public class StatDAOImpl implements StatDAO {
    private QueryRunner qr = new QueryRunner();

    @Override
    public int addStat(Connection conn, Stat stat) throws Exception {
        String sql = "INSERT INTO t_statistics(stat_date,borrow_count,user_count) VALUES (?,?,?)";
        return qr.update(conn, sql, stat.getStatDate(), stat.getBorrowCount(), stat.getUserCount());
    }

    @Override
    public Stat getStatByDate(Connection conn, String date) throws Exception {
        String sql = "SELECT * FROM t_statistics WHERE stat_date=?";
        return qr.query(conn, sql, new BeanHandler<>(Stat.class), date);
    }

    @Override
    public List<Stat> listMonthStat(Connection conn, String month) throws Exception {
        String sql = "SELECT * FROM t_statistics WHERE stat_date LIKE ? ORDER BY stat_date";
        return qr.query(conn, sql, new BeanListHandler<>(Stat.class), month + "%");
    }

    @Override
    public int updateBorrowCount(Connection conn, String date) throws Exception {
        String sql = "UPDATE t_statistics SET borrow_count=borrow_count+1 WHERE stat_date=?";
        return qr.update(conn, sql, date);
    }

    @Override
    public int updateUserCount(Connection conn, String date) throws Exception {
        String sql = "UPDATE t_statistics SET user_count=user_count+1 WHERE stat_date=?";
        return qr.update(conn, sql, date);
    }

    @Override
    public List<Object[]> listHotBooks(Connection conn) throws Exception {
        String sql = "SELECT b.book_name,COUNT(br.id) AS borrow_num " +
                "FROM t_borrow br LEFT JOIN t_book b ON br.book_id=b.id " +
                "GROUP BY br.book_id ORDER BY borrow_num DESC LIMIT 10";
        return qr.query(conn, sql, new ColumnListHandler<>());
    }
}
