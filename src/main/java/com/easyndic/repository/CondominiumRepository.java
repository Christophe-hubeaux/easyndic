package com.easyndic.repository;

import com.easyndic.model.Condominium;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CondominiumRepository extends CrudRepository<Condominium, Integer> {
    List<Condominium> findByOwnersId(Integer userId);
}
