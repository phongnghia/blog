package com.resume.blog.client.rest.user;

import ch.qos.logback.core.util.StringUtil;
import com.resume.blog.utils.ApiResponse;
import com.resume.blog.dto.user.UserDto;
import com.resume.blog.dto.user.UserQueryRequest;
import com.resume.blog.mapper.BlogMapper;
import com.resume.blog.service.user.IUserService;
import com.resume.blog.utils.CustomException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
            return ResponseEntity.ok(
                    m_userService.listUser()
            );
        } catch (Exception ex){
            return ResponseEntity.status(500).body(new ApiResponse<>(null, "Failed! Unable to retrieve user list"));
        }
    }

    @PostMapping( value = "add" )
    public ResponseEntity<?> addUser(@RequestBody UserQueryRequest userQueryRequest){
        String message = null;

        try {
            if (StringUtil.isNullOrEmpty(userQueryRequest.getUsername()) || StringUtil.isNullOrEmpty(userQueryRequest.getPasswordHash())) {
                message = "Username or password cannot be empty. These fields are required";
                return ResponseEntity.status(400).body(message);
            }

            if (!isUserExists(userQueryRequest, null)) {
                UserDto userDto = m_userService.addUser(m_blogMapper.userRequestQueryToDto(userQueryRequest));
                message = "Added successful";
                return ResponseEntity.ok().body(new ApiResponse<>(userDto, message));
            } else {
                message = String.format("The username %s already exists", userQueryRequest.getUsername());
                return ResponseEntity.status(400).body(new ApiResponse<>(null, message));
            }
        } catch (CustomException ex) {
            return ResponseEntity.status(500).body(ex);
        }
    }

    @GetMapping ( value = "get/{id}" )
    public ResponseEntity<?> getUserById(@PathVariable UUID id) {
        UserDto userDto = m_userService.findUserById(id);
        if (userDto == null) {
            return ResponseEntity.ok().body(new ApiResponse<>(null, String.format("No user found with ID %s", id.toString())));
        }
        return ResponseEntity.ok(new ApiResponse<>(userDto, String.format("Success! User with ID %s has been found", id.toString())));
    }

    @PostMapping ( value = "update/{id}" )
    public ResponseEntity<?> updateUser(@PathVariable UUID id, @RequestBody UserQueryRequest userQueryRequest){
        try {
            if (!isUserExists(null, id)) {
                String message = String.format("No user found with ID %s", id.toString());
                return ResponseEntity.status(404).body(new ApiResponse<>(null, message));
            }
            UserDto userDto = m_userService.updateUser(id, m_blogMapper.userRequestQueryToDto(userQueryRequest));
            return ResponseEntity.ok().body(new ApiResponse<>(userDto, String.format("Success! The user with ID %s has been successfully updated", id.toString())));
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.status(500).body(ex);
        }
    }

    @DeleteMapping ( value = "delete/{id}" )
    public ResponseEntity<?> deleteUser(@PathVariable UUID id) {
        try {
            if (!isUserExists(null, id)) {
                return ResponseEntity.status(404).body(new ApiResponse<>(null, String.format("No user found with ID %s", id.toString())));
            }
            m_userService.deleteUserById(id);
            return ResponseEntity.ok().body(new ApiResponse<>(null, String.format("User deletion successful for the specified ID: %s", id.toString())));
        } catch (CustomException ex) {
            return ResponseEntity.status(500).body(ex);
        }
    }

    private Boolean isUserExists(UserQueryRequest userQueryRequest, UUID id) {
        if (userQueryRequest != null && !StringUtil.isNullOrEmpty(userQueryRequest.getUsername())) {
            return m_userService.findUserByUsername(userQueryRequest.getUsername()) != null;
        }
        if (id != null) {
            return m_userService.findUserById(id) != null;
        }
        return false;
    }

}
