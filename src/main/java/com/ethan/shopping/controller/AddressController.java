package com.ethan.shopping.controller;


import com.ethan.shopping.dto.address.AddAddressForm;
import com.ethan.shopping.dto.address.DeleteAddressForm;
import com.ethan.shopping.service.AddressService;
import com.ethan.shopping.utils.Result;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Log4j2
@RestController
@RequestMapping(value = "/address")
public class AddressController {
    @Autowired
    private AddressService addressService;

    @PostMapping(value = "/insert")
    Result addAddress(@RequestBody @Valid AddAddressForm form, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return Result.fail(bindingResult);
        }
        try {
            addressService.addAddress(form.getReceiverName(),form.getReceiverMobile(),
                form.getReceiverProvince(),form.getReceiverCity(),form.getReceiverDistrict(),form.getReceiverAddress(),form.getReceiverZip());
        } catch (Exception e) {
            log.error(e);
            return Result.fail(e.getMessage());
        }
        return Result.success();
    }
    @PostMapping(value = "/delete")
    Result deleteAddress(@RequestBody @Valid DeleteAddressForm form, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return Result.fail(bindingResult);
        }
        try {
            addressService.deleteAddress(form.getId());
        } catch (Exception e) {
            log.error(e);
            return Result.fail(e.getMessage());
        }
        return Result.success();
    }
    @GetMapping (value = "/list")
    Result listAddress(){
        try {
            return addressService.list();
        } catch (Exception e) {
            log.error(e);
            return Result.fail(e.getMessage());
        }
    }
}
