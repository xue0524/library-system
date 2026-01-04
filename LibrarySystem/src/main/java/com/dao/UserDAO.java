package com.dao;

import com.model.User;
import java.sql.Connection;
import java.util.List;

public interface UserDAO {
    // 新增用户
    int addUser(Connection conn, User user) throws Exception;
    // 按用户名查询
    User getUserByUsername(Connection conn, String username) throws Exception;
    // 按ID查询
    User getUserById(Connection conn, Integer id) throws Exception;
    // 查询所有用户
    List<User> listAllUsers(Connection conn) throws Exception;
    // 更新用户状态
    int updateUserStatus(Connection conn, Integer id, Integer status) throws Exception;
    // 更新用户信息
    int updateUserInfo(Connection conn, User user) throws Exception;
}
