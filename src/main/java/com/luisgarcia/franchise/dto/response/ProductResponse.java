package com.luisgarcia.franchise.dto.response;

public class ProductResponse {

    private Long id;
    private String name;
    private Integer stock;
    private Long branchId;

    public ProductResponse(Long id, String name, Integer stock, Long branchId) {
        this.id = id;
        this.name = name;
        this.stock = stock;
        this.branchId = branchId;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getStock() {
        return stock;
    }

    public Long getBranchId() {
        return branchId;
    }
}
