package com.ethan.shopping.dto.user;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;

@Data
public class CreateUserForm {
    @NotEmpty
    private String username;

    @NotBlank
    @Length(min = 4, max = 16)
    private String password;

    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    @Pattern(regexp = "1[3|4|5|7|8][0-9]\\d{8}")
    private String mobile;

    private String question;

    private String answer;

    @NotNull
    @Max(2)
    @Min(1)
    private Integer role;
}
