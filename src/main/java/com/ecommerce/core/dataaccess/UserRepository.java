package com.ecommerce.core.dataaccess;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.core.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> getByEmail(String email);
}
