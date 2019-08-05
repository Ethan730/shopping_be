package com.ethan.shopping.controller;

import com.ethan.shopping.dto.LoginForm;
import com.ethan.shopping.utils.PasswordUtil;
import com.ethan.shopping.utils.Result;
import lombok.extern.log4j.Log4j2;
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
import java.text.MessageFormat;

@Log4j2
@Controller
public class AuthController {
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
        log.info("用户登录");
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(form.getUsername(), PasswordUtil.MD5(form.getPassword()));
        try {
            subject.login(token);
        } catch (UnknownAccountException e) {
            log.warn(MessageFormat.format("用户名 {0} 错误", form.getUsername()));
            return Result.fail("用户名错误");
        } catch (IncorrectCredentialsException e){
            log.warn(MessageFormat.format("用户 {0} 密码错误", form.getUsername()));
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
