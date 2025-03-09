package com.resume.blog.repository.es;

import com.resume.blog.entity.jpa.PostEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.UUID;

public interface PostESRepository extends ElasticsearchRepository<PostEntity, UUID> {
}