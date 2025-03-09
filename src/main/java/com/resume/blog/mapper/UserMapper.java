package com.resume.blog.mapper;

import com.resume.blog.dto.user.UserDto;
import com.resume.blog.dto.user.UserESDto;
import com.resume.blog.dto.user.UserQueryRequest;
import com.resume.blog.entity.es.UserESEntity;
import com.resume.blog.entity.jpa.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper( componentModel = "spring" )
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserEntity userToEntity(UserDto userDto);

    UserDto userToDto(UserEntity userEntity);

    UserDto userRequestQueryToDto(UserQueryRequest userRequestQuery);

    UserESEntity userESToEntity(UserESDto userESDto);

    UserESDto userESToDto(UserESEntity userESEntity);

}
