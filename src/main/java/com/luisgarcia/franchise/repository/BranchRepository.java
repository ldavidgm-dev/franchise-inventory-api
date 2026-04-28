package com.luisgarcia.franchise.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luisgarcia.franchise.model.Branch;

public interface BranchRepository extends JpaRepository<Branch, Long>{

}
