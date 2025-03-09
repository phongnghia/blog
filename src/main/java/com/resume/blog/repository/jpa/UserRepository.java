package com.resume.blog.repository.jpa;

import com.resume.blog.entity.jpa.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    UserEntity findUserByUsername(String username);

    UserEntity findUserById(UUID id);

}
