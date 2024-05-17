package com.example.ECommerce.Platform.serviceimplementation;

import com.example.ECommerce.Platform.dto.ProductDTO;
import com.example.ECommerce.Platform.entity.Product;
import com.example.ECommerce.Platform.exception.ProductNotFoundException;
import com.example.ECommerce.Platform.repository.ProductRepository;
import com.example.ECommerce.Platform.serviceInterface.ProductServiceInterface;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Optional;

@Service
public class ProductService implements ProductServiceInterface {
    private ProductRepository productRepository;
    private ObjectMapper objectMapper;
    public ProductService(ProductRepository productRepository,ObjectMapper objectMapper){
        this.productRepository=productRepository;
        this.objectMapper=objectMapper;

    }
    @Override
    public Product createProduct(ProductDTO productDTO){
         Product newProduct=objectMapper.convertValue(productDTO,Product.class);
         newProduct.setCreatedAt(Timestamp.from(Instant.now()));
         return productRepository.save(newProduct);
    }
    @Override
    public Product getProductById(Long id)throws ProductNotFoundException{
      Optional<Product> foundProduct=productRepository.findById(id);
      if(foundProduct.isPresent())
      {
          return foundProduct.get();
      }
      else throw new ProductNotFoundException(id);
     }
    @Override
    public Product updateProduct(Long productId,ProductDTO productDTO)throws ProductNotFoundException
    {
        Product existingProduct=getProductById(productId);

        //Update ProductDetails
        existingProduct.setName(productDTO.getName());
        existingProduct.setDescription(productDTO.getDescription());
        existingProduct.setPrice(productDTO.getPrice());
        existingProduct.setQuantity(productDTO.getQuantity());
        existingProduct.setUpdated_at(Timestamp.from(Instant.now()));
        return productRepository.save(existingProduct);

    }
    @Override
    public Product deleteProduct(Long productId)throws ProductNotFoundException{
      Product existingProduct=getProductById(productId);
      productRepository.delete(existingProduct);
      return existingProduct;

    }
}
