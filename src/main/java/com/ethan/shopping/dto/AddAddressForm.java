package com.ethan.shopping.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class AddAddressForm {


    @NotNull( message = "收件人不能为空")
    private String receiverName;

    @NotNull( message = "电话不能为空")
    private String receiverMobile;

    @NotNull( message = "收件省份不能为空")
    private String receiverProvince;

    @NotNull( message = "收件城市不能为空")
    private String receiverCity;

    @NotNull( message = "收件街道不能为空")
    private String receiverDistrict;

    @NotNull( message = "地址不能为空")
    private String receiverAddress;

    @NotNull( message = "zip不能为空")
    private String receiverZip;
}
