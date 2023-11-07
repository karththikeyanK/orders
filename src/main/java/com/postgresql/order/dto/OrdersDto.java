package com.postgresql.order.dto;

public class OrdersDto {
    private Long orderId;
    private String name;

    private String userName;

    private Integer quantity;


    public OrdersDto() {
        this.orderId = orderId;
        this.name = name;
        this.userName = userName;
        this.quantity = quantity;
    }

    public Long getOrderId() {
        return orderId;
    }

    public String getName() {
        return name;
    }

    public String getUserName() {
        return userName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
