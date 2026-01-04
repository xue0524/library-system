package com.service;

import com.model.User;
import java.util.List;

public interface UserService {
    // 用户注册
    boolean register(User user);
    // 用户登录
    User login(String username, String password);
    // 查询所有用户
    List<User> listAllUsers();
    // 激活/注销用户
    boolean updateUserStatus(Integer id, Integer status);
    // 修改用户信息
    boolean updateUserInfo(User user);
    // 按ID查询用户
    User getUserById(Integer id);
}
