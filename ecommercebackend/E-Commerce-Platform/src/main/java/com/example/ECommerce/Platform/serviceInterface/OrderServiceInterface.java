package com.example.ECommerce.Platform.serviceInterface;

import com.example.ECommerce.Platform.dto.OrderDTO;
import com.example.ECommerce.Platform.entity.Order;

public interface OrderServiceInterface {
    public Order createOrder(OrderDTO orderDTO)throws Exception;
}
