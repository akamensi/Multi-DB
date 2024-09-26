package com.multi.product.service;

import java.util.List;

import com.multi.product.entity.dto.ProductDTO;

public interface IProductService {
	
    List<ProductDTO> getAllProducts();
    
    ProductDTO getProductById(Long productId);
    
    ProductDTO createProduct(ProductDTO productDTO);
    
    ProductDTO updateProduct(Long productId, ProductDTO productDTO);
    
    void deleteProductById(Long productId);

}
