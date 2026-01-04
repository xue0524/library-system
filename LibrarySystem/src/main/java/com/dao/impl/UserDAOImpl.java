package com.dao.impl;

import com.dao.UserDAO;
import com.model.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    private QueryRunner qr = new QueryRunner();

    @Override
    public int addUser(Connection conn, User user) throws Exception {
        String sql = "INSERT INTO t_user(username,password,real_name,id_card,phone,role,status) VALUES (?,?,?,?,?,?,?)";
        return qr.update(conn, sql,
                user.getUsername(),
                user.getPassword(),
                user.getRealName(),
                user.getIdCard(),
                user.getPhone(),
                user.getRole(),
                user.getStatus());
    }

    @Override
    public User getUserByUsername(Connection conn, String username) throws Exception {
        String sql = "SELECT * FROM t_user WHERE username=?";
        return qr.query(conn, sql, new BeanHandler<>(User.class), username);
    }

    @Override
    public User getUserById(Connection conn, Integer id) throws Exception {
        String sql = "SELECT * FROM t_user WHERE id=?";
        return qr.query(conn, sql, new BeanHandler<>(User.class), id);
    }

    @Override
    public List<User> listAllUsers(Connection conn) throws Exception {
        String sql = "SELECT * FROM t_user ORDER BY create_time DESC";
        return qr.query(conn, sql, new BeanListHandler<>(User.class));
    }

    @Override
    public int updateUserStatus(Connection conn, Integer id, Integer status) throws Exception {
        String sql = "UPDATE t_user SET status=? WHERE id=?";
        return qr.update(conn, sql, status, id);
    }

    @Override
    public int updateUserInfo(Connection conn, User user) throws Exception {
        String sql = "UPDATE t_user SET real_name=?,id_card=?,phone=? WHERE id=?";
        return qr.update(conn, sql,
                user.getRealName(),
                user.getIdCard(),
                user.getPhone(),
                user.getId());
    }
}
