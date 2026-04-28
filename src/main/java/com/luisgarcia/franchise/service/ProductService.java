package com.luisgarcia.franchise.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luisgarcia.franchise.dto.request.ProductRequest;
import com.luisgarcia.franchise.dto.response.ProductResponse;
import com.luisgarcia.franchise.dto.response.TopProductResponse;
import com.luisgarcia.franchise.exception.BusinessRuleException;
import com.luisgarcia.franchise.exception.ResourceNotFoundException;
import com.luisgarcia.franchise.model.Branch;
import com.luisgarcia.franchise.model.Product;
import com.luisgarcia.franchise.repository.BranchRepository;
import com.luisgarcia.franchise.repository.FranchiseRepository;
import com.luisgarcia.franchise.repository.ProductRepository;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final BranchRepository branchRepository;
    private final FranchiseRepository franchiseRepository;

    public ProductService(ProductRepository productRepository,
            BranchRepository branchRepository,
            FranchiseRepository franchiseRepository) {
        this.productRepository = productRepository;
        this.branchRepository = branchRepository;
        this.franchiseRepository = franchiseRepository;
    }

    public ProductResponse createProduct(Long branchId, ProductRequest request) {

        Branch branch = branchRepository.findById(branchId)
                .orElseThrow(() -> new ResourceNotFoundException("Branch", branchId));

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

    @Transactional
    public void deleteProduct(Long productId) {

        Product product = productRepository.findById(productId)
            .orElseThrow(() -> new ResourceNotFoundException("Product", productId));

        productRepository.delete(product);
    }

    @Transactional
    public ProductResponse updateStock(Long productId, Integer stock) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", productId));

        if (stock < 0) {
            throw new BusinessRuleException("Stock cannot be negative");
        }

        product.setStock(stock);

        Product updated = productRepository.save(product);

        return new ProductResponse(
                updated.getId(),
                updated.getName(),
                updated.getStock(),
                updated.getBranch().getId()
        );
    }

    public List<TopProductResponse> getTopProductsByFranchise(Long franchiseId) {

        if (!franchiseRepository.existsById(franchiseId)) {
            throw new BusinessRuleException("Franchise not found");
        }

        List<Product> products =
                productRepository.findWithBranchByBranchFranchiseId(franchiseId);

        if (products.isEmpty()) {
            return Collections.emptyList();
        }

        Map<Long, List<Product>> grouped =
                products.stream()
                        .collect(Collectors.groupingBy(p -> p.getBranch().getId()));

        List<TopProductResponse> result = new ArrayList<>();

        for (List<Product> branchProducts : grouped.values()) {

            Product top = branchProducts.stream()
                    .max(Comparator.comparing(Product::getStock))
                    .orElse(null);

            if (top != null) {
                result.add(new TopProductResponse(
                        top.getBranch().getId(),
                        top.getBranch().getName(),
                        top.getId(),
                        top.getName(),
                        top.getStock()
                ));
            }
        }

        return result;
    }
    
    public ProductResponse updateName(Long id, String name) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product", id));

        product.setName(name);

        Product updated = productRepository.save(product);

        return new ProductResponse(
                updated.getId(),
                updated.getName(),
                updated.getStock(),
                updated.getBranch().getId()
        );
    }    
}
