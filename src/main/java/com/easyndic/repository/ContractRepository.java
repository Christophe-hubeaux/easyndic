package com.easyndic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.easyndic.model.Contract;

public interface ContractRepository extends JpaRepository<Contract, Integer> {
}
