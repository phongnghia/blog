package com.resume.blog.dto.user;

import com.resume.blog.dto.post.PostDto;
import lombok.Data;

import java.util.List;

@Data
public class UserDto extends UserBaseDto{

    private String passwordHash;

    private List<PostDto> posts;

}
