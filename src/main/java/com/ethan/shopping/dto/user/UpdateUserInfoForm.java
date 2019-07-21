package com.ethan.shopping.dto.user;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class UpdateUserInfoForm {
    @Email
    private String email;

    @Pattern(regexp = "1[3|4|5|7|8][0-9]\\d{8}")
    private String mobile;

    private String question;

    private String answer;
}
