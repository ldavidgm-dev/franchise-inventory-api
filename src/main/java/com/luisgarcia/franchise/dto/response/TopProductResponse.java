package com.luisgarcia.franchise.dto.response;

public class TopProductResponse {

    private Long branchId;
    private String branchName;
    private Long productId;
    private String productName;
    private Integer stock;

    public TopProductResponse(Long branchId, String branchName,
                              Long productId, String productName, Integer stock) {
        this.branchId = branchId;
        this.branchName = branchName;
        this.productId = productId;
        this.productName = productName;
        this.stock = stock;
    }

    public Long getBranchId() {
        return branchId;
    }

    public String getBranchName() {
        return branchName;
    }

    public Long getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public Integer getStock() {
        return stock;
    }

}
