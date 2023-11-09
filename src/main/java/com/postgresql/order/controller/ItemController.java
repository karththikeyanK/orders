package com.postgresql.order.controller;

import com.postgresql.order.entity.Item;
import com.postgresql.order.request.ItemRequest;
import com.postgresql.order.service.ItemService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
//@RequestMapping("api/v1")
@Tag(name = "Item Controller", description = "Item Controller")
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

    @GetMapping("get/item/{id}")
    public Item getItemById(@PathVariable Long id){
        return itemService.getItemById(id);
    }

    @PutMapping("update/item/{id}")
    public Item updateItem(@PathVariable Long id, @RequestBody ItemRequest itemRequest){
        return itemService.updateItem(id, itemRequest);
    }

    @DeleteMapping("delete/item/{id}")
    public String deleteItem(@PathVariable Long id){
        return itemService.deleteItem(id);
    }



}
