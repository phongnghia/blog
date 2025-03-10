package com.resume.blog.client.rest.user;

import ch.qos.logback.core.util.StringUtil;
import com.resume.blog.utils.ApiResponse;
import com.resume.blog.dto.user.UserDto;
import com.resume.blog.dto.user.UserQueryRequest;
import com.resume.blog.mapper.UserMapper;
import com.resume.blog.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping ( value = "/rest/user" )
public class UserRestController {

    private IUserService m_userService;

    private UserMapper m_userMapper;

    @Autowired
    public UserRestController(IUserService userService, UserMapper userMapper){
        this.m_userService = userService;
        this.m_userMapper = userMapper;
    }

    @GetMapping ( value = "list" )
    public ResponseEntity listUser(){
        try {
            return ResponseEntity.ok(
                    m_userService.listUser()
            );
        } catch (Exception ex){
            return ResponseEntity.status(500).body("Server error");
        }
    }

    @PostMapping( value = "add" )
    public ResponseEntity<?> addUser(@RequestBody UserQueryRequest userQueryRequest){
        String message = null;

        if (StringUtil.isNullOrEmpty(userQueryRequest.getUsername()) || StringUtil.isNullOrEmpty(userQueryRequest.getPasswordHash())) {
            message = "Username or password cannot be empty. These fields are required";
            return ResponseEntity.status(400).body(message);
        }

        if (!isUserExists(userQueryRequest, null)) {
            UserDto userDto = m_userService.addUser(m_userMapper.userRequestQueryToDto(userQueryRequest));
            message = String.format("Added successful");
            return ResponseEntity.ok().body(new ApiResponse<>(userDto, message));
        } else {
            message = String.format("The username %s already exists", userQueryRequest.getUsername());
            return ResponseEntity.status(400).body(new ApiResponse<>(null, message));
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

    public Boolean isUserExists(UserQueryRequest userQueryRequest, UUID id) {
        if (!StringUtil.isNullOrEmpty(userQueryRequest.getUsername())) {
            return m_userService.findUserByUsername(userQueryRequest.getUsername()) != null;
        }
        if (id != null) {
            return m_userService.findUserById(id) != null;
        }
        return false;
    }

}
