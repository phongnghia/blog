package com.resume.blog.repository.jpa;

import com.resume.blog.entity.jpa.RoleEntity;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RoleRepository {
    RoleEntity findRoleById(UUID id);
}
