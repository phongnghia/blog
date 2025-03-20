package com.resume.blog.service.tag;

import com.resume.blog.dto.tag.TagDto;

import java.util.List;
import java.util.UUID;

public interface ITagService {

    TagDto addTag(TagDto tagDto);

    TagDto findTagById(UUID id);

    List<TagDto> listTags();

    TagDto updateTag(UUID id, TagDto tagDto);

    void deleteTag(UUID id);

}
