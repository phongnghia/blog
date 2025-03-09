package com.resume.blog.entity.es;

import com.resume.blog.entity.base.BaseUserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.UUID;


@Data
@Document(indexName = "user")
public class UserESEntity extends BaseUserEntity {

    private String lastModify;

    public UUID getId(){
        return id;
    }

    public void setId(UUID id){
        this.id = (this.id == null) ? UUID.randomUUID() : id;
    }

}
