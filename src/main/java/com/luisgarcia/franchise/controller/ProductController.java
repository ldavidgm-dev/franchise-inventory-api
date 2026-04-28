package com.luisgarcia.franchise.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luisgarcia.franchise.dto.request.ProductRequest;
import com.luisgarcia.franchise.dto.response.ProductResponse;
import com.luisgarcia.franchise.service.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/branches")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/{id}/products")
    public ResponseEntity<ProductResponse> createProduct(
            @PathVariable Long id,
            @Valid @RequestBody ProductRequest request
    ) {
        ProductResponse response = productService.createProduct(id, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
