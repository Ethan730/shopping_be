package com.ethan.shopping.model;

import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

@Data
public class Product {
    private Integer id;

    private Integer categoryId;

    private String name;

    private String subname;

    private String mainImage;

    private String subImages;

    private String detail;

    private BigDecimal price;

    private Integer stock;

    private Integer status;

    private Date createTime;

    private Date modifyTime;
}