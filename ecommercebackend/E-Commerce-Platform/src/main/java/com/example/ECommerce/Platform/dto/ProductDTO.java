package com.example.ECommerce.Platform.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    @NotBlank(message = "Product name is empty")
    private String name;
    @NotBlank(message = "description is empty")
    private String description;
    @NotBlank(message = "price is empty")
    private BigDecimal price;
    @NotBlank(message = "quantity is empty")
    private Integer quantity;
}
