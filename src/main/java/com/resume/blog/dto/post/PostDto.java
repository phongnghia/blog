package com.resume.blog.dto.post;

import com.resume.blog.dto.category.CategoryDto;
import com.resume.blog.dto.tag.TagDto;
import com.resume.blog.dto.user.UserDto;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
public class PostDto {

    private UUID id;

    private String title;

    private String metaTitle;

    private String summary;

    private String content;

    private Date createDate;

    private Date updateDate;

    private Boolean published;

    private UserDto author;
    
    private List<TagDto> tags;
    
    private List<CategoryDto> categories;

}
