package com.resume.blog.repository.es;

import com.resume.blog.entity.es.UserESEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.UUID;

public interface UserESRepository extends ElasticsearchRepository<UserESEntity, UUID> {

}
