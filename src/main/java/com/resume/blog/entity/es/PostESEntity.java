package com.resume.blog.entity.es;

import com.resume.blog.entity.base.BasePostEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.UUID;

@Data
@Document( indexName = "post" )
public class PostESEntity extends BasePostEntity {

}
