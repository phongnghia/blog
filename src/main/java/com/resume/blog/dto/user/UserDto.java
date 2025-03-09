package com.resume.blog.dto.user;

import lombok.Data;

@Data
public class UserDto extends UserBaseDto{
    private String passwordHash;
}
