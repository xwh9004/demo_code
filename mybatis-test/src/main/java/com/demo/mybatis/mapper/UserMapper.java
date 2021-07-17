package com.demo.mybatis.mapper;

import com.demo.mybatis.domain.User;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {

    @Select("select * from user where id = #{id}")
    User selectUserById(Integer id);
}
