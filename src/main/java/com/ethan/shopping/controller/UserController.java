package com.ethan.shopping.controller;

import com.ethan.shopping.dto.user.ChangePasswordForm;
import com.ethan.shopping.dto.user.CreateUserForm;
import com.ethan.shopping.dto.user.UpdateUserInfoForm;
import com.ethan.shopping.model.User;
import com.ethan.shopping.service.UserService;
import com.ethan.shopping.utils.Result;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Log4j2
@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping(value = "/register")
    public Result register(@RequestBody @Valid CreateUserForm form, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return Result.fail(bindingResult);
        }
        try{
            userService.createUser(form);
            return Result.success();
        }
        catch (Exception e){
            return Result.fail(e);
        }
    }

    @PostMapping(value = "/updateInfo")
    public Result update(@RequestBody @Valid UpdateUserInfoForm form, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return Result.fail(bindingResult);
        }
        User user = new User();
        user.setEmail(form.getEmail());
        user.setMobile(form.getMobile());
        user.setQuestion(form.getQuestion());
        user.setAnswer(form.getAnswer());
        try{
            userService.editUser(user);
            return Result.success();
        }
        catch (Exception e){
            return Result.fail(e);
        }
    }

    @PostMapping(value = "/changePassword")
    public Result changePassword(@RequestBody @Valid ChangePasswordForm form, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return Result.fail(bindingResult);
        }
        try{
            userService.changePassword(form);
            return Result.success();
        }
        catch (Exception e){
            return Result.fail(e);
        }
    }
}
