package com.postgresql.order.service;

import com.postgresql.order.dto.OrdersDto;
import com.postgresql.order.entity.Orders;
import com.postgresql.order.exception.DuplicateResourceException;
import com.postgresql.order.exception.MainServiceBusinessException;
import com.postgresql.order.repo.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class OrderService {

    @Autowired
    private OrdersRepository orderRepository;

    public Orders createOrder(Orders order)  {
        return orderRepository.save(order);
    }

    public List<OrdersDto> getOrders() throws MainServiceBusinessException{
        try{
            List<Orders> orders = orderRepository.findAll();
            List<OrdersDto> ordersDto = new ArrayList<>();

            for (Orders order : orders){
                OrdersDto or = new OrdersDto();
                or.setUserName(order.getUsers().getUserName());
                or.setOrderId(order.getOrderId());
                or.setQuantity(order.getQuantity());
                or.setName(order.getItem().getName());

                ordersDto.add(or);
            }
            return ordersDto;
        }catch (Exception ex){
            throw new MainServiceBusinessException("Error Getting Order");
        }
    }


    public Orders updateOrder(Long id, Integer quantity) throws MainServiceBusinessException{

        try{
            Orders order = orderRepository.findById(id).orElse(null);
            if (order == null){
                throw new DuplicateResourceException("There is no order");
            }
            order.setQuantity(quantity);
            return orderRepository.save(order);

        }catch (Exception ex){
            throw new MainServiceBusinessException("Error Updating Order");
        }

    }

    public String deleteOrder(Long id){
        try{
            Orders order = orderRepository.findById(id).orElse(null);
            if (order == null){
                throw new DuplicateResourceException("There is no order");
            }
            orderRepository.delete(order);
            return "Order Deleted Sucesfully";

        }catch (Exception ex){
            throw new MainServiceBusinessException("Error Updating Order");
        }
    }
}
