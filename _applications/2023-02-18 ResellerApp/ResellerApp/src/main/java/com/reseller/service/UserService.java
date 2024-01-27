package com.reseller.service;

import com.reseller.model.entity.UserEntity;
import com.reseller.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity getById(Long id) {
        return userRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }
}
