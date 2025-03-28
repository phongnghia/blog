package com.resume.blog.rest.category;

import com.resume.blog.dto.category.CategoryDto;
import com.resume.blog.dto.category.CategoryQueryRequest;
import com.resume.blog.mapper.BlogMapper;
import com.resume.blog.service.category.ICategoryService;
import com.resume.blog.utils.ApiResponse;
import com.resume.blog.utils.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

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
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse<>(m_categoryService.listCategories()));
    }

    @PostMapping( value = "add" )
    public ResponseEntity<?> addCategory(@RequestBody CategoryQueryRequest categoryQueryRequest) {
        try {
            CategoryDto categoryDto = m_blogMapper.categoryQueryRequestToDto(categoryQueryRequest);
            m_categoryService.addCategory(categoryDto);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ApiResponse<>(categoryDto, "Added successful"));
        } catch (CustomException ex) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(ex.getMessage()));
        }
    }

    @PutMapping( value = "update/{id}" )
    public ResponseEntity<?> updateCategory(@PathVariable UUID id, @RequestBody CategoryQueryRequest categoryQueryRequest)  {
        try {
            CategoryDto categoryDto = m_blogMapper.categoryQueryRequestToDto(categoryQueryRequest);
            categoryDto = m_categoryService.updateCategory(id, categoryDto);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ApiResponse<>(categoryDto));
        } catch (CustomException ex) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ex.getMessage());
        }
    }

    @DeleteMapping( value = "delete/{id}" )
    public ResponseEntity<?> deleteCategory(@PathVariable UUID id) {
        try {
            m_categoryService.deleteCategory(id);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ApiResponse<>(String.format("deletion successful for the specified ID: %s", id.toString())));
        } catch (CustomException ex) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(ex.getMessage()));
        }
    }

}
