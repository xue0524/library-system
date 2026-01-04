package com.dao;

import com.model.Book;
import java.sql.Connection;
import java.util.List;

public interface BookDAO {
    // 新增图书
    int addBook(Connection conn, Book book) throws Exception;
    // 按ISBN查询
    Book getBookByIsbn(Connection conn, String isbn) throws Exception;
    // 按ID查询
    Book getBookById(Connection conn, Integer id) throws Exception;
    // 模糊查询
    List<Book> searchBooks(Connection conn, String keyword, String type) throws Exception;
    // 更新图书
    int updateBook(Connection conn, Book book) throws Exception;
    // 删除图书
    int deleteBook(Connection conn, Integer id) throws Exception;
    // 减少库存
    int decreaseStock(Connection conn, Integer id) throws Exception;
    // 增加库存
    int increaseStock(Connection conn, Integer id) throws Exception;
    // 查询所有图书
    List<Book> listAllBooks(Connection conn) throws Exception;
}
