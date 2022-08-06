package lfu;

public class Item<Key, Data> {
    private Data data;
    private FrequencyNode<Key> parent;

    public Item(Data data, FrequencyNode<Key> parent) {
        this.data = data;
        this.parent = parent;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public FrequencyNode<Key> getParent() {
        return parent;
    }

    public void setParent(FrequencyNode<Key> parent) {
        this.parent = parent;
    }
}
