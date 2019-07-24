package com.ethan.shopping.dto.cart;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class UpdateCheckedForm {
    @NotNull
    private Integer id;

    @NotNull
    @Min(value = 0, message = "状态码最小为0")
    @Max(value = 1, message = "状态码最大为1")
    private Integer checked;
}
