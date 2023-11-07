package com.postgresql.order.controller;


import com.postgresql.order.dto.OrdersDto;
import com.postgresql.order.entity.Item;
import com.postgresql.order.entity.Orders;
import com.postgresql.order.request.OrderRequest;
import com.postgresql.order.service.ItemService;
import com.postgresql.order.service.OrderService;
import com.postgresql.order.service.UserService;
import jakarta.persistence.criteria.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @Autowired
    private ItemService itemService;

    @PostMapping("create/order")
    public Orders createOrder(@RequestBody OrderRequest orderRequest) {
        Orders order = new Orders();
        order.setQuantity(orderRequest.getQuantity());
        order.setUsers(userService.getUserById(orderRequest.getUserId()));
        order.setItem(itemService.getItemById(orderRequest.getItemId()));
        return orderService.createOrder(order);
    }

    @GetMapping("get/order")
    public List<OrdersDto> getOrders(){
        return orderService.getOrders();
    }

    @PutMapping("update/order")
    public Orders updateItem(@RequestBody OrderRequest orderRequest){
        return orderService.updateOrder(orderRequest.getOrderId(),orderRequest.getQuantity());
    }

    @DeleteMapping("delete/order")
    public String deleteOrder(@RequestBody OrderRequest orderRequest){
        return orderService.deleteOrder(orderRequest.getOrderId());
    }



}
