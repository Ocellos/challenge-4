package com.example.challenge4app.service;

import com.example.challenge4app.model.User;

public interface UserService {

    User getUserByUsername(String username);
    void addUser(User user);
    void updateUser(Long userId, User user);
    void deleteUser(Long userId);
}
