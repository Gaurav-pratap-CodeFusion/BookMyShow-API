package com.gpcf.BookMyShow.Repository;

import com.gpcf.BookMyShow.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
