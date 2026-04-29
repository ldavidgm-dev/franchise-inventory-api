package com.luisgarcia.franchise.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ProductRequest {

    @NotBlank(message = "Product name is required")
    private String name;

    @NotNull
    @Min(value = 0, message = "Stock cannot be negative")
    private Integer stock;

    public String getName() {
        return name;
    }

    public Integer getStock() {
        return stock;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}
