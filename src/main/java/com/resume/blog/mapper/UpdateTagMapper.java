package com.resume.blog.mapper;

import com.resume.blog.dto.tag.TagDto;
import com.resume.blog.entity.jpa.TagEntity;

import java.util.Optional;

public class UpdateTagMapper {

    public static TagEntity updateTagDtoToEntity(TagEntity tag, TagDto tagDto){
        if (tagDto == null) {
            return null;
        }

        Optional.ofNullable(tagDto.getContent()).ifPresent(tag::setContent);
        Optional.ofNullable(tagDto.getTitle()).ifPresent(tag::setTitle);
        Optional.ofNullable(tagDto.getMetaTitle()).ifPresent(tag::setMetaTitle);

        return tag;
    }

}
