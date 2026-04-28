package com.luisgarcia.franchise.service;

import org.springframework.stereotype.Service;

import com.luisgarcia.franchise.dto.request.ProductRequest;
import com.luisgarcia.franchise.dto.response.ProductResponse;
import com.luisgarcia.franchise.model.Branch;
import com.luisgarcia.franchise.model.Product;
import com.luisgarcia.franchise.repository.BranchRepository;
import com.luisgarcia.franchise.repository.ProductRepository;

@Service
public class ProductService {
 private final ProductRepository productRepository;
    private final BranchRepository branchRepository;

    public ProductService(ProductRepository productRepository,
                          BranchRepository branchRepository) {
        this.productRepository = productRepository;
        this.branchRepository = branchRepository;
    }

    public ProductResponse createProduct(Long branchId, ProductRequest request) {

        Branch branch = branchRepository.findById(branchId)
                .orElseThrow(() -> new RuntimeException("Branch not found"));

        Product product = new Product();
        product.setName(request.getName());
        product.setStock(request.getStock());
        product.setBranch(branch);

        Product saved = productRepository.save(product);

        return new ProductResponse(
                saved.getId(),
                saved.getName(),
                saved.getStock(),
                branch.getId()
        );
    }
}
