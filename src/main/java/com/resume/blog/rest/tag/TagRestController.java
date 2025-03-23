package com.resume.blog.rest.tag;

import com.resume.blog.dto.tag.TagDto;
import com.resume.blog.dto.tag.TagQueryRequest;
import com.resume.blog.mapper.BlogMapper;
import com.resume.blog.service.tag.ITagService;
import com.resume.blog.utils.ApiResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.UUID;

@RestController
@RequestMapping( value = "/rest/tag" )
public class TagRestController {

    private final ITagService m_tagService;

    private final BlogMapper m_blogMapper;

    @Autowired
    protected TagRestController(ITagService tagService, BlogMapper mapper){
        this.m_tagService = tagService;
        this.m_blogMapper = mapper;
    }

    @GetMapping( value = "list" )
    public ResponseEntity<?> listTags() {
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ApiResponse<>(m_tagService.listTags()));
        } catch (Exception ex) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(ex.getMessage()));
        }
    }

    @PostMapping( value = "add" )
    public ResponseEntity<?> addTag(@RequestBody TagQueryRequest tagQueryRequest) {
        TagDto tagDto = m_blogMapper.tagQueryRequestToDto(tagQueryRequest);
        if (StringUtils.isEmpty(tagDto.getTitle())) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse<>(String.format("Title cannot be empty. This field is required")));
        }

        m_tagService.addTag(tagDto);

        return ResponseEntity
                .status(HttpStatus.OK).body(new ApiResponse<>(tagDto, String.format("Added successful")));
    }

    @GetMapping ( value = "get/{id}" )
    public ResponseEntity<?> getTagById(@PathVariable UUID id) {
        try {
            TagDto tagDto = m_tagService.findTagById(id);

            if (tagDto == null) {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponse<>(String.format("No tag found with ID %s", id.toString())));
            }
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ApiResponse<>(tagDto, String.format("Success! Tag with ID %s has been found", id.toString())));
        } catch (Exception ex) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(ex.getMessage()));
        }
    }

    @DeleteMapping( value = "delete/{id}" )
    public ResponseEntity<?> deleteTagById(@PathVariable UUID id) {
        try {
            m_tagService.deleteTag(id);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ApiResponse<>(String.format("User deletion successful for the specified ID: %s", id.toString())));
        } catch (Exception ex) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(ex.getMessage()));
        }
    }

}
