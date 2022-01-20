package com.example.peoplesinsights.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.peoplesinsights.model.Role;
import com.example.peoplesinsights.model.RoleName;

public interface RoleRepository extends JpaRepository<Role, Long>{

	Optional<Role> findByName(RoleName roleName);
}
