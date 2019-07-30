package com.ethan.shopping.service.impl;

import com.ethan.shopping.mapper.AddressMapper;
import com.ethan.shopping.model.Address;
import com.ethan.shopping.service.AddressService;
import com.ethan.shopping.utils.CurrentUserUtil;
import com.ethan.shopping.utils.MyException;
import com.ethan.shopping.utils.Result;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

@Service
@Log4j2
public class AddressServiceImpl implements  AddressService{
    @Autowired
    private AddressMapper addressMapper;

    public Result addAddress( String receiverName,String receiverMobile,String receiverProvince,
                             String receiverCity,String receiverDistrict,String receiverAddress,String receiverZip){
        Integer userId = CurrentUserUtil.getCurrentUser().getId();
        log.info("增加地址impl");
        //新建地址 不查重
        Address address=new Address();
        address.setUserId(userId);
        address.setReceiverName(receiverName);
        address.setReceiverMobile(receiverMobile);
        address.setReceiverProvince(receiverProvince);
        address.setReceiverCity(receiverCity);
        address.setReceiverDistrict(receiverDistrict);
        address.setReceiverAddress(receiverAddress);
        address.setReceiverZip(receiverZip);
        address.setStatus(1);
        int res=addressMapper.insert(address);
        if(res!=1){
            log.error(MessageFormat.format("添加订单地址异常：{}", address));
            throw new MyException("添加订单地址异常");
        }



        return Result.success();

    }

}
