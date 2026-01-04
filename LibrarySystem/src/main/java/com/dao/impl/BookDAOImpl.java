package com.dao.impl;

import com.dao.BookDAO;
import com.model.Book;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.util.List;

public class BookDAOImpl implements BookDAO {
    private QueryRunner qr = new QueryRunner();

    @Override
    public int addBook(Connection conn, Book book) throws Exception {
        String sql = "INSERT INTO t_book(isbn,book_name,author,publisher,publish_date,category,stock) VALUES (?,?,?,?,?,?,?)";
        return qr.update(conn, sql,
                book.getIsbn(),
                book.getBookName(),
                book.getAuthor(),
                book.getPublisher(),
                book.getPublishDate(),
                book.getCategory(),
                book.getStock());
    }

    @Override
    public Book getBookByIsbn(Connection conn, String isbn) throws Exception {
        String sql = "SELECT * FROM t_book WHERE isbn=?";
        return qr.query(conn, sql, new BeanHandler<>(Book.class), isbn);
    }

    @Override
    public Book getBookById(Connection conn, Integer id) throws Exception {
        String sql = "SELECT * FROM t_book WHERE id=?";
        return qr.query(conn, sql, new BeanHandler<>(Book.class), id);
    }

    @Override
    public List<Book> searchBooks(Connection conn, String keyword, String type) throws Exception {  	
    	String sql = "";
        if ("isbn".equals(type)) {
            sql = "SELECT * FROM t_book WHERE isbn LIKE ?";
        } else if ("name".equals(type)) {
            sql = "SELECT * FROM t_book WHERE book_name LIKE ?";
        } else if ("author".equals(type)) {
            sql = "SELECT * FROM t_book WHERE author LIKE ?";
        } else {
            sql = "SELECT * FROM t_book WHERE isbn LIKE ? OR book_name LIKE ? OR author LIKE ?";
            return qr.query(conn, sql, new BeanListHandler<>(Book.class),
                    "%" + keyword + "%", "%" + keyword + "%", "%" + keyword + "%");
        }
        return qr.query(conn, sql, new BeanListHandler<>(Book.class), "%" + keyword + "%");
    }

    @Override
    public int updateBook(Connection conn, Book book) throws Exception {
        String sql = "UPDATE t_book SET isbn=?,book_name=?,author=?,publisher=?,publish_date=?,category=?,stock=? WHERE id=?";
        return qr.update(conn, sql,
                book.getIsbn(),
                book.getBookName(),
                book.getAuthor(),
                book.getPublisher(),
                book.getPublishDate(),
                book.getCategory(),
                book.getStock(),
                book.getId());
    }

    @Override
    public int deleteBook(Connection conn, Integer id) throws Exception {
        String sql = "DELETE FROM t_book WHERE id=?";
        return qr.update(conn, sql, id);
    }

    @Override
    public int decreaseStock(Connection conn, Integer id) throws Exception {
        String sql = "UPDATE t_book SET stock=stock-1 WHERE id=?";
        return qr.update(conn, sql, id);
    }

    @Override
    public int increaseStock(Connection conn, Integer id) throws Exception {
        String sql = "UPDATE t_book SET stock=stock+1 WHERE id=?";
        return qr.update(conn, sql, id);
    }

    @Override
    public List<Book> listAllBooks(Connection conn) throws Exception {
        String sql = "SELECT * FROM t_book ORDER BY create_time DESC";
        return qr.query(conn, sql, new BeanListHandler<>(Book.class));
    }
}
