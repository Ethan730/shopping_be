package com.ethan.shopping.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

@Data
public class LoginForm {
    @NotEmpty
    @Length(min = 1, max = 6)
    private String username;

    @NotEmpty
    @Length(min = 4, max = 16)
    private String password;
}
