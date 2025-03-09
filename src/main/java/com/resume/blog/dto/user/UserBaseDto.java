package com.resume.blog.dto.user;

import lombok.Data;

import java.util.UUID;

@Data
public abstract class UserBaseDto {

    protected UUID id;

    protected String username;

    protected String firstName;

    protected String lastName;

    protected String email;

    protected String mobile;

    protected String profile;

    protected String intro;

}
