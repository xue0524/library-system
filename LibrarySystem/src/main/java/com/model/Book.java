package com.model;

import java.util.Date;

public class Book {
    private Integer id;
    private String isbn;
    private String bookName;
    private String author;
    private String publisher;
    private Date publishDate;
    private String category;
    private Integer stock;
    private Date createTime;
    private Date updateTime;

    // 空参构造
    public Book() {}

    // 全参构造
    public Book(Integer id, String isbn, String bookName, String author, 
                String publisher, Date publishDate, String category, 
                Integer stock, Date createTime, Date updateTime) {
        this.id = id;
        this.isbn = isbn;
        this.bookName = bookName;
        this.author = author;
        this.publisher = publisher;
        this.publishDate = publishDate;
        this.category = category;
        this.stock = stock;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    // Getter & Setter
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }
    public String getBookName() { return bookName; }
    public void setBookName(String bookName) { this.bookName = bookName; }
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }
    public String getPublisher() { return publisher; }
    public void setPublisher(String publisher) { this.publisher = publisher; }
    public Date getPublishDate() { return publishDate; }
    public void setPublishDate(Date publishDate) { this.publishDate = publishDate; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public Integer getStock() { return stock; }
    public void setStock(Integer stock) { this.stock = stock; }
    public Date getCreateTime() { return createTime; }
    public void setCreateTime(Date createTime) { this.createTime = createTime; }
    public Date getUpdateTime() { return updateTime; }
    public void setUpdateTime(Date updateTime) { this.updateTime = updateTime; }
}
