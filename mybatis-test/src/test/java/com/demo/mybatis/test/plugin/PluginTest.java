package com.demo.mybatis.test.plugin;

import org.apache.ibatis.plugin.*;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class PluginTest {

    @Test
    public void mapPluginShouldInterceptGet() {
        Map map = new HashMap();
        map = (Map) new AlwaysMapPlugin().plugin(map);
        assertEquals("Always", map.get("Anything"));
    }

    @Test
    public void shouldNotInterceptToString() {
        Map map = new HashMap();
        map = (Map) new AlwaysMapPlugin().plugin(map);
        assertNotEquals("Always", map.toString());
    }

    @Intercepts({
            @Signature(type = Map.class, method = "get", args = {Object.class})})
    public static class AlwaysMapPlugin implements Interceptor {


        @Override
        public Object intercept(Invocation invocation) {
            return "Always";
        }

        @Override
        public Object plugin(Object target) {
            return Plugin.wrap(target,this);
        }

        @Override
        public void setProperties(Properties properties) {

        }
    }
}
