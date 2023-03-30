package com.elmaddin.TaskForWork.config;

import com.elmaddin.TaskForWork.model.Role;
import com.elmaddin.TaskForWork.model.UserEntity;
import com.elmaddin.TaskForWork.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class StartStopConfig implements CommandLineRunner {

    private final UserService userService;

    public StartStopConfig(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
    userService.createUser(UserEntity.builder().username("elmaddin").role(Role.ADMIN).password("123").build());
    }
}
