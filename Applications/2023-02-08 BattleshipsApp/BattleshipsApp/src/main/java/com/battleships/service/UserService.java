package com.battleships.service;

import com.battleships.domain.entity.UserEntity;
import com.battleships.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity getById(Long id) {
        return userRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

}
