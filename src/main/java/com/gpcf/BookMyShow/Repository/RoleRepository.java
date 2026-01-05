package com.gpcf.BookMyShow.Repository;

import com.gpcf.BookMyShow.model.Role;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByrole(String roleName);}
