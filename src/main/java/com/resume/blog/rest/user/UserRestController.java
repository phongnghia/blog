package com.resume.blog.rest.user;

import com.resume.blog.utils.ApiResponse;
import com.resume.blog.dto.user.UserDto;
import com.resume.blog.dto.user.UserQueryRequest;
import com.resume.blog.mapper.BlogMapper;
import com.resume.blog.service.user.IUserService;
import com.resume.blog.utils.CustomException;
import io.micrometer.common.util.StringUtils;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.UUID;

@RestController
@RequestMapping ( value = "/rest/user" )
public class UserRestController {

    private final IUserService m_userService;

    private final BlogMapper m_blogMapper;

    @Autowired
    public UserRestController(IUserService userService, BlogMapper blogMapper){
        this.m_userService = userService;
        this.m_blogMapper = blogMapper;
    }

    @GetMapping ( value = "list" )
    public ResponseEntity<?> listUser(){
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ApiResponse<>(m_userService.listUser()));
        } catch (Exception ex){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(ex.getMessage()));
        }
    }

    @PostMapping( value = "add" )
    public ResponseEntity<?> addUser(@RequestBody UserQueryRequest userQueryRequest){
        String message;

        try {
            if (StringUtils.isEmpty(userQueryRequest.getUsername()) || StringUtils.isEmpty(userQueryRequest.getPasswordHash())) {
                message = "Username or password cannot be empty. These fields are required";
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body(new ApiResponse<>(message));
            }

            if (!isUserExists(userQueryRequest, null)) {
                UserDto userDto = m_userService.addUser(m_blogMapper.userRequestQueryToDto(userQueryRequest));
                message = "Added successful";
                return ResponseEntity
                        .status(HttpStatus.OK)
                        .body(new ApiResponse<>(userDto, message));
            } else {
                message = String.format("The username %s already exists", userQueryRequest.getUsername());
                return ResponseEntity
                        .status(HttpStatus.CONFLICT)
                        .body(new ApiResponse<>(message));
            }
        } catch (CustomException ex) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(ex.getMessage()));
        }
    }

    @GetMapping ( value = "get/{id}" )
    public ResponseEntity<?> getUserById(@PathVariable UUID id) {
        UserDto userDto = m_userService.findUserById(id);
        String message;
        if (userDto == null) {
            message = String.format("No user found with ID %s", id.toString());
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(message));
        }
        message = String.format("Success! User with ID %s has been found", id.toString());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse<>(userDto, message));
    }

    @PutMapping ( value = "update/{id}" )
    public ResponseEntity<?> updateUser(@PathVariable UUID id, @RequestBody UserQueryRequest userQueryRequest){
        String message;
        try {
            if (!isUserExists(null, id)) {
                message = String.format("No user found with ID %s", id.toString());
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponse<>(message));
            }
            UserDto userDto = m_userService.updateUser(id, m_blogMapper.userRequestQueryToDto(userQueryRequest));

            message = String.format("Success! The user with ID %s has been successfully updated", id.toString());

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ApiResponse<>(userDto, message));
        } catch (EntityNotFoundException ex) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(ex.getMessage()));
        }
    }

    @DeleteMapping ( value = "delete/{id}" )
    public ResponseEntity<?> deleteUser(@PathVariable UUID id) {
        String message;
        try {
            if (!isUserExists(null, id)) {
                message = String.format("No user found with ID %s", id.toString());
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponse<>(message));
            }
            m_userService.deleteUserById(id);
            message = String.format("User deletion successful for the specified ID: %s", id.toString());
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ApiResponse<>(message));
        } catch (CustomException ex) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ex.getMessage());
        }
    }

    private Boolean isUserExists(UserQueryRequest userQueryRequest, UUID id) {
        if (userQueryRequest != null && !StringUtils.isEmpty(userQueryRequest.getUsername())) {
            return m_userService.findUserByUsername(userQueryRequest.getUsername()) != null;
        }
        if (id != null) {
            return m_userService.findUserById(id) != null;
        }
        return false;
    }

}
