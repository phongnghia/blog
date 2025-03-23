package com.resume.blog.mapper;

import com.resume.blog.dto.user.UserDto;
import com.resume.blog.entity.es.UserESEntity;
import com.resume.blog.entity.jpa.UserEntity;

import java.util.Optional;

public class UpdateUserMapper{

    public static UserEntity updateUserDtoToEntity(UserEntity user, UserDto userDto){
        if (userDto == null) {
            return null;
        }
        Optional.ofNullable(userDto.getFirstName()).ifPresent(user::setFirstName);
        Optional.ofNullable(userDto.getLastName()).ifPresent(user::setLastName);
        Optional.ofNullable(userDto.getEmail()).ifPresent(user::setEmail);
        Optional.ofNullable(userDto.getMobile()).ifPresent(user::setMobile);
        Optional.ofNullable(userDto.getProfile()).ifPresent(user::setProfile);
        Optional.ofNullable(userDto.getIntro()).ifPresent(user::setIntro);

        return user;
    }

    public static UserESEntity updateUserDtoToESEntity(UserESEntity user, UserDto userDto){
        if (userDto == null){
            return null;
        }

        Optional.ofNullable(userDto.getFirstName()).ifPresent(user::setFirstName);
        Optional.ofNullable(userDto.getLastName()).ifPresent(user::setLastName);
        Optional.ofNullable(userDto.getEmail()).ifPresent(user::setEmail);
        Optional.ofNullable(userDto.getMobile()).ifPresent(user::setMobile);
        Optional.ofNullable(userDto.getProfile()).ifPresent(user::setProfile);
        Optional.ofNullable(userDto.getIntro()).ifPresent(user::setIntro);

        return user;
    }
}
