package com.luisgarcia.franchise.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.luisgarcia.franchise.dto.request.FranchiseRequest;
import com.luisgarcia.franchise.dto.response.FranchiseResponse;
import com.luisgarcia.franchise.service.FranchiseService;

import jakarta.validation.Valid;

@Controller
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
}
