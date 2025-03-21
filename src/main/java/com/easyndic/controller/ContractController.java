package com.easyndic.controller;

import com.easyndic.model.Contract;
import com.easyndic.repository.CondominiumRepository;
import com.easyndic.repository.ContractRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contract")
public class ContractController {
    private final ContractRepository contractRepository;
    private final CondominiumRepository condominiumRepository;

    public ContractController(ContractRepository contractRepository, CondominiumRepository condominiumRepository) {
        this.contractRepository = contractRepository;
        this.condominiumRepository = condominiumRepository;
    }

    @GetMapping
    public Iterable<Contract> getAllContracts() { return this.contractRepository.findAll(); }

    @PostMapping("/new")
    public ResponseEntity<Contract> addContractToCondominium (@RequestBody Contract contract ) {
        contractRepository.save(contract);
        return ResponseEntity.ok(contract);
    }
}
