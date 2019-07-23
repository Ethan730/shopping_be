package com.ethan.shopping.controller;

import com.ethan.shopping.dto.LoginForm;
import com.ethan.shopping.model.User;
import com.ethan.shopping.utils.CurrentUserUtil;
import com.ethan.shopping.utils.PasswordUtil;
import com.ethan.shopping.utils.Result;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@Controller
public class AuthController {
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    @ResponseBody
    public User index() {
        return CurrentUserUtil.getCurrentUser();
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @ResponseBody
    public String login() {
        return "请先登录";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Result doLogin(@RequestBody @Valid LoginForm form, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return Result.fail(bindingResult);
        }
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(form.getUsername(), PasswordUtil.MD5(form.getPassword()));
        try {
            subject.login(token);
        } catch (UnknownAccountException e) {
            return Result.fail("用户名错误");
        } catch (IncorrectCredentialsException e){
            return Result.fail("密码错误");
        }
        return Result.success();
    }

    @RequestMapping(value = "/noAuth", method = RequestMethod.GET)
    @ResponseBody
    public Result noAuth() {
        return Result.fail("没有权限");
    }
}
