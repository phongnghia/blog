package com.resume.blog.service.user;

import com.resume.blog.dto.user.UserDto;

import java.util.List;
import java.util.UUID;

public interface IUserService {

    List<UserDto> listUser();

    UserDto addUser(UserDto userDto);

    UserDto findUserByUsername(String username);

    UserDto findUserById(UUID id);

}
