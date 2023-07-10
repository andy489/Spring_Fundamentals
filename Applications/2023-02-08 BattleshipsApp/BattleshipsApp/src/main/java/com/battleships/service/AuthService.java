package com.battleships.service;

import com.battleships.domain.dto.UserRegisterDto;
import com.battleships.mapper.MapStructMapper;
import com.battleships.domain.dto.UserLoginDto;
import com.battleships.domain.entity.UserEntity;
import com.battleships.session.CurrentUser;
import com.battleships.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final MapStructMapper mapper;
    private final CurrentUser currentUser;
    private final PasswordEncoder encoder;

    @Autowired
    public AuthService(
            UserRepository userRepository,
            MapStructMapper mapper,
            CurrentUser currentUser,
            PasswordEncoder encoder
    ) {
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.currentUser = currentUser;
        this.encoder = encoder;
    }

    public void registerAndLogin(UserRegisterDto userRegisterDto) {
        UserEntity newUser = mapper.toEntity(userRegisterDto)
                .setPassword(encoder.encode(userRegisterDto.getPassword()));

        userRepository.save(newUser);

        login(newUser);
    }

    public void login(UserLoginDto userLoginDto) {
        UserEntity existingUser = userRepository.findByUsername(userLoginDto.getUsername())
                .orElseThrow(NoSuchFieldError::new);
        login(existingUser);
    }

    private void login(UserEntity userEntity) {
        currentUser.setLoggedIn(true)
                .setId(userEntity.getId())
                .setUsername(userEntity.getUsername())
                .setEmail(userEntity.getEmail());
    }

    public void logout() {
        currentUser.clear();
    }

    public Optional<UserEntity> getByUsername(String uniqueFieldValue) {
        return userRepository.findByUsername(uniqueFieldValue);
    }

    public Optional<UserEntity> getByEmail(String email) {
        return userRepository.findByEmail(email);
    }

}
