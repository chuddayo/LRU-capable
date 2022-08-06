package lfu;

import java.util.HashSet;
import java.util.Set;

public class FrequencyNode {
    private Integer value;
    private FrequencyNode prev;
    private FrequencyNode next;
    private Set<Integer> itemSet;

    public FrequencyNode () {
        value = 0;
        prev = this;
        next = this;
        itemSet = new HashSet<>();
    }

    public FrequencyNode (Integer value, FrequencyNode prev, FrequencyNode next) {
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

    public FrequencyNode getPrev() {
        return prev;
    }

    public void setPrev(FrequencyNode prev) {
        this.prev = prev;
    }

    public FrequencyNode getNext() {
        return next;
    }

    public void setNext(FrequencyNode next) {
        this.next = next;
    }

    public Set<Integer> getItemSet() {
        return itemSet;
    }

    public void setItemSet(Set<Integer> itemSet) {
        this.itemSet = itemSet;
    }
}
