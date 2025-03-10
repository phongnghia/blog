package com.resume.blog.entity.jpa;

import com.resume.blog.entity.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table( name = "tag" )
public class TagEntity extends BaseEntity {

    @Column
    private String title;

    @Column ( length = 512 )
    private String metaTitle;

    @Column ( length = 512 )
    private String content;


    @ManyToMany
    @JoinTable(
            name = "post_tag",
            joinColumns = @JoinColumn( name = "tagId"),
            inverseJoinColumns = @JoinColumn( name = "postId" )
    )
    private List<PostEntity> posts;

}
