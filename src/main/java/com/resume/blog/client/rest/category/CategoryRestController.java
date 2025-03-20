package com.resume.blog.client.rest.category;

import com.resume.blog.dto.category.CategoryDto;
import com.resume.blog.dto.category.CategoryQueryRequest;
import com.resume.blog.mapper.BlogMapper;
import com.resume.blog.service.category.ICategoryService;
import com.resume.blog.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping( value = "/rest/category")
public class CategoryRestController {

    private final ICategoryService m_categoryService;

    private final BlogMapper m_blogMapper;

    @Autowired
    protected CategoryRestController(ICategoryService categoryService, BlogMapper mapper) {
        this.m_categoryService = categoryService;
        this.m_blogMapper = mapper;
    }

    @GetMapping( value = "list" )
    public ResponseEntity<?> listCategories() {
        return ResponseEntity.ok().body(m_categoryService.listCategories());
    }

    @PostMapping( value = "add" )
    public ResponseEntity<?> addCategory(@RequestBody CategoryQueryRequest categoryQueryRequest) {
        try {
            CategoryDto categoryDto = m_blogMapper.categoryQueryRequestToDto(categoryQueryRequest);
            m_categoryService.addCategory(categoryDto);
            return ResponseEntity.ok().body(new ApiResponse<>(categoryDto, String.format("Added successful")));
        } catch (Exception ex) {
            return ResponseEntity.status(500).body(ex.getMessage());
        }
    }

}
