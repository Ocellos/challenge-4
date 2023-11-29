package com.example.challenge4app.service.impl;

import com.example.challenge4app.model.User;
import com.example.challenge4app.repository.UserRepository;
import com.example.challenge4app.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void addUser(User user) {
        userRepository.save(user);
        LOGGER.info("Added a new user: {}", user.getUsername());
    }

    @Override
    public void updateUser(Long userId, User user) {
        User existingUser = userRepository.findById(userId).orElse(null);
        if (existingUser != null) {
            existingUser.setUsername(user.getUsername());
            existingUser.setEmail(user.getEmail());
            existingUser.setPassword(user.getPassword());
            userRepository.save(existingUser);
            LOGGER.info("Updated user with ID {}: {}", userId, existingUser.getUsername());
        } else {
            LOGGER.error("User with ID {} not found. Update failed.", userId);
        }
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
        LOGGER.info("Deleted user with ID {}", userId);
    }
}
