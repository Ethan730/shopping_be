package com.ethan.shopping.dto.address;

import lombok.Data;

import javax.validation.constraints.NotNull;
@Data
public class UpdateAddressForm {
    @NotNull( message = "地址Id不能为空")
    private Integer id;


    private String receiverName;


    private String receiverMobile;


    private String receiverProvince;


    private String receiverCity;


    private String receiverDistrict;


    private String receiverAddress;


    private String receiverZip;
}
