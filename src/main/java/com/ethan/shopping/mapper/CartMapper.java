package com.ethan.shopping.mapper;

import com.ethan.shopping.model.Cart;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CartMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Cart record);

    int insertSelective(Cart record);

    Cart selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Cart record);

    int updateByPrimaryKey(Cart record);
}