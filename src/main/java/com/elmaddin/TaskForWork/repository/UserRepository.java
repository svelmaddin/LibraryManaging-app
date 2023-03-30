package com.elmaddin.TaskForWork.repository;


import com.elmaddin.TaskForWork.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity,Long> {
    Optional<UserEntity> findUserEntitiesByUsername(String username);
}
