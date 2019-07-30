package com.ethan.shopping.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
public class LoginForm {
    @NotBlank
    @Length(min = 1, max = 6)
    private String username;

    @NotBlank(message = "ddd")
    @Length(min = 4, max = 16)
    private String password;
}
