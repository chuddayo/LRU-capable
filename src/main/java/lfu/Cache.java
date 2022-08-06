package lfu;

import java.util.HashMap;
import java.util.Map;

public class Cache<Key> {
    private Map<Key, Item> cacheMap;
    private FrequencyNode<Key> frequencyHead;
    private Integer cacheCapacity;

    public Cache(Integer capacity) {
        cacheMap = new HashMap<>();
        frequencyHead = new FrequencyNode<>();
        cacheCapacity = capacity;
    }

    public Map<Key, Item> getCacheMap() {
        return cacheMap;
    }

    public void setCacheMap(Map<Key, Item> cacheMap) {
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
