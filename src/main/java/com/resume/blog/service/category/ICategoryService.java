package com.resume.blog.service.category;

import com.resume.blog.dto.category.CategoryDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ICategoryService {

    List<Optional<CategoryDto>> listCategories();

    void addCategory(CategoryDto categoryDto);

    Optional<CategoryDto> findCategoryById(UUID id);

    Optional<CategoryDto> updateCategory(UUID id, CategoryDto categoryDto);

    void deleteCategory(UUID id);

}
