package com.easyndic.repository;

import com.easyndic.model.Condominium;
import org.springframework.data.jpa.repository.JpaRepository;
import com.easyndic.model.CondominiumOwner;

public interface CondominiumOwnerRepository extends JpaRepository<CondominiumOwner, Integer> {
}
