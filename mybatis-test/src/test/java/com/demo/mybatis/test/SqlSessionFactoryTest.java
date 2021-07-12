package com.demo.mybatis.test;

import com.demo.mybatis.domain.User;
import com.demo.mybatis.map.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class SqlSessionFactoryTest {

    private SqlSession sqlSession;

    @Before
    public void buildSqlSession() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        sqlSession = sqlSessionFactory.openSession();
    }

//    @Before
//    public void buildSqlSessionByProp() throws IOException {
//        String resource = "mybatis-config-props.xml";
//        InputStream inputStream = Resources.getResourceAsStream(resource);
//        Properties props = new Properties();
//        props.setProperty("driver","com.mysql.jdbc.Driver");
//        props.setProperty("url","jdbc:mysql://localhost:3306/test?useSSL=false&useUnicode=true&characterEncoding=utf8&autoReconnect=true&failOverReadOnly=false&allowMultiQueries=true&rewriteBatchedStatements=true&serverTimezone=GMT%2B8");
//        props.setProperty("username","root");
//        props.setProperty("password","");
//        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream,props);
//        sqlSession = sqlSessionFactory.openSession();
//    }
    
    @Test
    public void selectByXml() {
        String selectUser = "selectUser";
        User user = sqlSession.selectOne(selectUser,1);
        System.out.println(user);
    }
    @Test
    public void selectByAnnotation() {
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        System.out.println(userMapper.selectUserById(1));
    }

    public void globallyCacheTest(){

    }
}
