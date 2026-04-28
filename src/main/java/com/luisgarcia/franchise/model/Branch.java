package com.luisgarcia.franchise.model;

import jakarta.persistence.*;

@Entity
@Table(name = "branch")
public class Branch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "franchise_id", nullable = false)
    private Franchise franchise;

    public Branch() {}

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Franchise getFranchise() {
        return franchise;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFranchise(Franchise franchise) {
        this.franchise = franchise;
    }
}
