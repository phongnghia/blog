package com.resume.blog.service.category.implement;

import com.resume.blog.dto.category.CategoryDto;
import com.resume.blog.entity.jpa.CategoryEntity;
import com.resume.blog.mapper.BlogMapper;
import com.resume.blog.repository.jpa.CategoryRepository;
import com.resume.blog.service.category.ICategoryService;
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
public class CategoryServiceImpl implements ICategoryService {

    private final CategoryRepository m_categoryRepository;

    private final BlogMapper m_blogMapper;

    @Autowired
    protected CategoryServiceImpl(CategoryRepository categoryRepository, BlogMapper mapper) {
        this.m_categoryRepository = categoryRepository;
        this.m_blogMapper = mapper;
    }

    @Override
    public List<CategoryDto> listCategories() {
        return m_categoryRepository.findAll().stream().map(m_blogMapper::categoryEntityToDto).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public CategoryDto addCategory(CategoryDto categoryDto) {
        CategoryEntity categoryEntity = m_blogMapper.categoryDtoToEntity(categoryDto);
        try {
            UUID id = Utils.generateRandomId();
            categoryEntity.setId(id);
            categoryDto.setId(id);
            m_categoryRepository.save(categoryEntity);
            return categoryDto;
        } catch (Exception ex) {
            throw new CustomException("An error occurred while adding the Category");
        }
    }

    @Override
    public CategoryDto findCategoryById(UUID id) {
        CategoryDto categoryDto = m_blogMapper.categoryEntityToDto(m_categoryRepository.findCategoryById(id));
        return categoryDto;
    }

    @Override
    @Transactional
    public void deleteCategory(UUID id) {
        try {
            CategoryEntity categoryEntity = m_categoryRepository.findCategoryById(id);
            if (categoryEntity == null) {
                throw new EntityNotFoundException(String.format("No category found with ID %s", id.toString()));
            }
            m_categoryRepository.deleteById(id);
        } catch (DataIntegrityViolationException dataIntegrityViolationException) {
            throw new CustomException("Cannot delete User due to foreign key constraint", dataIntegrityViolationException);
        } catch (Exception ex) {
            throw new CustomException("An error occurred while deleting the Category", ex);
        }
    }
}
