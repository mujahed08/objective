package com.objective.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.objective.models.RoleEnum;
import com.objective.models.Role;

public interface RoleRepository extends MongoRepository<Role, String> {
  Optional<Role> findByName(RoleEnum name);
}
