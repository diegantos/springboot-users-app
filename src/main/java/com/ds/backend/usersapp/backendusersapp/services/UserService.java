package com.ds.backend.usersapp.backendusersapp.services;

import com.ds.backend.usersapp.backendusersapp.models.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAll();
    Optional<User> findById(Long id);
    User save(User user);
    void remove(Long id);
}
