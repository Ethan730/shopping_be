package com.ethan.shopping.model;

import lombok.Data;

import java.util.Date;

@Data
public class User {
    private Integer id;

    private String username;

    private String password;

    private String email;

    private String mobile;

    private String question;

    private String answer;

    private Integer role;

    private Integer status;

    private Date createTime;

    private Date modifyTime;
}