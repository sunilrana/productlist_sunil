package com.sunil.demo.test.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;

import static com.sunil.demo.test.entity.Product.TABLE_NAME;


/**
 * Created by Sunil Rana on 10/28/2017.
 */

@Entity(tableName = TABLE_NAME)
public class Product implements Serializable{
    public static final String TABLE_NAME = "product";

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;
    private String description;
    private String regular_price;
    private String sale_price;
    private String product_photo;
    private String color;
    private String store;


    public Product(int id, String name, String description, String regular_price, String sale_price, String product_photo, String color, String store) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.regular_price = regular_price;
        this.sale_price = sale_price;
        this.product_photo = product_photo;
        this.color = color;
        this.store = store;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRegular_price() {
        return regular_price;
    }

    public void setRegular_price(String regular_price) {
        this.regular_price = regular_price;
    }

    public String getSale_price() {
        return sale_price;
    }

    public void setSale_price(String sale_price) {
        this.sale_price = sale_price;
    }

    public String getProduct_photo() {
        return product_photo;
    }

    public void setProduct_photo(String product_photo) {
        this.product_photo = product_photo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }


    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", regular_price='" + regular_price + '\'' +
                ", sale_price='" + sale_price + '\'' +
                ", product_photo='" + product_photo + '\'' +
                ", color='" + color + '\'' +
                ", store='" + store + '\'' +
                '}';
    }
}
