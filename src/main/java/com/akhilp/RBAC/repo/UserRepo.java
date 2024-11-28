package com.akhilp.RBAC.repo;

import com.akhilp.RBAC.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository< Users, Integer> {
    Users findByUsername(String username);
    boolean existsByUsername(String username);
}
