package com.resume.blog.service.user;

import com.resume.blog.dto.user.UserDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IUserService {

    List<Optional<UserDto>> listUser();

    Optional<UserDto> addUser(UserDto userDto);

    Optional<UserDto> findUserByUsername(String username);

    Optional<UserDto> findUserById(UUID id);

    Optional<UserDto> updateUser(UUID id, UserDto userDto);

    void deleteUserById(UUID id);

}
