package com.demo.mybatis.test.cache;

import org.apache.ibatis.cache.decorators.LruCache;
import org.apache.ibatis.cache.impl.PerpetualCache;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class LruCacheTest {

    @Test
    public void shouldRemoveLeastRecentlyUsedItemInBeyondFiveEntries() {
        LruCache cache = new LruCache(new PerpetualCache("default"));
        cache.setSize(5);
        for (int i = 0; i < 5; i++) {
            cache.putObject(i, i);
        }
        assertEquals(0, cache.getObject(0));
        assertEquals(1,cache.getObject(1));
        cache.putObject(5, 5);
        assertNull(cache.getObject(2));
        assertEquals(5, cache.getSize());
    }
}
