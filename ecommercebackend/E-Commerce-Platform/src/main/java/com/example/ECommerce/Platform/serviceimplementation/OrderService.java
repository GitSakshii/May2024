package com.example.ECommerce.Platform.serviceimplementation;

import com.example.ECommerce.Platform.dto.OrderDTO;
import com.example.ECommerce.Platform.entity.Order;
import com.example.ECommerce.Platform.entity.OrderItem;
import com.example.ECommerce.Platform.entity.Product;
import com.example.ECommerce.Platform.exception.ProductNotFoundException;
import com.example.ECommerce.Platform.repository.OrderRepository;
import com.example.ECommerce.Platform.repository.ProductRepository;
import com.example.ECommerce.Platform.serviceInterface.OrderServiceInterface;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService implements OrderServiceInterface {
    private OrderRepository orderRepository;
    private ProductRepository productRepository;
    private ObjectMapper objectMapper;
    public OrderService(OrderRepository orderRepository,ProductRepository productRepository,ObjectMapper objectMapper){
        this.orderRepository=orderRepository;
        this.productRepository=productRepository;
        this.objectMapper=objectMapper;
    }

    @Override
    public Order createOrder(OrderDTO orderDTO) throws ProductNotFoundException {
        // Convert OrderDTO to Order entity
        Order newOrder = new Order();
        newOrder.setCustomerName(orderDTO.getCustomerName());
        newOrder.setCustomerEmail(orderDTO.getCustomerEmail());

        // Process each order item
        List<OrderItem> orderItems = orderDTO.getOrderItems().stream().map(orderItemDTO -> {
            OrderItem orderItem = new OrderItem();
            Product product = null;
            try {
                product = productRepository.findById(orderItemDTO.getProductId())
                        .orElseThrow(() -> new ProductNotFoundException(orderItemDTO.getProductId()));
            } catch (ProductNotFoundException e) {
                throw new RuntimeException(e);
            }


            orderItem.setProduct(product);
            orderItem.setQuantity(orderItemDTO.getQuantity());
            orderItem.setSubTotal(product.getPrice().multiply(BigDecimal.valueOf(orderItemDTO.getQuantity())));
            orderItem.setOrder(newOrder); // Set the order reference in the order item
            return orderItem;
        }).collect(Collectors.toList());

        newOrder.setOrderItems(orderItems);

        // Save the order along with order items
        return orderRepository.save(newOrder);
    }
}
