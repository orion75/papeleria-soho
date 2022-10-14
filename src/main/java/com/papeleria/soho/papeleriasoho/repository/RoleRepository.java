package com.papeleria.soho.papeleriasoho.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.papeleria.soho.papeleriasoho.model.ERole;
import com.papeleria.soho.papeleriasoho.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    
    Optional<Role> findByName(ERole name);
}
