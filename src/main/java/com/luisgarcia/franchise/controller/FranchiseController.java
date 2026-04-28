package com.luisgarcia.franchise.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luisgarcia.franchise.dto.request.FranchiseRequest;
import com.luisgarcia.franchise.dto.response.FranchiseResponse;
import com.luisgarcia.franchise.dto.response.TopProductResponse;
import com.luisgarcia.franchise.service.FranchiseService;
import com.luisgarcia.franchise.service.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/franchises")
public class FranchiseController {

    private final FranchiseService franchiseService;
    private final ProductService productService;

    public FranchiseController(FranchiseService franchiseService,
            ProductService productService) {
        this.franchiseService = franchiseService;
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<FranchiseResponse> createFranchise(
            @Valid @RequestBody FranchiseRequest request
    ) {
        FranchiseResponse response = franchiseService.createFranchise(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
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
