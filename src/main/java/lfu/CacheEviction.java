package lfu;

public class CacheEviction<Key, Data> {
    Cache<Key, Data> cache = new Cache<>(10);

    // GET a new node with with a certain value
    public FrequencyNode<Key> getNewNode(Integer value, FrequencyNode<Key> prev, FrequencyNode<Key> next) {
        FrequencyNode<Key> newNode = new FrequencyNode<>(value, prev, next);
        prev.setNext(newNode);
        next.setPrev(newNode);
        return newNode;
    }

    // ACCESS some data given the key, but also increase that element's frequency
    public Data access(Key key) {
        Item<Key, Data> item = cache.getCacheMap().get(key);
        if (item == null) throw new RuntimeException("No such key.");

        FrequencyNode<Key> freq = item.getParent();
        FrequencyNode<Key> nextFreq = freq.getNext();

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
    public void insert(Key key, Data value) {
        if (cache.getCacheMap().containsKey(key)) throw new RuntimeException("Key already exists.");

        // if we're at capacity, evict the LFU item
        if (cache.getCacheMap().size() >= cache.getCacheCapacity()) evictLFUItem();

        FrequencyNode<Key> frequencyNode = cache.getFrequencyHead().getNext();
        if (frequencyNode.getValue() != 1) frequencyNode = getNewNode(1, cache.getFrequencyHead(), frequencyNode);

        frequencyNode.getItemSet().add(key);
        cache.getCacheMap().put(key, new Item<>(value, frequencyNode));
    }

    // DELETE Node (for when its KeySet is empty)
    public void deleteNode(FrequencyNode<Key> node) {
        node.getPrev().setNext(node.getNext());
        node.getNext().setPrev(node.getPrev());
        System.out.println(node.getValue() + " deleted");
    }

    // GET the LFU item's key
    public Key getLFUItem() {
        if (cache.getCacheMap().size() == 0) throw new RuntimeException("The set is empty.");
        return cache.getFrequencyHead().getNext().getItemSet().iterator().next();
    }

    // EVICT the LFU Item in our cache
    public void evictLFUItem() {
        FrequencyNode<Key> leastFrequencyUsedNode = cache.getFrequencyHead().getNext();
        Key key = getLFUItem();

        // we'll remove the key from both data structures
        cache.getCacheMap().remove(key);
        cache.getFrequencyHead().getNext().getItemSet().remove(key);

        if (leastFrequencyUsedNode.getItemSet().size() == 0) deleteNode(leastFrequencyUsedNode);
    }
}
