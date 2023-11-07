package com.postgresql.order.request;

public class ItemRequest {
    private String name;
    private Double price;

    private Integer quantity;

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getQuantity() {
        return quantity;
    }
}
