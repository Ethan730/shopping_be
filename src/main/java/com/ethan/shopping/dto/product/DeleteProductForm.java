package com.ethan.shopping.dto.product;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class DeleteProductForm {
    @NotNull
    private Integer id;
}
