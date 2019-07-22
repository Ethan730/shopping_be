package com.ethan.shopping.service;

import com.ethan.shopping.dto.CreateUserForm;
import com.ethan.shopping.model.User;
import com.ethan.shopping.utils.Result;

import java.util.List;

public interface UserService {
    // 通过用户名查找用户
    Result<User> findByUsername(String username);

    //创建用户/注册
    Result createUser(CreateUserForm form);

    //删除用户/用户注销
    Result deleteUser(Integer id);

    Result<List<User>> listAllUser();

    //更新用户信息
    Result editUser(User user);
}
