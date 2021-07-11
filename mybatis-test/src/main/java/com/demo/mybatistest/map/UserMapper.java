package com.demo.mybatistest.map;

import com.demo.mybatistest.domain.User;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {

    @Select("select * from user where id = #{id}")
    User selectUserById(Integer id);
}
