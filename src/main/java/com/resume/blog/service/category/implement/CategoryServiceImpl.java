package com.resume.blog.service.category.implement;

import com.resume.blog.dto.category.CategoryDto;
import com.resume.blog.entity.jpa.CategoryEntity;
import com.resume.blog.mapper.BlogMapper;
import com.resume.blog.mapper.UpdateCategoryMapper;
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
import java.util.Optional;
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
    public List<Optional<CategoryDto>> listCategories() {
        return m_categoryRepository
                .findAll()
                .stream()
                .map(category -> Optional.ofNullable(m_blogMapper.categoryEntityToDto(category)))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void addCategory(CategoryDto categoryDto) {
        CategoryEntity categoryEntity = m_blogMapper.categoryDtoToEntity(categoryDto);
        try {
            UUID id = Utils.generateRandomId();
            categoryEntity.setId(id);
            categoryDto.setId(id);
            m_categoryRepository.save(categoryEntity);
        } catch (Exception ex) {
            throw new CustomException("An error occurred while adding the Category");
        }
    }

    @Override
    public Optional<CategoryDto> findCategoryById(UUID id) {
        return Optional.ofNullable(m_blogMapper.categoryEntityToDto(m_categoryRepository.findCategoryById(id)));
    }

    @Override
    @Transactional
    public Optional<CategoryDto> updateCategory(UUID id, CategoryDto categoryDto) {

        try {
            CategoryEntity category = m_categoryRepository.findCategoryById(id);
            if (category == null) {
                throw new EntityNotFoundException(String.format("No category found with ID %s", id.toString()));
            }

            UpdateCategoryMapper.updateCategoryDtoToEntity(category, categoryDto);

            m_categoryRepository.save(category);

            return Optional.ofNullable(m_blogMapper.categoryEntityToDto(category));

        } catch (Exception ex) {
            throw new CustomException("An error occurred while updating the Category");
        }
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
