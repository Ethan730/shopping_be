package com.ethan.shopping.service.impl;

import com.ethan.shopping.mapper.UserMapper;
import com.ethan.shopping.model.User;
import com.ethan.shopping.service.UserService;
import com.ethan.shopping.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    public Result<User> findByUsername(String username){
        User user = userMapper.selectByUsername(username);
        if(user == null){
            return Result.fail("用户名错误");
        }
        return Result.success(user);
    }
}
