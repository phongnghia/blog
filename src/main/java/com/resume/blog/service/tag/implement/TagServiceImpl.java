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

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
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
    public void addTag(TagDto tagDto) {
        TagEntity tagEntity = m_blogMapper.tagDtoToEntity(tagDto);
        try {
            UUID id = Utils.generateRandomId();
            tagEntity.setId(id);
            tagDto.setId(id);
            m_tagRepository.save(tagEntity);
        } catch (Exception ex) {
            throw new CustomException("An error occurred while adding the Category");
        }
    }

    @Override
    public Optional<TagDto> findTagById(UUID id) {
        TagDto tagDto = m_blogMapper.tagEntityToDto(m_tagRepository.findTagById(id));
        if (tagDto == null){
            throw new EntityNotFoundException(String.format("No tag found with ID %s", id.toString()));
        }
        return Optional.of(tagDto);
    }

    @Override
    public List<Optional<TagDto>> listTags() {
        return m_tagRepository
                .findAll()
                .stream()
                .map(tag -> Optional.ofNullable(m_blogMapper.tagEntityToDto(tag)))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Optional<TagDto> updateTag(UUID id, TagDto tagDto) {
        TagEntity tagEntity = m_tagRepository.findTagById(id);
        if (tagEntity == null){
            throw new EntityNotFoundException(String.format("No tag found with ID %s", id.toString()));
        }
        UpdateTagMapper.updateTagDtoToEntity(tagEntity, tagDto);
        m_tagRepository.save(tagEntity);

        tagDto = m_blogMapper.tagEntityToDto(tagEntity);
        return Optional.ofNullable(tagDto);
    }

    @Override
    @Transactional
    public void deleteTag(UUID id) {
        try {
            if (findTagById(id).isEmpty()) {
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
