package com.lewis.cms.domain;

/**
 * Created by zhangminghua on 2016/5/17.
 */
public class Product {
    private int id;

    private String name;

    private int price;

    private String producer;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", producer='" + producer + '\'' +
                '}';
    }

    public Product() {
    }

    public Product(int id, String name, int price, String producer) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.producer = producer;
    }
}
