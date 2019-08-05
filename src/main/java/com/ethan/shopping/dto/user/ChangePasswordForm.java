package com.ethan.shopping.dto.user;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
public class ChangePasswordForm {
    @NotBlank
    @Length(min = 4, max = 16)
    private String newPassword;

    @NotBlank
    @Length(min = 4, max = 16)
    private String oldPassword;
}
