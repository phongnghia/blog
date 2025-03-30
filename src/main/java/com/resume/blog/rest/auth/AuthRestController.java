package com.resume.blog.rest.auth;

import com.resume.blog.dto.user.UserDto;
import com.resume.blog.dto.user.UserQueryRequest;
import com.resume.blog.mapper.BlogMapper;
import com.resume.blog.service.user.IUserService;
import com.resume.blog.utils.ApiResponse;
import com.resume.blog.utils.CustomException;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping( value = "/api/auth" )
public class AuthRestController {

    private final IUserService m_userService;

    private final BlogMapper m_blogMapper;

    @Autowired
    public AuthRestController(IUserService userService, BlogMapper blogMapper) {
        this.m_userService = userService;
        this.m_blogMapper = blogMapper;
    }

    @PostMapping( value = "register" )
    public ResponseEntity<?> addUser(@RequestBody UserQueryRequest userQueryRequest){
        String message;

        try {
            if (StringUtils.isEmpty(userQueryRequest.getUsername()) || StringUtils.isEmpty(userQueryRequest.getPasswordHash())) {
                message = "Username or password cannot be empty. These fields are required";
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body(new ApiResponse<>(message));
            }

            if (m_userService.findUserByUsername(userQueryRequest.getUsername()) == null) {
                userQueryRequest.setActive(true);

                UserDto userDto = m_userService.addUser(m_blogMapper.userRequestQueryToDto(userQueryRequest));
                message = "User account has been successfully created";
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


}
