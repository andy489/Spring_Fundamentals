package com.example.bonapetit.service;

import com.example.bonapetit.mapper.MapStructMapper;
import com.example.bonapetit.model.dto.UserLoginDto;
import com.example.bonapetit.model.dto.UserRegisterDto;
import com.example.bonapetit.model.entity.UserEntity;
import com.example.bonapetit.repo.UserRepository;
import com.example.bonapetit.session.CurrentUser;
import jakarta.validation.Valid;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final CurrentUser currentUser;

    private final MapStructMapper mapper;

    private final PasswordEncoder encoder;


    public UserService(UserRepository userRepository,
                       CurrentUser currentUser,
                       MapStructMapper mapper,
                       PasswordEncoder encoder) {

        this.userRepository = userRepository;
        this.currentUser = currentUser;
        this.mapper = mapper;
        this.encoder = encoder;
    }

    public Optional<UserEntity> getByUsername(String username) {

        return userRepository.findByUsername(username);
    }

    public Optional<UserEntity> getByEmail(String email) {

        return userRepository.findByEmail(email);
    }

    public void registerAndLogin(UserRegisterDto userRegisterDto) {

        UserEntity newUser = mapper.toUserEntity(userRegisterDto)
                .setPassword(encoder.encode(userRegisterDto.getPassword()));

        userRepository.save(newUser);

        login(newUser);
    }

    public void login(UserEntity userEntity) {
        currentUser.setLoggedIn(true)
                .setId(userEntity.getId())
                .setUsername(userEntity.getUsername())
                .setEmail(userEntity.getEmail());
    }

    public void login(UserLoginDto userLoginDto) {
        UserEntity existingUser = userRepository.findByUsername(userLoginDto.getUsername())
                .orElseThrow(NoSuchElementException::new);

        login(existingUser);
    }

    public void logout() {
        currentUser.clear();
    }

    public UserEntity getCurrentUserEntity() {
        return userRepository.findById(currentUser.getId()).orElseThrow(NoSuchElementException::new);
    }
}
