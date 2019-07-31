package com.ethan.shopping.service;

import com.ethan.shopping.dto.product.AddToProductForm;
import com.ethan.shopping.dto.product.UpdateProductForm;
import com.ethan.shopping.utils.Result;

public interface ProductService {
    Result addToProduct(AddToProductForm form);

    Result updateProduct(UpdateProductForm form);

    Result deleteProduct(Integer id);

    Result getProduct(Integer id);
}
