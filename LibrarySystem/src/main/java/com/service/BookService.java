package com.service;

import com.model.Book;
import java.util.List;

public interface BookService {
    // 新增图书
    boolean addBook(Book book);
    // 按ID查询图书
    Book getBookById(Integer id);
    // 模糊查询图书
    List<Book> searchBooks(String keyword, String type);
    // 更新图书
    boolean updateBook(Book book);
    // 删除图书
    boolean deleteBook(Integer id);
    // 查询所有图书
    List<Book> listAllBooks();
}
