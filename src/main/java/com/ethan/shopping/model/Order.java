package com.ethan.shopping.model;

import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

@Data
public class Order {
    private Integer id;

    private Long orderNo;

    private Integer buyerId;

    private Integer salerId;

    private Integer addressId;

    private BigDecimal payment;

    private Integer postage;

    private Integer status;

    private Date createTime;

    private Date paymentTime;

    private Date sendTime;

    private Date endTime;

    private Date closeTime;

    private Date modifyTime;
}