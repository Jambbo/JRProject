package com.example.jrproject.service;

import com.example.jrproject.domain.user.User;

public interface UserService {

    User getByUsername(String username);
    User getById(Long id);

    User create(User user);

    void delete(Long id);

}
