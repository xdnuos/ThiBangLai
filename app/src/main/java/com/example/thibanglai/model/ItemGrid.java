package com.example.thibanglai.model;

public class ItemGrid {
    private int resource;
    private String nameItem;

    public int getResource() {
        return resource;
    }

    public void setResource(int resource) {
        this.resource = resource;
    }

    public String getNameItem() {
        return nameItem;
    }

    public void setNameItem(String nameItem) {
        this.nameItem = nameItem;
    }

    public ItemGrid(int resource, String nameItem) {
        this.resource = resource;
        this.nameItem = nameItem;
    }
}
