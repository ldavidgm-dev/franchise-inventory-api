package com.luisgarcia.franchise.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.luisgarcia.franchise.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByBranchFranchiseId(Long franchiseId);

    @EntityGraph(attributePaths = {"branch"})
    List<Product> findWithBranchByBranchFranchiseId(Long franchiseId);
}
