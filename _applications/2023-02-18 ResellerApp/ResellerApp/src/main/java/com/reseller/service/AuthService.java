package com.reseller.service;

import com.reseller.mapper.MapStructMapper;
import com.reseller.model.dto.UserLoginDto;
import com.reseller.model.dto.UserRegisterDto;
import com.reseller.model.entity.UserEntity;
import com.reseller.repository.UserRepository;
import com.reseller.session.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final MapStructMapper mapper;
    private final PasswordEncoder encoder;
    private final CurrentUser currentUser;

    @Autowired
    public AuthService(
            UserRepository userRepository,
            MapStructMapper mapper,
            PasswordEncoder encoder,
            CurrentUser currentUser
    ) {
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.encoder = encoder;
        this.currentUser = currentUser;
    }

    public Optional<UserEntity> getByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<UserEntity> getByUsername(String userName) {
        return userRepository.findByUsername(userName);
    }

    public void registerAndLogin(UserRegisterDto userRegisterDto) {
        UserEntity newUser = mapper.toEntity(userRegisterDto)
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
        UserEntity existingUser = userRepository.findByUsername(userLoginDto.getUsername()).orElseThrow(NoSuchFieldError::new);
        login(existingUser);
    }

    public void logout() {
        currentUser.clear();
    }

}