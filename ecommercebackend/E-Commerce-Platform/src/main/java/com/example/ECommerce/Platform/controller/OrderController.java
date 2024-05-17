package com.example.ECommerce.Platform.controller;

import com.example.ECommerce.Platform.dto.OrderDTO;
import com.example.ECommerce.Platform.entity.Order;
import com.example.ECommerce.Platform.exception.ProductNotFoundException;
import com.example.ECommerce.Platform.serviceimplementation.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    private OrderService orderService;
    public OrderController(OrderService orderService){
        this.orderService=orderService;
    }
    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody  OrderDTO orderDTO) throws ProductNotFoundException{

            Order newOrder = orderService.createOrder(orderDTO);
            return new ResponseEntity<>(newOrder, HttpStatus.CREATED);

    }
}
