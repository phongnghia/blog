package com.resume.blog.dto.tag;

import com.resume.blog.dto.post.PostDto;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class TagDto {

    private UUID id;

    private String title;

    private String metaTitle;

    private String content;

    private List<PostDto> posts;

}
