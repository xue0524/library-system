package com.service.impl;

import com.dao.BookDAO;
import com.dao.impl.BookDAOImpl;
import com.model.Book;
import com.service.BookService;
import com.util.DBUtil;

import java.sql.Connection;
import java.util.List;

public class BookServiceImpl implements BookService {
    private BookDAO bookDAO = new BookDAOImpl();

    @Override
    public boolean addBook(Book book) {
        Connection conn = null;
        try {
            conn = DBUtil.getConnection();
            // 校验ISBN唯一
            if (bookDAO.getBookByIsbn(conn, book.getIsbn()) != null) {
                return false;
            }
            int rows = bookDAO.addBook(conn, book);
            return rows > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            DBUtil.close(conn);
        }
    }

    @Override
    public Book getBookById(Integer id) {
        Connection conn = null;
        try {
            conn = DBUtil.getConnection();
            return bookDAO.getBookById(conn, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            DBUtil.close(conn);
        }
    }

    @Override
    public List<Book> searchBooks(String keyword, String type) {
        Connection conn = null;
        try {
            conn = DBUtil.getConnection();
            return bookDAO.searchBooks(conn, keyword, type);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            DBUtil.close(conn);
        }
    }

    @Override
    public boolean updateBook(Book book) {
        Connection conn = null;
        try {
            conn = DBUtil.getConnection();
            int rows = bookDAO.updateBook(conn, book);
            return rows > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            DBUtil.close(conn);
        }
    }

    @Override
    public boolean deleteBook(Integer id) {
        Connection conn = null;
        try {
            conn = DBUtil.getConnection();
            int rows = bookDAO.deleteBook(conn, id);
            return rows > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            DBUtil.close(conn);
        }
    }

    @Override
    public List<Book> listAllBooks() {
        Connection conn = null;
        try {
            conn = DBUtil.getConnection();
            return bookDAO.listAllBooks(conn);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            DBUtil.close(conn);
        }
    }
}
