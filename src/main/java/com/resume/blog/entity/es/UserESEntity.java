package com.resume.blog.entity.es;

import com.resume.blog.entity.base.BaseUserEntity;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;


@Data
@Document(indexName = "user")
public class UserESEntity extends BaseUserEntity {
    private boolean isActive;
}
