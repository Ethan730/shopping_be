package com.ethan.shopping.controller;

import com.ethan.shopping.dto.product.AddToProductForm;
import com.ethan.shopping.dto.product.DeleteProductForm;
import com.ethan.shopping.dto.product.GetProductForm;
import com.ethan.shopping.dto.product.UpdateProductForm;
import com.ethan.shopping.service.ProductService;
import com.ethan.shopping.utils.Result;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Log4j2
@RestController
@RequestMapping(value = "/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping(value = "/add")
    public Result addToProduct(@RequestBody @Valid AddToProductForm form, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return Result.fail(bindingResult);
        }
        try {
            productService.addToProduct(form);
        } catch (Exception e) {
            log.error(e);
            return Result.fail(e.getMessage());
        }
        return Result.success();
    }

    @PostMapping(value = "/update")
    public Result updateProduct(@RequestBody @Valid UpdateProductForm form, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return Result.fail(bindingResult);
        }
        try {
            productService.updateProduct(form);
        } catch (Exception e) {
            log.error(e);
            return Result.fail(e.getMessage());
        }
        return Result.success();
    }

    @PostMapping(value = "/delete")
    public Result delectProduct(@RequestBody @Valid DeleteProductForm form, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return Result.fail(bindingResult);
        }
        try {
            productService.deleteProduct(form.getId());
        } catch (Exception e) {
            log.error(e);
            return Result.fail(e.getMessage());
        }
        return Result.success();
    }

    @GetMapping(value = "/get")
    public Result getProducts(@RequestBody @Valid GetProductForm form, BindingResult bindingResult) {
        try {
            return productService.getProduct(form.getId());
        } catch (Exception e) {
            log.error(e);
            return Result.fail(e.getMessage());
        }
    }
}
