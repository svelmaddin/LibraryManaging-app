package com.elmaddin.TaskForWork.service;

import com.elmaddin.TaskForWork.dto.UserResponse;
import com.elmaddin.TaskForWork.exception.GenericException;
import com.elmaddin.TaskForWork.model.UserEntity;
import com.elmaddin.TaskForWork.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponse createUser(UserEntity user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        var userDb = userRepository.save(user);
        return UserResponse.builder()
                .username(userDb.getUsername())
                .role(userDb.getRole())
                .build();
    }

    public UserResponse getUser(String username) {
        var userDb = findUserByUsername(username);
        return UserResponse.builder()
                .username(userDb.getUsername())
                .role(userDb.getRole())
                .build();
    }


    public UserEntity findUserByUsername(String username) {
        return userRepository.findUserEntitiesByUsername(username).orElseThrow(
                () -> GenericException.builder()
                        .httpStatus(HttpStatus.NOT_FOUND)
                        .errorMessage("user not found by given id!")
                        .build());
    }
}
