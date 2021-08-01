package com.demo.mybatis.test;

import com.demo.mybatis.domain.Department;
import com.demo.mybatis.domain.DeptManager;
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
    private SqlSession sqlSessionTest;
    private SqlSession anotherSqlSession;
    @Before
    public void buildSqlSession() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        sqlSession = sqlSessionFactory.openSession();
        sqlSession.getConnection();
        anotherSqlSession = sqlSessionFactory.openSession();
    }

    /**
     * 环境测试
     * @throws IOException
     */
    @Before
    public void buildSqlSessionByEnv() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream,"test");
        sqlSessionTest = sqlSessionFactory.openSession();
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
    public void typeAliasTest() {
        String selectUser = "com.demo.mybatis.UserMapper.selectUserById";
        User user = sqlSession.selectOne(selectUser,1);
        System.out.println(user);
    }
    @Test
    public void selectByAnnotation() {
        UserMapper userMapper = sqlSessionTest.getMapper(UserMapper.class);
        System.out.println(userMapper.selectUserById(1));
    }

    @Test
    public void insertProviderTest() {
        UserMapper userMapper = sqlSessionTest.getMapper(UserMapper.class);
        User user = new User();
        user.setUsername("Jack");
        user.setAddress("SunYaoRd");
        user.setAge(18);
        userMapper.insert(user);
        sqlSessionTest.commit();
        System.out.println(user);
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
        employeeList = anotherSqlSession.selectList(selectByFirstName,"Georgi");
        System.out.println(employeeList.size());
    }

    /**
     * 1 将sql分为动态部分与静态部分
     * 2 将动态部分，每次sql执行时候动态部分与静态部分拼接到一起。
     * 3 以if-test为例： 将每个 if 部分抽象成一个 ifSqlNode,每次执行根据条件，判断表达式是否成立；
     * 3 表达式成立即拼接在一起，否则舍弃。
     */
    @Test
    public void ifTestConditionTest(){
        String findDeptWithNoOrName = "com.demo.mybatis.Department.findDeptWithNoAndName";
        Department condition = new Department();
//        condition.setDeptNo("d009");
        condition.setDeptName("Customer Service");
        Department department = sqlSession.selectOne(findDeptWithNoOrName,condition);
        System.out.println(department.toString());
    }
    /**
     * 1 将sql分为动态部分与静态部分
     * 2 将动态部分，每次sql执行时候动态部分与静态部分拼接到一起。
     * 3 以chose-when为例：先将chose抽象成choseSqlNode,
     *   将每个 when 部分抽象成一个 ifSqlNode,每次执行根据条件，判断表达式是否成立；
     * 4  依次判断ifSqlNode条件是否成立，直到找到第一个成立的when语句，并将sql拼接。
     */
    @Test
    public void choseWhenConditionTest(){
        String findDeptWithNoOrName = "com.demo.mybatis.Department.findDeptWithNoOrName";
        Department condition = new Department();
        condition.setDeptNo("d009");
        condition.setDeptName("Customer Service");
        Department department = sqlSession.selectOne(findDeptWithNoOrName,condition);
        System.out.println(department.toString());
    }

    /**
     * foreachSqlNode
     */
    @Test
    public void forEachTest(){
        String findDeptWithNos = "com.demo.mybatis.Department.findDeptWithNos";
//        List<String> condition = new ArrayList<>();
//        condition.add("d009");
//        condition.add("d005");
        String[] condition ={"d009","d005"};
        List<Department> departments = sqlSession.selectList(findDeptWithNos,condition);
        System.out.println(departments.size());
    }

    /**
     * 引如log包
     * 配置 log4j.logger.xxx.xxx 可以配置想要打印的log
     */
    @Test
    public void logTest(){
        String findDeptWithNoOrName = "com.demo.mybatis.Department.findDeptWithNoOrName";
        Department condition = new Department();
        condition.setDeptNo("d009");
        condition.setDeptName("Customer Service");
        Department department = sqlSession.selectOne(findDeptWithNoOrName,condition);
        System.out.println(department.toString());
    }

    /**
     * #与$的区别
     */
    @Test
    public void dollarAndPound() {
        //         select * from user where id = #{id}
        String selectUser = "com.demo.mybatis.UserMapper.selectUser";
        //  select * from user where id = ${id}
        String selectUserById = "com.demo.mybatis.UserMapper.selectUserById";
        List users1 =  sqlSessionTest.selectList(selectUser,"1 or 1=1 ");
        List users2 =  sqlSessionTest.selectList(selectUserById,"1 or 1=1");
        System.out.println(users1.size());
        System.out.println(users2.size());
    }

    @Test
    public void nestCollectionTest(){
        String selectDeptManager = "com.demo.mybatis.EmployeeMapper.selectDeptManagers";

        String condition ="d001";
        List deptManagers = sqlSession.selectList(selectDeptManager,condition);
        System.out.println(deptManagers.size());
    }

    @Test
    public void lazyLoadTest(){
        String selectDeptManager = "com.demo.mybatis.EmployeeMapper.selectDeptManager";

        String condition ="d001";
        //开启懒加载时返回的对象时一个代理对象（javaassit）
        Department departments = sqlSession.selectOne(selectDeptManager,condition);
        System.out.println(departments);
    }
}
