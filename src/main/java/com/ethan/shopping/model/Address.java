package com.ethan.shopping.model;

import lombok.Data;

import java.util.Date;

@Data
public class Address {
    private Integer id;

    private Integer userId;

    private String receiverName;

    private String receiverMobile;

    private String receiverProvince;

    private String receiverCity;

    private String receiverDistrict;

    private String receiverAddress;

    private String receiverZip;

    private Integer status;

    private Date createTime;

    private Date modifyTime;
}