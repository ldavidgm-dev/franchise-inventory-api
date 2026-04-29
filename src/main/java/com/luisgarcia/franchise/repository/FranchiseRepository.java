package com.luisgarcia.franchise.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luisgarcia.franchise.model.Franchise;

public interface FranchiseRepository extends JpaRepository<Franchise, Long>{

}
