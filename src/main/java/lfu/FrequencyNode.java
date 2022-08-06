package lfu;

import java.util.HashSet;
import java.util.Set;

public class FrequencyNode<Key> {
    private Integer value;
    private FrequencyNode<Key> prev;
    private FrequencyNode<Key> next;
    private Set<Key> itemSet;

    public FrequencyNode () {
        value = 0;
        prev = this;
        next = this;
        itemSet = new HashSet<>();
    }

    public FrequencyNode (Integer value, FrequencyNode<Key> prev, FrequencyNode<Key> next) {
        this.value = value;
        this.prev = prev;
        this.next = next;
        itemSet = new HashSet<>();
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public FrequencyNode<Key> getPrev() {
        return prev;
    }

    public void setPrev(FrequencyNode<Key> prev) {
        this.prev = prev;
    }

    public FrequencyNode<Key> getNext() {
        return next;
    }

    public void setNext(FrequencyNode<Key> next) {
        this.next = next;
    }

    public Set<Key> getItemSet() {
        return itemSet;
    }

    public void setItemSet(Set<Key> itemSet) {
        this.itemSet = itemSet;
    }
}
