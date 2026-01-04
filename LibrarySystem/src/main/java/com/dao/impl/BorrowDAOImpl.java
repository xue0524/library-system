package com.dao.impl;

import com.dao.BorrowDAO;
import com.model.Borrow;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.util.List;

public class BorrowDAOImpl implements BorrowDAO {
    private QueryRunner qr = new QueryRunner();

    @Override
    public int addBorrow(Connection conn, Borrow borrow) throws Exception {
        String sql = "INSERT INTO t_borrow(user_id,book_id,borrow_date,due_date,status) VALUES (?,?,?,?,?)";
        return qr.update(conn, sql,
                borrow.getUserId(),
                borrow.getBookId(),
                borrow.getBorrowDate(),
                borrow.getDueDate(),
                borrow.getStatus());
    }

    @Override
    public List<Borrow> listBorrowByUserId(Connection conn, Integer userId) throws Exception {
        String sql = "SELECT * FROM t_borrow WHERE user_id=? ORDER BY borrow_date DESC";
        return qr.query(conn, sql, new BeanListHandler<>(Borrow.class), userId);
    }

    @Override
    public Borrow getBorrowById(Connection conn, Integer id) throws Exception {
        String sql = "SELECT * FROM t_borrow WHERE id=?";
        return qr.query(conn, sql, new BeanHandler<>(Borrow.class), id);
    }

    @Override
    public int updateReturnInfo(Connection conn, Integer id, String returnDate, Double fine, Integer status) throws Exception {
        String sql = "UPDATE t_borrow SET return_date=?,fine=?,status=? WHERE id=?";
        return qr.update(conn, sql, returnDate, fine, status, id);
    }

    @Override
    public List<Borrow> listAllBorrows(Connection conn) throws Exception {
        String sql = "SELECT * FROM t_borrow ORDER BY borrow_date DESC";
        return qr.query(conn, sql, new BeanListHandler<>(Borrow.class));
    }

    @Override
    public List<Borrow> listOverdueBorrows(Connection conn) throws Exception {
        String sql = "SELECT * FROM t_borrow WHERE status=0 AND due_date < NOW()";
        return qr.query(conn, sql, new BeanListHandler<>(Borrow.class));
    }
}
