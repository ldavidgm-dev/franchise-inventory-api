package com.luisgarcia.franchise.dto.request;

import jakarta.validation.constraints.NotBlank;

public class BranchRequest {
    
    @NotBlank(message = "Branch name is required")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
