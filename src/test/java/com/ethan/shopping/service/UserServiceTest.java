package com.ethan.shopping.service;

import com.ethan.shopping.dto.user.CreateUserForm;
import com.ethan.shopping.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void selectByName(){
        assert userService.findByUsername("root").isSuccess();
        assert !userService.findByUsername("ddd").isSuccess();
    }

    @Test
    public void createUser(){
        CreateUserForm form = new CreateUserForm();
        form.setUsername("root");
        form.setPassword("1234");
        form.setEmail("wang@tt.com");
        form.setMobile("13333333333");
        form.setQuestion("question");
        form.setAnswer("answer");
        form.setRole(1);
        assert !userService.createUser(form).isSuccess();
        form.setUsername("rrrrrr");
        assert userService.createUser(form).isSuccess();
    }

    @Test
    public void listUser(){
        List<User> users = userService.listAllUser().getData();
        assert users.size()==2;
        for(User user:users){
            System.out.println(user);
        }
    }

    @Test
    public void editUser(){
        User user = userService.findByUsername("root").getData();
        User newUser = new User();
        newUser.setId(user.getId());
        newUser.setEmail("new@qq.com");
        assert userService.editUser(newUser).isSuccess();
    }
}
