package com.ethan.shopping.model;

import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

@Data
public class OrderItem {
    private Integer id;

    private Long orderNo;

    private Integer buyerId;

    private Integer productId;

    private String productName;

    private String productImage;

    private BigDecimal price;

    private Integer quantity;

    private BigDecimal totalPrice;

    private Integer status;

    private Date createTime;

    private Date modifyTime;
}