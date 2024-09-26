package com.multi.product.controller;

import java.util.List;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.multi.product.entity.dto.ProductDTO;
import com.multi.product.service.ProductServiceImpl;

@RestController
@RequestMapping("/api/products")
public class ProductController {
	
	 private final ProductServiceImpl productService;

	    public ProductController(ProductServiceImpl productService) {
	        this.productService = productService;
	    }

	    // Get all products
	    @GetMapping
	    public ResponseEntity<List<ProductDTO>> getAllProducts() {
	        List<ProductDTO> products = productService.getAllProducts();
	        return new ResponseEntity<>(products, HttpStatus.OK);
	    }

	    // Get a product by ID
	    @GetMapping("/{id}")
	    public ResponseEntity<ProductDTO> getProductById(@PathVariable("id") Long productId) {
	        ProductDTO product = productService.getProductById(productId);
	        return new ResponseEntity<>(product, HttpStatus.OK);
	    }

	    // Create a new product
	    @PostMapping
	    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO) {
	        ProductDTO newProduct = productService.createProduct(productDTO);
	        return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
	    }

	    // Update an existing product
	    @PutMapping("/{id}")
	    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
	        ProductDTO updatedProduct = productService.updateProduct(id, productDTO);
	        return ResponseEntity.ok(updatedProduct);
	    }

	    // Delete a product by ID
	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> deleteProductById(@PathVariable("id") Long productId) {
	        productService.deleteProductById(productId);
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    }

}
