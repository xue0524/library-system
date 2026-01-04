package com.model;

import java.math.BigDecimal;
import java.util.Date;

public class Borrow {
    private Integer id;
    private Integer userId;
    private Integer bookId;
    private Date borrowDate;
    private Date dueDate;
    private Date returnDate;
    private BigDecimal fine;
    private Integer status; // 0-未归还 1-已归还 2-超期未还

    // 空参构造
    public Borrow() {}

    // 全参构造
    public Borrow(Integer id, Integer userId, Integer bookId, Date borrowDate, 
                  Date dueDate, Date returnDate, BigDecimal fine, Integer status) {
        this.id = id;
        this.userId = userId;
        this.bookId = bookId;
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
        this.fine = fine;
        this.status = status;
    }

    // Getter & Setter
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Integer getUserId() { return userId; }
    public void setUserId(Integer userId) { this.userId = userId; }
    public Integer getBookId() { return bookId; }
    public void setBookId(Integer bookId) { this.bookId = bookId; }
    public Date getBorrowDate() { return borrowDate; }
    public void setBorrowDate(Date borrowDate) { this.borrowDate = borrowDate; }
    public Date getDueDate() { return dueDate; }
    public void setDueDate(Date dueDate) { this.dueDate = dueDate; }
    public Date getReturnDate() { return returnDate; }
    public void setReturnDate(Date returnDate) { this.returnDate = returnDate; }
    public BigDecimal getFine() { return fine; }
    public void setFine(BigDecimal fine) { this.fine = fine; }
    public Integer getStatus() { return status; }
    public void setStatus(Integer status) { this.status = status; }
}
