package lfu;

import java.util.HashMap;
import java.util.Map;

public class Cache {
    private static final Cache cache = new Cache(10);
    private Map<Integer, Item> cacheMap;
    private FrequencyNode frequencyHead;
    private Integer cacheCapacity;

    private Cache(Integer capacity) {
        cacheMap = new HashMap<>();
        frequencyHead = new FrequencyNode();
        cacheCapacity = capacity;
    }

    public static Cache getCache() {
        return cache;
    }

    public Map<Integer, Item> getCacheMap() {
        return cacheMap;
    }

    public void setCacheMap(Map<Integer, Item> cacheMap) {
        this.cacheMap = cacheMap;
    }

    public FrequencyNode getFrequencyHead() {
        return frequencyHead;
    }

    public void setFrequencyHead(FrequencyNode frequencyHead) {
        this.frequencyHead = frequencyHead;
    }

    public Integer getCacheCapacity() {
        return cacheCapacity;
    }

    public void setCacheCapacity(Integer cacheCapacity) {
        this.cacheCapacity = cacheCapacity;
    }
}
