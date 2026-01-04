package com.service.impl;

import com.dao.BookDAO;
import com.dao.BorrowDAO;
import com.dao.impl.BookDAOImpl;
import com.dao.impl.BorrowDAOImpl;
import com.model.Borrow;
import com.model.Book; 
import com.service.BorrowService;
import com.util.DBUtil;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class BorrowServiceImpl implements BorrowService {
    private BorrowDAO borrowDAO = new BorrowDAOImpl();
    private BookDAO bookDAO = new BookDAOImpl();

    @Override
    public boolean borrowBook(Integer userId, Integer bookId) {
        Connection conn = null;
        try {
            conn = DBUtil.getConnection();
            conn.setAutoCommit(false); // 开启事务

            // 1. 校验库存
            Book book = bookDAO.getBookById(conn, bookId);
            if (book == null || book.getStock() <= 0) {
                conn.rollback();
                return false;
            }

            // 2. 创建借阅记录
            Borrow borrow = new Borrow();
            borrow.setUserId(userId);
            borrow.setBookId(bookId);
            borrow.setBorrowDate(new Date());
            // 预计归还日期：借阅30天后
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DAY_OF_MONTH, 30);
            borrow.setDueDate(cal.getTime());
            borrow.setStatus(0); // 未归还
            borrow.setFine(BigDecimal.ZERO);

            // 3. 插入借阅记录 + 减少库存
            borrowDAO.addBorrow(conn, borrow);
            bookDAO.decreaseStock(conn, bookId);

            conn.commit();
            return true;
        } catch (Exception e) {
            try {
                if (conn != null) conn.rollback();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (conn != null) {
                    conn.setAutoCommit(true);
                    DBUtil.close(conn);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean returnBook(Integer borrowId) {
        Connection conn = null;
        try {
            conn = DBUtil.getConnection();
            conn.setAutoCommit(false);

            // 1. 查询借阅记录
            Borrow borrow = borrowDAO.getBorrowById(conn, borrowId);
            if (borrow == null || borrow.getStatus() == 1) {
                conn.rollback();
                return false;
            }

            // 2. 计算超期天数和罚金
            Date returnDate = new Date();
            long overdueDays = (returnDate.getTime() - borrow.getDueDate().getTime()) / (1000 * 60 * 60 * 24);
            double fine = 0.0;
            if (overdueDays > 0) {
                fine = Math.min(overdueDays * 0.1, 20.0); // 超期1天0.1元，上限20元
            }

            // 3. 更新归还信息
            borrowDAO.updateReturnInfo(conn, borrowId, new Timestamp(returnDate.getTime()).toString(), fine, 1);
            // 4. 增加图书库存
            bookDAO.increaseStock(conn, borrow.getBookId());

            conn.commit();
            return true;
        } catch (Exception e) {
            try {
                if (conn != null) conn.rollback();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (conn != null) {
                    conn.setAutoCommit(true);
                    DBUtil.close(conn);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Borrow> listBorrowByUserId(Integer userId) {
        Connection conn = null;
        try {
            conn = DBUtil.getConnection();
            return borrowDAO.listBorrowByUserId(conn, userId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            DBUtil.close(conn);
        }
    }

    @Override
    public List<Borrow> listAllBorrows() {
        Connection conn = null;
        try {
            conn = DBUtil.getConnection();
            return borrowDAO.listAllBorrows(conn);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            DBUtil.close(conn);
        }
    }

    @Override
    public List<Borrow> listOverdueBorrows() {
        Connection conn = null;
        try {
            conn = DBUtil.getConnection();
            return borrowDAO.listOverdueBorrows(conn);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            DBUtil.close(conn);
        }
    }
}
