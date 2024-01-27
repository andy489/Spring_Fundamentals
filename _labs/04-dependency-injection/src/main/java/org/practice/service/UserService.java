package org.practice.service;

import org.practice.model.User;

import java.util.Optional;

public interface UserService {

    Optional<User> findYoungestUser();
}
