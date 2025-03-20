package com.resume.blog.service.category;

import com.resume.blog.dto.category.CategoryDto;

import java.util.List;
import java.util.UUID;

public interface ICategoryService {

    List<CategoryDto> listCategories();

    CategoryDto addCategory(CategoryDto categoryDto);

    CategoryDto findCategoryById(UUID id);

    void deleteCategory(UUID id);

}
