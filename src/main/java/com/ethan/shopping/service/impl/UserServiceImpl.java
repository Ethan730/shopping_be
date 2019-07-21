package com.ethan.shopping.service.impl;

import com.ethan.shopping.dto.CreateUserForm;
import com.ethan.shopping.mapper.UserMapper;
import com.ethan.shopping.model.User;
import com.ethan.shopping.service.UserService;
import com.ethan.shopping.utils.PasswordUtil;
import com.ethan.shopping.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    public Result<User> findByUsername(String username) {
        User user = userMapper.selectByUsername(username);
        if (user == null) {
            return Result.fail("用户名错误");
        }
        return Result.success(user);
    }

    public Result createUser(CreateUserForm form) {
        User user = new User();
        user.setUsername(form.getUsername());
        user.setPassword(PasswordUtil.MD5(form.getPassword()));
        user.setEmail(form.getEmail());
        user.setMobile(form.getMobile());
        user.setQuestion(form.getQuestion());
        user.setAnswer(form.getAnswer());
        user.setRole(form.getRole());
        int cnt = userMapper.insert(user);
        if (cnt == 1)
            return Result.success();
        else
            return Result.fail("新增用户失败");
    }

    public Result deleteUser(Integer id) {
        int cnt = userMapper.deleteByPrimaryKey(id);
        if (cnt == 1)
            return Result.success();
        else
            return Result.fail("删除用户失败");
    }

    public Result<List<User>> listAllUser(){
        List<User> users = userMapper.selectAll();
        if(users == null){
            return Result.fail("查询用户错误");
        }
        return Result.success(users);
    }
}
