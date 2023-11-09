package com.postgresql.order.service;

import com.postgresql.order.dto.OrdersDto;
import com.postgresql.order.entity.Item;
import com.postgresql.order.entity.Orders;
import com.postgresql.order.entity.Users;
import com.postgresql.order.exception.DuplicateResourceException;
import com.postgresql.order.exception.MainServiceBusinessException;
import com.postgresql.order.repo.ItemRepository;
import com.postgresql.order.repo.OrdersRepository;
import com.postgresql.order.repo.UsersRepository;
import com.postgresql.order.request.OrderRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@Slf4j
public class OrderService {

    @Autowired
    private OrdersRepository orderRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private UsersRepository usersRepository;

    public Orders createOrder(OrderRequest orderRequest)  {
        log.info("OrderService::createOrder started");
        Orders savedOrder;
        try {
            Orders order  = new Orders();
            log.info("Fetching item for order ID: {}", orderRequest.getItemId());
            Item item = itemRepository.findById(orderRequest.getItemId()).orElseThrow(()->new MainServiceBusinessException("Item not found with item id: "+orderRequest.getItemId()));
            log.info("Item found: {}", item);

            log.info("Fetching user for user ID: {}", orderRequest.getUserId());
            Users user = usersRepository.findById(orderRequest.getUserId()).orElseThrow(()->new MainServiceBusinessException("User not found with user id: "+orderRequest.getUserId()));
            log.info("User found: {}", user);

            order.setItem(item);
            order.setUsers(user);
            order.setQuantity(orderRequest.getQuantity());

            savedOrder = orderRepository.save(order);

            log.info("OrderService::createOrder response {}", savedOrder);
            return savedOrder;
        } catch (MainServiceBusinessException ex) {
            log.error("Error Creating Order: " + ex.getMessage());
            throw ex;
        } catch (Exception ex) {
            log.error("Error Creating Order", ex);
            throw new RuntimeException("Error Creating Order");
        }
    }


    public List<OrdersDto> getOrders(){
        log.info("OrderService::getOrders started");
        try{
            List<Orders> orders = orderRepository.findAll();
            if (orders.isEmpty()){
                throw new MainServiceBusinessException("There is no order");
            }
            List<OrdersDto> ordersDto = new ArrayList<>();
            for (Orders order : orders){
                OrdersDto or = new OrdersDto();
                or.setUserName(order.getUsers().getUserName());
                or.setOrderId(order.getOrderId());
                or.setQuantity(order.getQuantity());
                or.setName(order.getItem().getName());

                ordersDto.add(or);
            }
            log.info("OrderService::getOrders response");
            return ordersDto;
        }catch (MainServiceBusinessException ex){
            log.error("Error Getting Orders: " + ex.getMessage());
            throw ex;
        }
        catch (Exception ex){
            log.error("Error Getting Orders: "+ ex.getMessage());
            throw new RuntimeException("Error Getting Orders");
        }

    }


    public Orders updateOrder(Long id, Integer quantity){
        log.info("OrderService::updateOrder started");
        try{
            Orders order = orderRepository.findById(id).orElseThrow(()-> new MainServiceBusinessException("There is no order with id: "+id));
            order.setQuantity(quantity);
            log.info("OrderService::updateOrder response");
            return orderRepository.save(order);

        }catch (MainServiceBusinessException ex){
            log.error("Error Updating Order: " + ex.getMessage());
            throw ex;
        }
        catch (Exception ex){
            log.error("Error Updating Order: "+ ex.getMessage());
            throw new RuntimeException("Error Updating Order");
        }

    }

    public String deleteOrder(Long id){
        log.info("OrderService::deleteOrder started");
        try{
            Orders order = orderRepository.findById(id).orElseThrow(()-> new MainServiceBusinessException("There is no order with id: "+id));
            orderRepository.delete(order);
            log.info("OrderService::deleteOrder response");
            return "Order Deleted Sucesfully";

        }catch (MainServiceBusinessException ex){
            log.error("Error Deleting Order: " + ex.getMessage());
            throw ex;
        }
        catch (Exception ex){
            log.error("Error Deleting Order: "+ ex.getMessage());
            throw new RuntimeException("Error Deleting Order");
        }

    }
}
