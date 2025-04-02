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

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping( value = "/api/category")
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
                .body(ApiResponse
                        .builder()
                        .m_message("List of category")
                        .m_data(m_categoryService.listCategories())
                        .build()
                );
    }

    @PostMapping( value = "add" )
    public ResponseEntity<?> addCategory(@RequestBody CategoryQueryRequest categoryQueryRequest) {
        try {
            CategoryDto categoryDto = m_blogMapper.categoryQueryRequestToDto(categoryQueryRequest);
            m_categoryService.addCategory(categoryDto);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(ApiResponse
                            .builder()
                            .m_message("Added successful")
                            .m_data(categoryDto)
                            .build()
                    );
        } catch (CustomException ex) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse
                            .builder()
                            .m_message(ex.getMessage())
                            .build()
                    );
        }
    }

    @PutMapping( value = "update/{id}" )
    public ResponseEntity<?> updateCategory(@PathVariable UUID id, @RequestBody CategoryQueryRequest categoryQueryRequest)  {
        try {
            Optional<CategoryDto> categoryDto = m_categoryService.updateCategory(id, m_blogMapper.categoryQueryRequestToDto(categoryQueryRequest));
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(ApiResponse
                            .builder()
                            .m_data(categoryDto)
                            .build()
                    );
        } catch (CustomException ex) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse
                            .builder()
                            .m_message(ex.getMessage())
                            .build()
                    );
        }
    }

    @DeleteMapping( value = "delete/{id}" )
    public ResponseEntity<?> deleteCategory(@PathVariable UUID id) {
        try {
            m_categoryService.deleteCategory(id);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(ApiResponse
                            .builder()
                            .m_message(String.format("deletion successful for the specified ID: %s", id.toString()))
                            .build()
                    );
        } catch (CustomException ex) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse
                            .builder()
                            .m_message(ex.getMessage())
                            .build()
                    );
        }
    }

}
