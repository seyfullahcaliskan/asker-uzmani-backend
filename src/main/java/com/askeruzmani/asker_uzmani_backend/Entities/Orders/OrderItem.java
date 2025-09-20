package com.askeruzmani.asker_uzmani_backend.Entities.Orders;

import java.math.BigDecimal;

public class OrderItem {
    private String name;

    private BigDecimal price;

    private int quantity;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
