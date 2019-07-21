package com.ethan.shopping.model;

import java.util.Date;
import lombok.Data;

@Data
public class Category {
    private Integer id;

    private Integer parentId;

    private String name;

    private Integer status;

    private Integer sortOrder;

    private Date createTime;

    private Date modifyTime;
}