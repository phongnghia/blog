package com.resume.blog.entity.jpa;

import com.resume.blog.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table ( name = "category" )
public class CategoryEntity extends BaseEntity {

    @Column
    private String title;

    @Column ( length = 512 )
    private String metaTitle;

    @Column ( length = 512 )
    private String content;

    @ManyToMany
    @JoinTable(
            name = "post_category",
            joinColumns = @JoinColumn( name = "categoryId" ),
            inverseJoinColumns = @JoinColumn( name = "postId" )
    )
    private List<PostEntity> posts;

}
