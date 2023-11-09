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
        log.info("ItemServices: Creating item with name {}", item.getName());
        try {
            if (itemRepository.existsByName(item.getName())) {
                throw new MainServiceBusinessException("Item with name {%s} already exists".formatted(item.getName()));
            }
            log.info("ItemServices: Saving item with name {}", item.getName());
            return itemRepository.save(item);
        }catch (MainServiceBusinessException e){
            log.error("ItemServices: Error creating item"+e.getMessage());
            throw e;
        } catch (Exception e) {
            throw new MainServiceBusinessException("Error creating item");
        }

    }


    public Item getItemById(Long itemId) {
        try{
            log.info("ItemServices: Fetching item with id {}", itemId);
            return itemRepository.findById(itemId).orElseThrow(() -> new MainServiceBusinessException("Item with id {%s} not found".formatted(itemId)));

        }catch (MainServiceBusinessException e) {
            log.error("ItemServices: Error fetching item" + e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("ItemServices: Error fetching item" + e.getMessage());
            throw new MainServiceBusinessException("Error fetching item");
        }
    }


    public Item updateItem(Long id, ItemRequest itemRequest) {
        log.info("ItemServices: Updating item with id {}", id);
        try {
            Item item = itemRepository.findById(id).orElseThrow(() -> new MainServiceBusinessException("Item with id {%s} not found".formatted(id)));
            if (itemRequest.getName() != null ) {
                item.setName(itemRequest.getName());
            }
            if (itemRequest.getPrice() != null ) {
                item.setPrice(itemRequest.getPrice());
            }
            if (itemRequest.getQuantity() != null ) {
                item.setQuantity(itemRequest.getQuantity());
            }
            return itemRepository.save(item);
        }catch (MainServiceBusinessException e){
            log.error("ItemServices: Error updating item"+e.getMessage());
            throw e;
        }
        catch (Exception e) {
            log.error("ItemServices: Error updating item"+e.getMessage());
            throw new MainServiceBusinessException("Error updating item");
        }
    }

    public String deleteItem(Long id) {
        try{
            log.info("ItemServices: Deleting item Start with id {}", id);
            Item item = itemRepository.findById(id).orElseThrow(() -> new MainServiceBusinessException("Item with id {%s} not found".formatted(id)));
            log.info("ItemServices: Deleting item with id {}", id);
            itemRepository.delete(item);
            return " Item with id {%s} deleted successfully";
        }catch (MainServiceBusinessException e){
            log.error("ItemServices: Error deleting item"+e.getMessage());
            throw e;
        }
        catch (Exception e) {
            log.error("ItemServices:unexpected Error deleting item"+e.getMessage());
            throw new MainServiceBusinessException("Error deleting item");
        }
    }
}
