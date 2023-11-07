package com.postgresql.order.service;

import com.postgresql.order.entity.Item;
import com.postgresql.order.exception.MainServiceBusinessException;
import com.postgresql.order.repo.ItemRepository;
import com.postgresql.order.request.ItemRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public Item createItem(Item item) throws MainServiceBusinessException {

        try {
            if (itemRepository.existsByName(item.getName())) {
                throw new MainServiceBusinessException("Item with name {%s} already exists".formatted(item.getName()));
            }

            return itemRepository.save(item);

        } catch (Exception e) {
            throw new MainServiceBusinessException("Error creating item");
        }

    }


    public Item getItemById(Long itemId) {
        return itemRepository.findById(itemId).orElse(null);
    }


}
