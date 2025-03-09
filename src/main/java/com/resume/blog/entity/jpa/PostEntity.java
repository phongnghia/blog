package com.resume.blog.entity.jpa;

import com.resume.blog.entity.base.BasePostEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table ( name = "post" )
public class PostEntity extends BasePostEntity {

    @ManyToOne
    @JoinColumn(name = "authorId")
    private UserEntity author;


    @ManyToMany ( mappedBy = "posts" )
    private List<TagEntity> tags;


    @ManyToMany( mappedBy = "posts" )
    private List<CategoryEntity> categories;

}
