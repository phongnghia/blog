package com.resume.blog.service.user;

import com.resume.blog.dto.user.UserDto;

import java.util.Optional;

public interface IAuthentication {

    Optional<UserDto> registryUser(UserDto userDto);

    Optional<UserDto> login(String username, String passwordHash);
}
