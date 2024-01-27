package org.practice.repository;

import org.practice.model.User;

import java.util.List;

public interface UserRepository {

    List<User> findAll();

}
