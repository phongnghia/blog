package com.resume.blog.dto.user;

import com.resume.blog.dto.post.PostDto;
import lombok.Data;

import java.util.List;

@Data
public class UserDto extends UserBaseDto{

    private String passwordHash;

    private boolean isActive;

    private List<PostDto> posts;

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

}
