package com.resume.blog.dto.user;

import com.resume.blog.dto.post.PostDto;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class UserDto extends UserBaseDto{

    private String passwordHash;

    private List<PostDto> posts;

}
