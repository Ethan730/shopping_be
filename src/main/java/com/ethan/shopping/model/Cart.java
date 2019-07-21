package com.ethan.shopping.model;

import java.util.Date;
import lombok.Data;

@Data
public class Cart {
    private Integer id;

    private Integer userId;

    private Integer productId;

    private Integer quantity;

    private Integer checked;

    private Integer status;

    private Date createTime;

    private Date modifyTime;
}