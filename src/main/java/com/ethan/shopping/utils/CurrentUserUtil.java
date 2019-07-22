package com.ethan.shopping.utils;

import com.ethan.shopping.model.User;
import org.apache.shiro.SecurityUtils;

public class CurrentUserUtil {
    public static User getCurrentUser(){
        return (User)SecurityUtils.getSubject().getPrincipal();
    }
}
