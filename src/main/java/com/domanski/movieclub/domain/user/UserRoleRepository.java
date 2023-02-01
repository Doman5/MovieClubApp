package com.domanski.movieclub.domain.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRoleRepository extends CrudRepository<UserRole,Long> {
    Optional<UserRole> findByName(String name);
    List<UserRole> findAll();
}
