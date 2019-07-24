package com.ethan.shopping.controller;

import com.ethan.shopping.dto.cart.AddToCartForm;
import com.ethan.shopping.dto.cart.DeleteCartForm;
import com.ethan.shopping.dto.cart.UpdateCheckedForm;
import com.ethan.shopping.service.CartService;
import com.ethan.shopping.utils.Result;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Log4j2
@RestController
@RequestMapping(value = "/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @PostMapping(value = "/add")
    public Result addToCart(@RequestBody @Valid AddToCartForm form, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return Result.fail(bindingResult);
        }
        try {
            cartService.addToCart(form.getProductId(), form.getQuantity());
        } catch (Exception e) {
            log.error(e);
            return Result.fail(e.getMessage());
        }
        return Result.success();
    }

    @PostMapping(value = "/checked")
    public Result updateChecked(@RequestBody @Valid UpdateCheckedForm form, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return Result.fail(bindingResult);
        }
        try {
            cartService.checkedCart(form.getId(), form.getChecked());
        } catch (Exception e) {
            log.error(e);
            return Result.fail(e.getMessage());
        }
        return Result.success();
    }

    @PostMapping(value = "/delete")
    public Result delectCart(@RequestBody @Valid DeleteCartForm form, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return Result.fail(bindingResult);
        }
        try {
            cartService.deleteCart(form.getId());
        } catch (Exception e) {
            log.error(e);
            return Result.fail(e.getMessage());
        }
        return Result.success();
    }

    @GetMapping(value = "/list")
    public Result listCarts() {
        try {
            return cartService.list();
        } catch (Exception e) {
            log.error(e);
            return Result.fail(e.getMessage());
        }
    }
}