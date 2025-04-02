package com.resume.blog.service.tag;

import com.resume.blog.dto.tag.TagDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ITagService {

    void addTag(TagDto tagDto);

    Optional<TagDto> findTagById(UUID id);

    List<Optional<TagDto>> listTags();

    Optional<TagDto> updateTag(UUID id, TagDto tagDto);

    void deleteTag(UUID id);

}
