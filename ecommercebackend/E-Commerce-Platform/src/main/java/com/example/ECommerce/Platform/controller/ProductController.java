package com.example.ECommerce.Platform.controller;

import com.example.ECommerce.Platform.dto.ProductDTO;
import com.example.ECommerce.Platform.entity.Product;
import com.example.ECommerce.Platform.exception.ProductNotFoundException;
import com.example.ECommerce.Platform.serviceimplementation.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private ProductService productService;
    public ProductController(ProductService productService){
        this.productService=productService;
    }
    @PostMapping("/addProduct")
    public ResponseEntity<Product> createProduct(@RequestBody @Valid ProductDTO productDTO) {
        Product newProduct = productService.createProduct(productDTO);
        return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) throws ProductNotFoundException {
            Product product = productService.getProductById(id);
            return new ResponseEntity<>(product, HttpStatus.OK);

    }

}
