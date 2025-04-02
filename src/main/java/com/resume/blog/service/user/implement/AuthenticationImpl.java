package com.resume.blog.service.user.implement;

import com.resume.blog.dto.user.UserDto;
import com.resume.blog.service.user.IAuthentication;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationImpl implements IAuthentication {
    @Override
    public Optional<UserDto> registryUser(UserDto userDto) {
        return null;
    }

    @Override
    public Optional<UserDto> login(String username, String passwordHash) {
        return null;
    }
}
