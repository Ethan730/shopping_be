package com.ethan.shopping.dto.cart;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class AddToCartForm {
    @NotNull(message = "商品id不能为空")
    private Integer productId;

    @Min(value = 1, message = "数量不能小于1")
    private Integer quantity;
}
