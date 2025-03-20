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

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMetaTitle() {
        return metaTitle;
    }

    public void setMetaTitle(String metaTitle) {
        this.metaTitle = metaTitle;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
