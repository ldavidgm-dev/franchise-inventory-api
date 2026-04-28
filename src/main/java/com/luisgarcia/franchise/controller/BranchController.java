package com.luisgarcia.franchise.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luisgarcia.franchise.dto.request.BranchRequest;
import com.luisgarcia.franchise.dto.response.BranchResponse;
import com.luisgarcia.franchise.service.BranchService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/franchises")
public class BranchController {

        private final BranchService branchService;

    public BranchController(BranchService branchService) {
        this.branchService = branchService;
    }

    @PostMapping("/{id}/branches")
    public ResponseEntity<BranchResponse> createBranch(
            @PathVariable Long id,
            @Valid @RequestBody BranchRequest request
    ) {
        BranchResponse response = branchService.createBranch(id, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
