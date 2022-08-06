package lfu;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CacheEvictionTest {
    CacheEviction cacheEviction;
    @Before
    public void init() {
        cacheEviction = new CacheEviction();
    }

    @Test
    public void insertTest() {
        cacheEviction.insert(0, "apple");
        int actual = cacheEviction.cache.getCacheMap().size();

        Assert.assertEquals(1, actual);
    }

    @Test (expected = RuntimeException.class)
    public void insertSameKeyTest() {
        cacheEviction.insert(0, "apple");
        cacheEviction.insert(0, "apple");
    }

    @Test
    public void insertMultipleItemsTest() {
        cacheEviction.insert(0, "banana");
        cacheEviction.insert(1, "coffee");
        cacheEviction.insert(14, "school");
        int actual = cacheEviction.cache.getCacheMap().size();

        Assert.assertEquals(3, actual);
    }
}
