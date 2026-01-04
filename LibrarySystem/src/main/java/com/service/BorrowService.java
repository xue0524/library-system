package com.service;

import com.model.Borrow;
import java.util.List;

public interface BorrowService {
    // 借阅图书
    boolean borrowBook(Integer userId, Integer bookId);
    // 归还图书
    boolean returnBook(Integer borrowId);
    // 按用户ID查询借阅记录
    List<Borrow> listBorrowByUserId(Integer userId);
    // 查询所有借阅记录
    List<Borrow> listAllBorrows();
    // 查询超期未还记录
    List<Borrow> listOverdueBorrows();
}
