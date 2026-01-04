package com.model;

import java.util.Date;

public class User {
    private Integer id;
    private String username;
    private String password;
    private String realName;
    private String idCard;
    private String phone;
    private String role; // admin/user
    private Integer status; // 0-未激活 1-正常 2-注销
    private Date createTime;

    // 空参构造
    public User() {}

    // 全参构造
    public User(Integer id, String username, String password, String realName, 
                String idCard, String phone, String role, Integer status, Date createTime) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.realName = realName;
        this.idCard = idCard;
        this.phone = phone;
        this.role = role;
        this.status = status;
        this.createTime = createTime;
    }

    // Getter & Setter
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getRealName() { return realName; }
    public void setRealName(String realName) { this.realName = realName; }
    public String getIdCard() { return idCard; }
    public void setIdCard(String idCard) { this.idCard = idCard; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    public Integer getStatus() { return status; }
    public void setStatus(Integer status) { this.status = status; }
    public Date getCreateTime() { return createTime; }
    public void setCreateTime(Date createTime) { this.createTime = createTime; }
}