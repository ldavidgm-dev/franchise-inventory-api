package com.luisgarcia.franchise.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luisgarcia.franchise.dto.request.FranchiseRequest;
import com.luisgarcia.franchise.dto.request.UpdateNameRequest;
import com.luisgarcia.franchise.dto.response.FranchiseResponse;
import com.luisgarcia.franchise.service.FranchiseService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/franchises")
public class FranchiseController {

    private final FranchiseService franchiseService;

    public FranchiseController(FranchiseService franchiseService) {
        this.franchiseService = franchiseService;
    }

    @PostMapping
    public ResponseEntity<FranchiseResponse> createFranchise(
            @Valid @RequestBody FranchiseRequest request
    ) {
        FranchiseResponse response = franchiseService.createFranchise(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<FranchiseResponse> updateName(
            @PathVariable Long id,
            @Valid @RequestBody UpdateNameRequest request
    ) {
        FranchiseResponse response =
                franchiseService.updateName(id, request.getName());

        return ResponseEntity.ok(response);
    }    
}
