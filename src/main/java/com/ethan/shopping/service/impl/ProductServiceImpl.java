package com.ethan.shopping.service.impl;

import com.ethan.shopping.dto.product.AddToProductForm;
import com.ethan.shopping.dto.product.UpdateProductForm;
import com.ethan.shopping.mapper.ProductMapper;
import com.ethan.shopping.model.Product;
import com.ethan.shopping.service.ProductService;
import com.ethan.shopping.utils.MyException;
import com.ethan.shopping.utils.Result;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.Date;

@Service
@Log4j2
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;

    public Result addToProduct(AddToProductForm form) {
        Product product = new Product();
        product.setId(form.getId());
        product.setName(form.getName());
        product.setSubname(form.getSubname());
        product.setMainImage(form.getMainImage());
        product.setSubImages(form.getSubImages());
        product.setDetail(form.getDetail());
        product.setPrice(form.getPrice());
        product.setStock(form.getStock());
        product.setStatus(form.getStatus());

        Date now = new Date();
        product.setCreateTime(now);
        int res = productMapper.insertSelective(product);
        if (res != 1) {
            log.error(MessageFormat.format("加入商品异常：{}", product));
            throw new MyException("加入商品异常");
        }
        return Result.success();
    }

    public Result updateProduct(UpdateProductForm form) {
        Product product = new Product();
        product.setId(form.getId());
        if (form.getName() != "")
            product.setName(form.getName());
        if (form.getSubname() != "")
            product.setSubname(form.getSubname());
        if (form.getMainImage() != "")
            product.setMainImage(form.getMainImage());
        if (form.getSubImages() != "")
            product.setSubImages(form.getSubImages());
        if (form.getDetail() != "")
            product.setDetail(form.getDetail());
        if (form.getPrice() != BigDecimal.valueOf(0))
            product.setPrice(form.getPrice());
        if (form.getStock() != 0)
            product.setStock(form.getStock());

        Date now = new Date();
        product.setModifyTime(now);
        int res = productMapper.updateByPrimaryKeySelective(product);
        if (res != 1) {
            log.error(MessageFormat.format("更新商品异常：{}", product));
            throw new MyException("更新商品异常");
        }
        return Result.success();
    }

    public Result deleteProduct(Integer id) {
        int res = productMapper.deleteByPrimaryKey(id);
        if (res != 1) {
            log.error(MessageFormat.format("删除商品异常：{}", id));
            throw new MyException("删除商品异常");
        }
        return Result.success();
    }

    public Result getProduct(Integer id) {
        Product product = productMapper.selectByPrimaryKey(id);
        System.out.println(product);
        if (product == null) {
            log.error("查询购物车错误");
            throw new MyException("查询购物车错误");
        }
        return Result.success(product);
    }

}
