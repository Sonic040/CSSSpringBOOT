package com.example.CSS.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.CSS.Model.User;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
}