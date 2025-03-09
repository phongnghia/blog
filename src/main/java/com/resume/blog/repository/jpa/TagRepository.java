package com.resume.blog.repository.jpa;

import com.resume.blog.entity.jpa.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TagRepository extends JpaRepository<TagEntity, UUID> {
}