package com.ethan.shopping.service.impl;

import com.ethan.shopping.dto.user.ChangePasswordForm;
import com.ethan.shopping.dto.user.CreateUserForm;
import com.ethan.shopping.mapper.UserMapper;
import com.ethan.shopping.model.User;
import com.ethan.shopping.service.UserService;
import com.ethan.shopping.utils.CurrentUserUtil;
import com.ethan.shopping.utils.MyException;
import com.ethan.shopping.utils.PasswordUtil;
import com.ethan.shopping.utils.Result;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;

@Service
@Log4j2
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

    public Result deleteUser() {
        Integer id = CurrentUserUtil.getCurrentUser().getId();
        User user = userMapper.selectByPrimaryKey(id);
        if(user == null){
            log.error(MessageFormat.format("用户异常，id：{}", id));
            return Result.fail("删除用户失败，用户不存在");
        }
        User tmp = new User();
        tmp.setId(id);
        tmp.setStatus(0);
        int cnt = userMapper.updateByPrimaryKeySelective(tmp);
        if (cnt == 1)
            return Result.success();
        else
            return Result.fail("删除用户失败");
    }

    public Result<List<User>> listAllUser(){
        User user = CurrentUserUtil.getCurrentUser();
        if(user.getRole()!=0){
            return Result.fail("没有权限");
        }
        List<User> users = userMapper.selectAll();
        if(users == null){
            return Result.fail("查询用户错误");
        }
        return Result.success(users);
    }

    public Result editUser(User user){
        user.setId(CurrentUserUtil.getCurrentUser().getId());
        int cnt = userMapper.updateByPrimaryKeySelective(user);
        if(cnt == 1){
            return Result.success();
        }else{
            return Result.fail("更新用户信息失败");
        }
    }

    public Result changePassword(ChangePasswordForm form){
        Integer id = CurrentUserUtil.getCurrentUser().getId();
        User user = userMapper.selectByPrimaryKey(id);
        if(user == null){
            log.error("更新密码错误，当前用户不存在");
            throw new MyException("更新密码错误，当前用户不存在");
        }
        if(!PasswordUtil.MD5(form.getOldPassword()).equals(user.getPassword())){
            return Result.fail("原密码错误");
        }
        User tmp = new User();
        tmp.setId(user.getId());
        tmp.setPassword(PasswordUtil.MD5(form.getNewPassword()));
        int cnt = userMapper.updateByPrimaryKeySelective(tmp);
        if(cnt == 1){
            return Result.success();
        }else{
            return Result.fail("更改密码失败");
        }
    }
}
