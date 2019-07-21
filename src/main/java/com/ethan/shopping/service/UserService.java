package com.ethan.shopping.service;

import com.ethan.shopping.model.User;
import com.ethan.shopping.utils.Result;

public interface UserService {
    // 通过用户名查找用户
    Result<User> findByUsername(String username);
}
