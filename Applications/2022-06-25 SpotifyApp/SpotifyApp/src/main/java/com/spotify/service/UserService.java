package com.spotify.service;

import com.spotify.mapper.MapStructMapper;
import com.spotify.model.dto.UserLoginDto;
import com.spotify.model.dto.UserRegisterDto;
import com.spotify.model.entity.SongEntity;
import com.spotify.model.entity.UserEntity;
import com.spotify.repository.UserRepository;
import com.spotify.session.CurrentUser;
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

    public Optional<UserEntity> getByEmail(String email) {

        return userRepository.findByEmail(email);
    }

    public Optional<UserEntity> getByUsername(String username) {

        return userRepository.findByUsername(username);
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

    public void addSongToUser(SongEntity songToAdd) {
        UserEntity currUser = getCurrentUserEntity();

        if (currUser.getPlaylist().stream().noneMatch(s -> s.getId().equals(songToAdd.getId()))) {
            currUser.addSongToPlaylist(songToAdd);
            userRepository.saveAndFlush(currUser);
        }
    }

    public void deleteAllSongs() {
        UserEntity user = getCurrentUserEntity();
        user.deleteAllSongFromPlaylist();
        userRepository.saveAndFlush(user);
    }

    public void removeSongFromUser(Long songId) {
        UserEntity user = getCurrentUserEntity();
        user.removeSongFromPlaylist(songId);
        userRepository.saveAndFlush(user);
    }

}
