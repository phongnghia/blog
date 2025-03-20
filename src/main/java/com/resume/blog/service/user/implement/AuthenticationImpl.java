package com.resume.blog.service.user.implement;

import com.resume.blog.dto.user.UserDto;
import com.resume.blog.service.user.IAuthentication;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationImpl implements IAuthentication {
    @Override
    public UserDto registryUser(UserDto userDto) {
        return null;
    }

    @Override
    public UserDto login(String username, String passwordHash) {
        return null;
    }
}
