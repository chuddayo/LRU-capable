package lfu;

public class CacheEviction {
    Cache cache = Cache.getCache();

    public FrequencyNode getNewNode(Integer value, FrequencyNode prev, FrequencyNode next) {
        FrequencyNode newNode = new FrequencyNode(value, prev, next);
        prev.setNext(newNode);
        next.setPrev(newNode);
        return newNode;
    }

    public String access(Integer key) {
        Item item = cache.getCacheMap().get(key);
        if (item == null) throw new RuntimeException("No such key.");

        FrequencyNode freq = item.getParent();
        FrequencyNode nextFreq = freq.getNext();

        // if cache is empty or next frequency is greater than current + 1, create a new freq node
        if (nextFreq.equals(cache.getFrequencyHead()) || nextFreq.getValue() != freq.getValue() + 1) {
            nextFreq = getNewNode(freq.getValue() + 1, freq, nextFreq);
        }
        nextFreq.getItemSet().add(key);
        item.setParent(nextFreq);

        freq.getItemSet().remove(key);
        if (freq.getItemSet().size() == 0) deleteNode(freq);
        return item.getData();
    }

    // INSERT a new element into the LFU CACHE
    public void insert(Integer key, String value) {
        if (cache.getCacheMap().containsKey(key)) throw new RuntimeException("Key already exists.");

        // if we're at capacity, evict the LFU item
        if (cache.getCacheMap().size() >= cache.getCacheCapacity()) evictLFUItem();

        FrequencyNode frequencyNode = cache.getFrequencyHead().getNext();
        if (frequencyNode.getValue() != 1) frequencyNode = getNewNode(1, cache.getFrequencyHead(), frequencyNode);

        frequencyNode.getItemSet().add(key);
        cache.getCacheMap().put(key, new Item(value, frequencyNode));
    }

    public void deleteNode(FrequencyNode node) {
        node.getPrev().setNext(node.getNext());
        node.getNext().setPrev(node.getPrev());
        System.out.println(node.getValue() + " deleted");
    }

    // GET the LFU item's key
    public Integer getLFUItem() {
        if (cache.getCacheMap().size() == 0) throw new RuntimeException("The set is empty.");
        return cache.getFrequencyHead().getNext().getItemSet().iterator().next();
    }

    // EVICT the LFU Item in our cache
    public void evictLFUItem() {
        FrequencyNode leastFrequencyUsedNode = cache.getFrequencyHead().getNext();
        Integer key = getLFUItem();
        //System.out.println("we're gonna remove " + key);

        // we'll remove the key from both data structures
        cache.getCacheMap().remove(key);
        cache.getFrequencyHead().getNext().getItemSet().remove(key);

        if (leastFrequencyUsedNode.getItemSet().size() == 0) deleteNode(leastFrequencyUsedNode);
    }
}
