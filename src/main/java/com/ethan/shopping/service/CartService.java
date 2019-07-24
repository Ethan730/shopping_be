package com.ethan.shopping.service;

import com.ethan.shopping.utils.Result;

public interface CartService {
    Result addToCart(Integer productId, Integer quantity);

    Result checkedCart(Integer id, Integer checked);

    Result deleteCart(Integer id);

    Result list();
}
