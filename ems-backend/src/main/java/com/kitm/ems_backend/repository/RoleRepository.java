package com.kitm.ems_backend.repository;


import com.kitm.ems_backend.entity.Role;
import com.kitm.ems_backend.enums.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(Roles name);
}
