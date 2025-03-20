package com.resume.blog.service.user;

import com.resume.blog.dto.user.UserDto;

public interface IAuthentication {

    UserDto registryUser(UserDto userDto);

    UserDto login(String username, String passwordHash);
}
