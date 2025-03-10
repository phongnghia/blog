package com.resume.blog.entity.es;

import com.resume.blog.entity.base.BasePostEntity;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@Document( indexName = "post" )
public class PostESEntity extends BasePostEntity {

}
