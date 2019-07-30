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

    public Result deleteAddress(Integer Id){
        log.info("deleteAddressImpl");
        Address address=addressMapper.selectByPrimaryKey(Id);
        if (address == null) {
            log.error(MessageFormat.format("删除地址错误：不存在id {}", Id));
            throw new MyException("删除地址id错误");
        }
        if (address.getUserId() != CurrentUserUtil.getCurrentUser().getId()) {
            log.error(MessageFormat.format("删除地址错误：cart user id: {}, current user id{}", address.getUserId(), CurrentUserUtil.getCurrentUser().getId()));
            throw new MyException("删除地址错误当前用户与删除订单ID不匹配");
        }
        address.setStatus(0);
        int res=addressMapper.updateByPrimaryKeySelective(address);
        if (res != 1) {
            log.error("更新地址错误");
            throw new MyException("更新地址错误");
        }
        return Result.success();
    }
}
