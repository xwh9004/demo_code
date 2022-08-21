package com.demo.mybatis.mapper;

import com.demo.mybatis.SqlProvider;
import com.demo.mybatis.domain.User;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {

    @Select("select * from user where id = #{id}")
    User selectUserById(Integer id);
    @Options(useGeneratedKeys = true,keyProperty = "id")
    @InsertProvider(value = SqlProvider.class,method = "insertUser" )
    int insert(User user);
}
