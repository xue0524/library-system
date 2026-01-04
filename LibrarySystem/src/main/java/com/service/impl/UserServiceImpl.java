package com.service.impl;

import com.dao.UserDAO;
import com.dao.impl.UserDAOImpl;
import com.model.User;
import com.service.UserService;
import com.util.DBUtil;
import com.util.MD5Util;

import java.sql.Connection;
import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDAO userDAO = new UserDAOImpl();

    @Override
    public boolean register(User user) {
        Connection conn = null;
        try {
            conn = DBUtil.getConnection();
            // 校验用户名是否存在
            if (userDAO.getUserByUsername(conn, user.getUsername()) != null) {
                return false;
            }
            // 密码加密
            user.setPassword(MD5Util.encrypt(user.getPassword()));
            // 默认角色user，状态0未激活
            user.setRole("user");
            user.setStatus(0);
            // 新增用户
            int rows = userDAO.addUser(conn, user);
            return rows > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            DBUtil.close(conn);
        }
    }

    @Override
    public User login(String username, String password) {
        Connection conn = null;
        try {
            conn = DBUtil.getConnection();
            User user = userDAO.getUserByUsername(conn, username);
            // 校验用户存在、密码正确、状态正常
            if (user != null && MD5Util.verify(password, user.getPassword()) && user.getStatus() == 1) {
                return user;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            DBUtil.close(conn);
        }
    }

    @Override
    public List<User> listAllUsers() {
        Connection conn = null;
        try {
            conn = DBUtil.getConnection();
            return userDAO.listAllUsers(conn);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            DBUtil.close(conn);
        }
    }

    @Override
    public boolean updateUserStatus(Integer id, Integer status) {
        Connection conn = null;
        try {
            conn = DBUtil.getConnection();
            int rows = userDAO.updateUserStatus(conn, id, status);
            return rows > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            DBUtil.close(conn);
        }
    }

    @Override
    public boolean updateUserInfo(User user) {
        Connection conn = null;
        try {
            conn = DBUtil.getConnection();
            int rows = userDAO.updateUserInfo(conn, user);
            return rows > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            DBUtil.close(conn);
        }
    }

    @Override
    public User getUserById(Integer id) {
        Connection conn = null;
        try {
            conn = DBUtil.getConnection();
            return userDAO.getUserById(conn, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            DBUtil.close(conn);
        }
    }
}