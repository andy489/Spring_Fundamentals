package com.gira.service;

import com.gira.mapper.MapStructMapper;
import com.gira.model.dto.UserLoginDto;
import com.gira.model.dto.UserRegisterDto;
import com.gira.model.entity.UserEntity;
import com.gira.repository.UserRepository;
import com.gira.session.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    public UserService(
            UserRepository userRepository,
            CurrentUser currentUser,
            MapStructMapper mapper,
            PasswordEncoder encoder
    ) {
        this.userRepository = userRepository;
        this.currentUser = currentUser;
        this.mapper = mapper;
        this.encoder = encoder;
    }

    public Optional<UserEntity> getById(Long id) {

        return userRepository.findById(id);
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

    private void login(UserEntity userEntity) {
        currentUser.setLoggedIn(true)
                .setId(userEntity.getId())
                .setUsername(userEntity.getUsername())
                .setEmail(userEntity.getEmail());
    }

    public void login(UserLoginDto userLoginDto) {
        UserEntity existingUser = userRepository.findByEmail(userLoginDto.getEmail())
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
