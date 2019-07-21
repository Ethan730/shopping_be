package com.ethan.shopping.mapper;

import com.ethan.shopping.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    int insert(User record);

    @Deprecated
    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    @Deprecated
    int updateByPrimaryKey(User record);

    User selectByUsername(String username);

    List<User> selectAll();
}