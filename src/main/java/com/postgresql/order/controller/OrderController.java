package com.postgresql.order.controller;


import com.postgresql.order.dto.OrdersDto;
import com.postgresql.order.entity.Item;
import com.postgresql.order.entity.Orders;
import com.postgresql.order.request.OrderRequest;
import com.postgresql.order.service.ItemService;
import com.postgresql.order.service.OrderService;
import com.postgresql.order.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.criteria.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("api/v1")
@Tag(name = "Order Controller", description = "Order Controller")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @Autowired
    private ItemService itemService;

    @PostMapping("create/order")
    public Orders createOrder(@RequestBody OrderRequest orderRequest) {

        return orderService.createOrder(orderRequest);
    }

    @Operation(summary = "Get Order By Id")
    @GetMapping("get/order")
    public List<OrdersDto> getOrders(){
        return orderService.getOrders();
    }

    @PutMapping("update/order/{id}")
    public Orders updateItem(@PathVariable Long id,@RequestBody OrderRequest orderRequest){
        return orderService.updateOrder(id,orderRequest.getQuantity());
    }

    @DeleteMapping("delete/order/{id}")
    public String deleteOrder(@PathVariable Long id){
        return orderService.deleteOrder(id);
    }



}
