package com.example.ECommerce.Platform.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderDTO {
    private String customerName;
    private String customerEmail;
    private List<OrderItemDTO> orderItems;
}
