package com.luisgarcia.franchise.service;

import org.springframework.stereotype.Service;

import com.luisgarcia.franchise.dto.request.BranchRequest;
import com.luisgarcia.franchise.dto.response.BranchResponse;
import com.luisgarcia.franchise.model.Branch;
import com.luisgarcia.franchise.model.Franchise;
import com.luisgarcia.franchise.repository.BranchRepository;
import com.luisgarcia.franchise.repository.FranchiseRepository;

@Service
public class BranchService {

    private final BranchRepository branchRepository;
    private final FranchiseRepository franchiseRepository;

    public BranchService(BranchRepository branchRepository,
                         FranchiseRepository franchiseRepository) {
        this.branchRepository = branchRepository;
        this.franchiseRepository = franchiseRepository;
    }

    public BranchResponse createBranch(Long franchiseId, BranchRequest request) {

        Franchise franchise = franchiseRepository.findById(franchiseId)
                .orElseThrow(() -> new RuntimeException("Franchise not found"));

        Branch branch = new Branch();
        branch.setName(request.getName());
        branch.setFranchise(franchise);

        Branch saved = branchRepository.save(branch);

        return new BranchResponse(
                saved.getId(),
                saved.getName(),
                franchise.getId()
        );
    }
}
