package lfu;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CacheEvictionTest {
    CacheEviction<Integer, String> cacheEviction;
    @Before
    public void init() {
        cacheEviction = new CacheEviction<>();
    }

    @Test
    public void constructorInitialMapSizeTest() {
        int actual = cacheEviction.cache.getCacheMap().size();

        Assert.assertEquals(0, actual);
    }

    @Test
    public void getNewNodeTest() {
        FrequencyNode<Integer> frequencyNode = cacheEviction
                .getNewNode(3, new FrequencyNode<>(), new FrequencyNode<>());

        int actual = frequencyNode.getValue();
        Assert.assertEquals(3, actual);
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

    @Test
    public void capacityTest_CapacitySetTo3() {
        cacheEviction.cache.setCacheCapacity(3);

        cacheEviction.insert(0, "banana");
        cacheEviction.insert(1, "coffee");
        cacheEviction.insert(14, "school");
        int actual = cacheEviction.cache.getCacheMap().size();

        Assert.assertEquals(3, actual);

        cacheEviction.insert(3, "child");

        actual = cacheEviction.cache.getCacheMap().size();

        Assert.assertEquals(3, actual);
    }

    @Test (expected = RuntimeException.class)
    public void accessNotInMapTest() {
        cacheEviction.cache.setCacheCapacity(3);

        cacheEviction.insert(0, "banana");
        cacheEviction.insert(1, "coffee");
        cacheEviction.insert(14, "school");
        cacheEviction.access(7);
    }

    @Test // testing that there is a frequency node with value two
    // (and a freq node of value one between that node and the head)
    public void accessAlreadyInMapTest() {
        cacheEviction.cache.setCacheCapacity(3);

        cacheEviction.insert(0, "banana");
        cacheEviction.insert(1, "coffee");
        cacheEviction.insert(14, "school");
        cacheEviction.access(14);

        int actual = cacheEviction.cache.getFrequencyHead().getNext().getNext().getValue();

        Assert.assertEquals(2, actual);
    }

    @Test
    public void accessReturnsCorrectDataTest() {
        String expected = "school";
        cacheEviction.insert(0, "banana");
        cacheEviction.insert(1, "coffee");
        cacheEviction.insert(14, expected);

        String actual = cacheEviction.access(14);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void deleteNodeTest() {
        cacheEviction.insert(1, "coffee");
        int actual = cacheEviction.cache.getFrequencyHead().getNext().getValue();

        Assert.assertEquals(1, actual);

        cacheEviction.access(1);
        actual = cacheEviction.cache.getFrequencyHead().getNext().getValue();

        Assert.assertEquals(2, actual);
    }

    @Test (expected = RuntimeException.class)
    public void getLFUItemFromEmptySetTest() {
        cacheEviction.getLFUItem();
    }

    @Test
    public void getLFUItemKey() {
        cacheEviction.insert(1, "coffee");
        cacheEviction.insert(14, "school");
        cacheEviction.access(1);

        int actualKey = cacheEviction.getLFUItem();

        Assert.assertEquals(14, actualKey);
    }

    @Test (expected = RuntimeException.class)
    public void evictLFUItemFromEmptySet() {
        cacheEviction.evictLFUItem();
    }

    @Test
    public void evictLFUItemTest() {
        cacheEviction.insert(1, "coffee");
        cacheEviction.insert(14, "school");
        cacheEviction.access(1);

        int actualKey = cacheEviction.getLFUItem();

        Assert.assertEquals(14, actualKey);

        cacheEviction.evictLFUItem();
        // after we evict 14, we expect 1 to be new LFU
        actualKey = cacheEviction.getLFUItem();

        Assert.assertEquals(1, actualKey);
    }

    @After
    public void cleanUp() {
        cacheEviction.cache.getCacheMap().clear();
        // do I need to also clear the freq node list?
    }
}
