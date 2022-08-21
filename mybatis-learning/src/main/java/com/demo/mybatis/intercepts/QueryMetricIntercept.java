package com.demo.mybatis.intercepts;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.util.Properties;
@Intercepts(value = {@Signature(
        type = Executor.class,
        method = "query",
        args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})})
public class QueryMetricIntercept implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        before();
        Object returnObject = invocation.proceed();
        after();
        return returnObject;
    }

    private void after() {
        System.out.println("after....");
    }

    private void before() {
        System.out.println("before....");
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target,this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
