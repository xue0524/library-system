package com.model;

import java.util.Date;

public class Stat {
    private Integer id;
    private Date statDate;
    private Integer borrowCount;
    private Integer userCount;
    private Date createTime;

    // 空参构造
    public Stat() {}

    // 全参构造
    public Stat(Integer id, Date statDate, Integer borrowCount, 
                Integer userCount, Date createTime) {
        this.id = id;
        this.statDate = statDate;
        this.borrowCount = borrowCount;
        this.userCount = userCount;
        this.createTime = createTime;
    }

    // Getter & Setter
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Date getStatDate() { return statDate; }
    public void setStatDate(Date statDate) { this.statDate = statDate; }
    public Integer getBorrowCount() { return borrowCount; }
    public void setBorrowCount(Integer borrowCount) { this.borrowCount = borrowCount; }
    public Integer getUserCount() { return userCount; }
    public void setUserCount(Integer userCount) { this.userCount = userCount; }
    public Date getCreateTime() { return createTime; }
    public void setCreateTime(Date createTime) { this.createTime = createTime; }
}
