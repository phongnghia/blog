package com.resume.blog.dto.category;

import com.resume.blog.dto.post.PostDto;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class CategoryDto {

    private UUID id;

    private String title;

    private String metaTitle;

    private String content;

    private List<PostDto> posts;

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
