package com.learning.thread;

import com.learning.pojo.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author: xwh90
 * @date: 2022/12/12 9:57
 * @description:
 */
public class UnsafeTest {
    //使用方法
    private static Unsafe getUnsafeInstance() throws SecurityException,
            NoSuchFieldException, IllegalArgumentException,
            IllegalAccessException {
        Field theUnsafeInstance = Unsafe.class.getDeclaredField("theUnsafe");
        theUnsafeInstance.setAccessible(true);
        return (Unsafe) theUnsafeInstance.get(Unsafe.class);
    }

    private Unsafe unsafe;

    private User user;

    @Before
    public void before() throws NoSuchFieldException, IllegalAccessException {
        unsafe = getUnsafeInstance();
        user = new User();
        user.setName("Jesse");
        user.setAge(18);
    }
    @Test
    public void parkTest() {
        System.out.println("start...." + System.currentTimeMillis());
        //绝对时间
        unsafe.park(true, System.currentTimeMillis() + 3000);
        System.out.println("end...." + System.currentTimeMillis());
    }

    @Test
    public void getAndSetObjectTest() throws NoSuchFieldException {
        Field field = User.class.getDeclaredField("name");
        final long fieldNameOffset = unsafe.objectFieldOffset(field);
        unsafe.compareAndSwapObject(user,fieldNameOffset,"Jesse1","Tommy");
        Assert.assertEquals(user.getName(),"Jesse");
        unsafe.compareAndSwapObject(user,fieldNameOffset,"Jesse","Tommy");
        Assert.assertEquals(user.getName(),"Tommy");
    }


}
