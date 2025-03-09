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
}
