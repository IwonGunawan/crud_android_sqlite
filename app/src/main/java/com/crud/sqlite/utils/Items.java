package com.crud.sqlite.utils;

public class Items {

    private long items_id;
    private String items_name;
    private String items_brand;
    private String items_price;

    public Items() {
    }

    public long getItems_id() {
        return items_id;
    }

    public void setItems_id(long items_id) {
        this.items_id = items_id;
    }

    public String getItems_name() {
        return items_name;
    }

    public void setItems_name(String items_name) {
        this.items_name = items_name;
    }

    public String getItems_brand() {
        return items_brand;
    }

    public void setItems_brand(String items_brand) {
        this.items_brand = items_brand;
    }

    public String getItems_price() {
        return items_price;
    }

    public void setItems_price(String items_price) {
        this.items_price = items_price;
    }

    @Override
    public String toString() {
        return "Items{" +
                "items_name='" + items_name + '\'' +
                ", items_brand='" + items_brand + '\'' +
                ", items_price='" + items_price + '\'' +
                '}';
    }
}
