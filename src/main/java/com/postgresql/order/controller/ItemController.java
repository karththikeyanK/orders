package com.postgresql.order.controller;

import com.postgresql.order.entity.Item;
import com.postgresql.order.request.ItemRequest;
import com.postgresql.order.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping("create/item")
    public Item createItem(@RequestBody ItemRequest itemRequest) {
        Item item = new Item();
        item.setName(itemRequest.getName());
        item.setPrice(itemRequest.getPrice());
        item.setQuantity(itemRequest.getQuantity());
        return itemService.createItem(item);
    }


}
