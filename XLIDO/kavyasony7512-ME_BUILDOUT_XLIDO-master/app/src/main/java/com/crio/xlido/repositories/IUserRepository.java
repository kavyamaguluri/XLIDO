package com.crio.xlido.repositories;

import java.util.List;
import java.util.Optional;
import com.crio.xlido.entities.User;

public interface IUserRepository {

    User save(User entity);
    List<User> findAll();
    Optional<User> findById(Long id);
    Optional<User> findByEmail(String email); // Method to find user by email
}


