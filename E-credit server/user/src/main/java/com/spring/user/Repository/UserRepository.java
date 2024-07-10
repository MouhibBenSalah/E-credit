package com.spring.user.Repository;

import com.spring.user.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository <User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByNumCin(long numCin);

}
