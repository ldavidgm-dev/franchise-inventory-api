package com.luisgarcia.franchise.service;

import org.springframework.stereotype.Service;

import com.luisgarcia.franchise.dto.request.FranchiseRequest;
import com.luisgarcia.franchise.dto.response.FranchiseResponse;
import com.luisgarcia.franchise.exception.ResourceNotFoundException;
import com.luisgarcia.franchise.model.Franchise;
import com.luisgarcia.franchise.repository.FranchiseRepository;

@Service
public class FranchiseService {

    private final FranchiseRepository franchiseRepository;

    public FranchiseService(FranchiseRepository franchiseRepository) {
        this.franchiseRepository = franchiseRepository;
    }

    public FranchiseResponse createFranchise(FranchiseRequest request) {

        Franchise franchise = new Franchise();
        franchise.setName(request.getName());

        Franchise saved = franchiseRepository.save(franchise);

        return new FranchiseResponse(
                saved.getId(),
                saved.getName()
        );
    }

    public FranchiseResponse updateName(Long id, String name) {

        Franchise franchise = franchiseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Franchise", id));

        franchise.setName(name);

        Franchise updated = franchiseRepository.save(franchise);

        return new FranchiseResponse(
                updated.getId(),
                updated.getName()
        );
    }    
}
