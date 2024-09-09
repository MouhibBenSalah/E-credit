package com.spring.user.Repository;

import com.spring.user.Entity.User;
import com.spring.user.Enum.Role;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository <User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByNumCin(long numCin);
    List<User> findByRole(Role role);

}
