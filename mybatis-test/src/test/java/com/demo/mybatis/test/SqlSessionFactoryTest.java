package com.demo.mybatis.test;

import com.demo.mybatistest.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

public class SqlSessionFactoryTest {

    private SqlSession sqlSession;


    @Before
    public void connectIsClosed() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        sqlSession = sqlSessionFactory.openSession();

    }
    @Test
    public void selectOne() {
        String selectUser = "selectUser";
        User user = sqlSession.selectOne(selectUser,1);
        System.out.println(user);
    }

}
