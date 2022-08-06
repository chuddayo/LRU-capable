package lfu;

public class Item {
    private String data;
    private FrequencyNode parent;

    public Item(String data, FrequencyNode parent) {
        this.data = data;
        this.parent = parent;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public FrequencyNode getParent() {
        return parent;
    }

    public void setParent(FrequencyNode parent) {
        this.parent = parent;
    }
}
