package com.example.ECommerce.Platform.serviceInterface;

import com.example.ECommerce.Platform.dto.ProductDTO;
import com.example.ECommerce.Platform.entity.Product;

public interface ProductServiceInterface {
Product createProduct(ProductDTO productDTO);
Product getProductById(Long id)throws Exception;
Product updateProduct(Long productId,ProductDTO productDTO)throws Exception;
Product deleteProduct(Long id)throws Exception;

}
