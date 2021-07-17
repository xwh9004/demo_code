package com.demo.mybatis.test;

import com.demo.mybatis.domain.Employee;
import com.demo.mybatis.domain.User;
import com.demo.mybatis.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class SqlSessionFactoryTest {

    private SqlSession sqlSession;
    private SqlSession sqlSession1;
    @Before
    public void buildSqlSession() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        sqlSession = sqlSessionFactory.openSession();
        sqlSession1 = sqlSessionFactory.openSession();
    }

//    /**
//     * 环境测试
//     * @throws IOException
//     */
//    @Before
//    public void buildSqlSessionByEnv() throws IOException {
//        String resource = "mybatis-config.xml";
//        InputStream inputStream = Resources.getResourceAsStream(resource);
//        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream,"test");
//        sqlSession1 = sqlSessionFactory.openSession();
//    }
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
    public void typeAliasTest() {
        String selectUser = "com.demo.mybatis.UserMapper.selectUserById";
        User user = sqlSession.selectOne(selectUser,1);
        System.out.println(user);
    }
    @Test
    public void selectByAnnotation() {
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        System.out.println(userMapper.selectUserById(1));
    }
    @Test
    public void typeHandlerTest(){
        String selectUser = "com.demo.mybatis.UserMapper.selectUserById";
        User user = sqlSession.selectOne(selectUser,3);
        User user2 = sqlSession.selectOne(selectUser,1);
         user2 = sqlSession.selectOne(selectUser,2);
        System.out.println(user2);
    }

    @Test
    public void selectTimeoutTest(){
        String selectByFirstName = "com.demo.mybatis.EmployeeMapper.selectByFirstName";
        List<Employee> employeeList = sqlSession.selectList(selectByFirstName,"Georgi");
        System.out.println(employeeList.size());
    }

    @Test
    public void selectFetchSizeTest(){
        String selectByFirstName = "com.demo.mybatis.EmployeeMapper.selectByFirstName";
        List<Employee> employeeList = sqlSession.selectList(selectByFirstName,"Georgi");
        System.out.println(employeeList.size());
    }

    @Test
    public void select2thCaceTest(){
        String selectByFirstName = "com.demo.mybatis.EmployeeMapper.selectByFirstName";
        List<Employee> employeeList = sqlSession.selectList(selectByFirstName,"Georgi");
        sqlSession.commit();  //刷入缓存
        System.out.println(employeeList.size());
        employeeList = sqlSession1.selectList(selectByFirstName,"Georgi");
        System.out.println(employeeList.size());
    }
}
