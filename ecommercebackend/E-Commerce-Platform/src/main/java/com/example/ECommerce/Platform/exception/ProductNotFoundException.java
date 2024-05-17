package com.example.ECommerce.Platform.exception;

public class ProductNotFoundException extends Exception{
    public ProductNotFoundException(Long id)
    {
       super("Product with id :"+id+" not found!");
    }
}
