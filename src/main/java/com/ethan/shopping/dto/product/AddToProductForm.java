package com.ethan.shopping.dto.product;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class AddToProductForm {
    @NotNull(message = "商品id不能为空")
    private Integer Id;

    private Integer categoryId;

    private String name;

    private String subname;

    private String mainImage;

    private String subImages;

    private String detail;

    private BigDecimal price;

    @Min(value = 1, message = "数量不能小于1")
    private Integer stock;

    private Integer status;
}
