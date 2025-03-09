package com.resume.blog.repository.jpa;

import com.resume.blog.entity.jpa.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PostRepository extends JpaRepository<PostEntity, UUID> {
}