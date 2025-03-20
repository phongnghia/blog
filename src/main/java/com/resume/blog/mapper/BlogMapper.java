package com.resume.blog.mapper;

import com.resume.blog.dto.category.CategoryDto;
import com.resume.blog.dto.category.CategoryQueryRequest;
import com.resume.blog.dto.tag.TagDto;
import com.resume.blog.dto.tag.TagQueryRequest;
import com.resume.blog.dto.user.UserDto;
import com.resume.blog.dto.user.UserESDto;
import com.resume.blog.dto.user.UserQueryRequest;
import com.resume.blog.entity.es.UserESEntity;
import com.resume.blog.entity.jpa.CategoryEntity;
import com.resume.blog.entity.jpa.TagEntity;
import com.resume.blog.entity.jpa.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper( componentModel = "spring" )
public interface BlogMapper {

    // Initializing instance
    BlogMapper INSTANCE = Mappers.getMapper(BlogMapper.class);

    // User mapper
    UserEntity userToEntity(UserDto userDto);

    UserDto userToDto(UserEntity userEntity);

    UserDto userRequestQueryToDto(UserQueryRequest userRequestQuery);

    UserESEntity userESToEntity(UserESDto userESDto);

    UserESDto userESToDto(UserESEntity userESEntity);

    UserESEntity userDtoToESEntity(UserDto userDto);

    // Tag mapper
    TagEntity tagDtoToEntity(TagDto tagDto);

    TagDto tagEntityToDto(TagEntity tagEntity);

    TagDto tagQueryRequestToDto(TagQueryRequest tagQueryRequest);

    // Category
    CategoryEntity categoryDtoToEntity(CategoryDto categoryDto);

    CategoryDto categoryEntityToDto(CategoryEntity categoryEntity);

    CategoryDto categoryQueryRequestToDto(CategoryQueryRequest categoryQueryRequest);

}
