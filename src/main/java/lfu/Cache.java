package lfu;

import java.util.HashMap;
import java.util.Map;

public class Cache<Key> {
    private final Map<Key, Item> cacheMap;
    private final FrequencyNode<Key> frequencyHead;
    private Integer cacheCapacity;

    public Cache(Integer capacity) {
        cacheMap = new HashMap<>();
        frequencyHead = new FrequencyNode<>();
        cacheCapacity = capacity;
    }

    public Map<Key, Item> getCacheMap() {
        return cacheMap;
    }

    public FrequencyNode<Key> getFrequencyHead() {
        return frequencyHead;
    }

    public Integer getCacheCapacity() {
        return cacheCapacity;
    }

    public void setCacheCapacity(Integer cacheCapacity) {
        this.cacheCapacity = cacheCapacity;
    }
}
