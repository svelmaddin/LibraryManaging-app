package com.elmaddin.TaskForWork.repository;


import com.elmaddin.TaskForWork.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity,Long> {
}
