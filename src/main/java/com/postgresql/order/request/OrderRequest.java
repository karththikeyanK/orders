package com.postgresql.order.request;

public class OrderRequest {

    private Long itemId;
    private Long userId;

    private Integer quantity;

    public Long getItemId() {
        return itemId;
    }

    public Long getUserId() {
        return userId;
    }

    public Integer getQuantity() {
        return quantity;
    }

}
