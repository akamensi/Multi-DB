package com.multi.product.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.multi.product.entity.Product;
import com.multi.product.entity.dto.ProductDTO;
import com.multi.product.repository.ProductRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductServiceImpl implements IProductService {

	private final ProductRepository productRepository;

	public ProductServiceImpl(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Override
	public List<ProductDTO> getAllProducts() {
		return productRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	@Override
	public ProductDTO getProductById(Long productId) {
		Product product = productRepository.findById(productId)
				.orElseThrow(() -> new RuntimeException("Product not found"));
		return convertToDTO(product);
	}

	@Override
	public ProductDTO createProduct(ProductDTO productDTO) {
		Product product = convertToEntity(productDTO);
		Product savedProduct = productRepository.save(product);
		return convertToDTO(savedProduct);
	}

	@Override
	public ProductDTO updateProduct(Long productId, ProductDTO productDTO) {
	    // Fetch the existing product
	    Product existingProduct = productRepository.findById(productId)
	            .orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + productId));

	    // Update only the fields that are provided in the DTO
	    if (productDTO.getName() != null) {
	        existingProduct.setName(productDTO.getName());
	    }
	    if (productDTO.getPrice() > 0) { // Assuming price should not be negative or zero; adjust as necessary
	        existingProduct.setPrice(productDTO.getPrice());
	    }

	    // Save the updated product
	    Product updatedProduct = productRepository.save(existingProduct);
	    
	    // Convert to DTO and return
	    return convertToDTO(updatedProduct);
	}

	@Override
	public void deleteProductById(Long productId) {
		productRepository.deleteById(productId);
	}

	
	
	// Conversion methods
	private ProductDTO convertToDTO(Product product) {
		ProductDTO dto = new ProductDTO();
		dto.setProductId(product.getProductId());
		dto.setName(product.getName());
		dto.setPrice(product.getPrice());
		return dto;
	}

	private Product convertToEntity(ProductDTO productDTO) {
		Product product = new Product();
		product.setProductId(productDTO.getProductId());
		product.setName(productDTO.getName());
		product.setPrice(productDTO.getPrice());
		return product;
	}

}
