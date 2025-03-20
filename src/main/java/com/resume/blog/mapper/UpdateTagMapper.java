package com.resume.blog.mapper;

import com.resume.blog.dto.tag.TagDto;
import com.resume.blog.entity.jpa.TagEntity;

public class UpdateTagMapper {

    public static TagEntity updateTagDtoToEntity(TagEntity tag, TagDto tagDto){
        if (tagDto == null) {
            return null;
        }

        tag.setTitle(tagDto.getTitle());
        tag.setContent(tagDto.getContent());
        tag.setMetaTitle(tagDto.getMetaTitle());

        return tag;
    }

}
