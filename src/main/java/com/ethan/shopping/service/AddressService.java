package com.ethan.shopping.service;

import com.ethan.shopping.utils.Result;

public interface AddressService {

        Result addAddress(String receiverName, String receiverMobile, String receiverProvince,
                          String receiverCity, String receiverDistrict, String receiverAddress, String receiverZip);



        Result deleteAddress(Integer Id);

        Result list ();


        Result updateAddress(Integer id,String receiverName, String receiverMobile, String receiverProvince,
                          String receiverCity, String receiverDistrict, String receiverAddress, String receiverZip);

}
