package com.resume.blog.mapper;

import com.resume.blog.dto.category.CategoryDto;
import com.resume.blog.entity.jpa.CategoryEntity;

import java.util.Optional;

public class UpdateCategoryMapper {

    public static CategoryEntity updateCategoryDtoToEntity(CategoryEntity category, CategoryDto categoryDto){
        if (categoryDto == null) {
            return null;
        }

        Optional.ofNullable(categoryDto.getContent()).ifPresent(category::setContent);
        Optional.ofNullable(categoryDto.getTitle()).ifPresent(category::setTitle);
        Optional.ofNullable(categoryDto.getMetaTitle()).ifPresent(category::setMetaTitle);

        return category;
    }

}

