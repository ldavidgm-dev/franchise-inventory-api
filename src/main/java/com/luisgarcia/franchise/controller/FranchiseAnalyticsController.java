package com.luisgarcia.franchise.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luisgarcia.franchise.dto.response.TopProductResponse;
import com.luisgarcia.franchise.service.ProductService;

@RestController
@RequestMapping("/api/v1/franchises")
public class FranchiseAnalyticsController {

    private final ProductService productService;

    public FranchiseAnalyticsController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}/products/top")
    public ResponseEntity<List<TopProductResponse>> getTopProducts(
            @PathVariable Long id
    ) {

        List<TopProductResponse> response =
                productService.getTopProductsByFranchise(id);

        return ResponseEntity.ok(response);
    }   
}
