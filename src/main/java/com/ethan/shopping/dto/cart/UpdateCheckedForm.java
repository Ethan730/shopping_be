package com.ethan.shopping.dto.cart;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UpdateCheckedForm {
    @NotNull
    private Integer id;

    @NotNull
    private Integer checked;
}
