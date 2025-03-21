package com.easyndic.repository;

import org.springframework.data.repository.CrudRepository;
import com.easyndic.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer > {
}
