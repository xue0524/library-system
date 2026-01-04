package com.dao;

import com.model.Borrow;
import java.sql.Connection;
import java.util.List;

public interface BorrowDAO {
    // 新增借阅记录
    int addBorrow(Connection conn, Borrow borrow) throws Exception;
    // 按用户ID查询借阅记录
    List<Borrow> listBorrowByUserId(Connection conn, Integer userId) throws Exception;
    // 按ID查询借阅记录
    Borrow getBorrowById(Connection conn, Integer id) throws Exception;
    // 更新归还信息
    int updateReturnInfo(Connection conn, Integer id, String returnDate, Double fine, Integer status) throws Exception;
    // 查询所有借阅记录
    List<Borrow> listAllBorrows(Connection conn) throws Exception;
    // 查询超期未还记录
    List<Borrow> listOverdueBorrows(Connection conn) throws Exception;
}
