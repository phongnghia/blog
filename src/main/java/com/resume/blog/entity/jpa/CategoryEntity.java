package com.resume.blog.entity.jpa;

import com.resume.blog.entity.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.JoinTable;
import lombok.Data;

import java.util.List;

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
