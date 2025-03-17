package com.resume.blog.mapper;

import com.resume.blog.dto.user.UserDto;
import com.resume.blog.entity.es.UserESEntity;
import com.resume.blog.entity.jpa.UserEntity;

public class UpdateUserMapper{

    public static UserEntity updateUserDtoToEntity(UserEntity user, UserDto userDto){
        if (userDto == null) {
            return null;
        }

        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setMobile(userDto.getMobile());
        user.setProfile(userDto.getProfile());
        user.setIntro(userDto.getIntro());

        return user;
    }

    public static UserESEntity updateUserDtoToESEntity(UserESEntity userESEntity, UserDto userDto){
        if (userDto == null){
            return null;
        }

        userESEntity.setFirstName(userDto.getFirstName());
        userESEntity.setLastName(userDto.getLastName());
        userESEntity.setEmail(userDto.getEmail());
        userESEntity.setMobile(userDto.getMobile());
        userESEntity.setProfile(userDto.getProfile());
        userESEntity.setIntro(userDto.getIntro());

        return userESEntity;
    }
}
