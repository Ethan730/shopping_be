package com.ethan.shopping.dto.cart;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class DeleteCartForm {
    @NotNull
    private Integer id;
}
