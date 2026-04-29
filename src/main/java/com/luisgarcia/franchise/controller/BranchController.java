package com.luisgarcia.franchise.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luisgarcia.franchise.dto.request.UpdateNameRequest;
import com.luisgarcia.franchise.dto.response.BranchResponse;
import com.luisgarcia.franchise.service.BranchService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/branches")
public class BranchController {

    private final BranchService branchService;

    public BranchController(BranchService branchService) {
        this.branchService = branchService;
    }

    @PatchMapping("/{id}")
    public ResponseEntity<BranchResponse> updateName(
            @PathVariable Long id,
            @Valid @RequestBody UpdateNameRequest request
    ) {
        BranchResponse response =
                branchService.updateName(id, request.getName());

        return ResponseEntity.ok(response);
    }    
}
