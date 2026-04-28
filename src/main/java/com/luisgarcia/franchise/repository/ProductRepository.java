package com.luisgarcia.franchise.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luisgarcia.franchise.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
