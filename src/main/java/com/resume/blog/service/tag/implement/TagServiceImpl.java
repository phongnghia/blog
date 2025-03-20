package com.resume.blog.service.tag.implement;

import com.resume.blog.dto.tag.TagDto;
import com.resume.blog.entity.jpa.TagEntity;
import com.resume.blog.mapper.BlogMapper;
import com.resume.blog.mapper.UpdateTagMapper;
import com.resume.blog.repository.jpa.TagRepository;
import com.resume.blog.service.tag.ITagService;
import com.resume.blog.utils.CustomException;
import com.resume.blog.utils.Utils;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TagServiceImpl implements ITagService {

    private final BlogMapper m_blogMapper;

    private final TagRepository m_tagRepository;

    @Autowired
    public TagServiceImpl (BlogMapper blogMapper, TagRepository tagRepository) {
        this.m_blogMapper = blogMapper;
        this.m_tagRepository = tagRepository;
    }

    @Override
    @Transactional
    public TagDto addTag(TagDto tagDto) {
        TagEntity tagEntity = m_blogMapper.tagDtoToEntity(tagDto);
        try {
            UUID id = Utils.generateRandomId();
            tagEntity.setId(id);
            tagDto.setId(id);
            m_tagRepository.save(tagEntity);
            return tagDto;
        } catch (Exception ex) {
            throw new CustomException("An error occurred while adding the Category");
        }
    }

    @Override
    public TagDto findTagById(UUID id) {
        TagDto tagDto = m_blogMapper.tagEntityToDto(m_tagRepository.findTagById(id));
        if (tagDto == null){
            throw new EntityNotFoundException(String.format("No tag found with ID %s", id.toString()));
        }
        return tagDto;
    }

    @Override
    public List<TagDto> listTags() {
        return m_tagRepository.findAll().stream().map(m_blogMapper::tagEntityToDto).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public TagDto updateTag(UUID id, TagDto tagDto) {
        TagEntity tagEntity = m_tagRepository.findTagById(id);
        if (tagEntity == null){
            throw new EntityNotFoundException(String.format("No tag found with ID %s", id.toString()));
        }
        UpdateTagMapper.updateTagDtoToEntity(tagEntity, tagDto);
        m_tagRepository.save(tagEntity);

        tagDto = m_blogMapper.tagEntityToDto(tagEntity);
        return tagDto;
    }

    @Override
    @Transactional
    public void deleteTag(UUID id) {
        try {
            if (findTagById(id) == null) {
                throw new CustomException(String.format("Tag not found with ID %s", id.toString()));
            }
            m_tagRepository.deleteById(id);
        } catch (DataIntegrityViolationException dataIntegrityViolationException) {
            throw new DataIntegrityViolationException("Cannot delete tag due to foreign key constraint", dataIntegrityViolationException);
        } catch (Exception ex) {
            throw new CustomException("An error occurred while deleting the User", ex);
        }
    }
}
