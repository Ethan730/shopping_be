package com.ethan.shopping.service.impl;

import com.ethan.shopping.mapper.CartMapper;
import com.ethan.shopping.model.Cart;
import com.ethan.shopping.service.CartService;
import com.ethan.shopping.utils.CurrentUserUtil;
import com.ethan.shopping.utils.MyException;
import com.ethan.shopping.utils.Result;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Log4j2
public class CartServiceImpl implements CartService {
    @Autowired
    private CartMapper cartMapper;

    public Result addToCart(Integer productId, Integer quantity) {
        Integer userId = CurrentUserUtil.getCurrentUser().getId();
        // 判断该商品是否存在于购物车之中
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        params.put("productId", productId);
        Cart cart = cartMapper.selectByUserIdAndProductId(params);
        // todo 校验货物id和货物数量是否有效
        if (cart == null) {
            // 新建购物车
            cart = new Cart();
            cart.setUserId(userId);
            cart.setProductId(productId);
            cart.setChecked(1);
            cart.setQuantity(quantity);
            cart.setStatus(1);
            int res = cartMapper.insert(cart);
            if (res != 1) {
                log.error(MessageFormat.format("加入购物车异常：{}", cart));
                throw new MyException("加入购物车异常");
            }
        } else {
            cart.setQuantity(cart.getQuantity() + quantity);
            int res = cartMapper.updateByPrimaryKeySelective(cart);
            if (res != 1) {
                log.error(MessageFormat.format("加入购物车异常：{}", cart));
                throw new MyException("加入购物车异常");
            }
        }
        return Result.success();
    }

    public Result checkedCart(Integer id, Integer checked) {
        Cart cart = cartMapper.selectByPrimaryKey(id);
        if (cart == null) {
            log.error(MessageFormat.format("更新购物车状态错误：不存在id {}", id));
            throw new MyException("购物车id错误");
        }
        if (cart.getUserId() != CurrentUserUtil.getCurrentUser().getId()) {
            log.error(MessageFormat.format("更新购物车状态错误：cart user id: {}, current user id{}", cart.getUserId(), CurrentUserUtil.getCurrentUser().getId()));
            throw new MyException("更新购物车错误");
        }
        cart.setChecked(checked);
        int res = cartMapper.updateByPrimaryKeySelective(cart);
        if (res != 1) {
            log.error("更新购物车状态错误");
            throw new MyException("更新购物车错误");
        }
        return Result.success();
    }

    public Result deleteCart(Integer id) {
        Cart cart = cartMapper.selectByPrimaryKey(id);
        if (cart == null) {
            log.error(MessageFormat.format("更新购物车状态错误：不存在id {0, number}", id));
            throw new MyException("删除购物车错误");
        }
        if (cart.getUserId() != CurrentUserUtil.getCurrentUser().getId()) {
            log.error(MessageFormat.format("更新购物车状态错误：cart user id: {}, current user id{}", cart.getUserId(), CurrentUserUtil.getCurrentUser().getId()));
            throw new MyException("删除购物车错误");
        }
        cart.setStatus(0);
        int res = cartMapper.updateByPrimaryKeySelective(cart);
        if (res != 1) {
            log.error("删除购物车错误");
            throw new MyException("删除购物车错误");
        }
        return Result.success();
    }

    public Result list() {
        List<Cart> carts = cartMapper.selectByUserId(CurrentUserUtil.getCurrentUser().getId());
        for(Cart cart:carts){
            System.out.println(cart);
        }
        if (carts == null) {
            log.error("查询购物车错误");
            throw new MyException("查询购物车错误");
        }
        return Result.success(carts);
    }
}
