package com.example.jrproject.service.impl;

import com.example.jrproject.domain.user.Role;
import com.example.jrproject.domain.user.User;
import com.example.jrproject.repository.UserRepository;
import com.example.jrproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Cacheable(value = "UserService::getById",key = "#id")
    public User getById(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new RuntimeException("User with id: "+id+" not found.")
        );
    }

    @Override
    @Cacheable(value = "UserService::getByUsername",key = "#username")
    public User getByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(
                ()->new RuntimeException("User with username: "+username+" not found.")
        );
    }

    @Override
    @Caching(cacheable = {
            @Cacheable(value = "UserService::getByUsername",key = "#user.username"),
            @Cacheable(value = "UserService::getById",key = "#user.id")
    })
    public User create(User user){
        if(userRepository.findByUsername(user.getUsername()).isPresent()){
            throw new IllegalStateException("User already exists.");
        }
        if(!(user.getPassword().equals(user.getPasswordConfirmation()))){
            throw new IllegalStateException("Password and password confirmation do not match.");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Set<Role> roles = Set.of(Role.USER);
        user.setRoles(roles);
        userRepository.save(user);
        return user;
    }

    @Override
    @Transactional
    @CacheEvict(value = "UserService::getById",key = "#id")
    public void delete(Long id) {
        userRepository.deleteById(id);
    }


}
