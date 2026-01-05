package com.gpcf.BookMyShow.Repository;

import com.gpcf.BookMyShow.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserModel, Long> {
    Optional<UserModel> findByEmail(String username);
    boolean existsByEmail(String email);
}
