package com.resume.blog.utils;

import lombok.Getter;
import lombok.Setter;
import lombok.Builder;

@Getter
@Setter
@Builder(toBuilder = true)
public class ApiResponse<T> {

    private T m_data;

    private String m_message;

    public ApiResponse(T data, String message) {
        this.m_data = data;
        this.m_message = message;
    }

    public ApiResponse(String message) {
        this.m_message = message;
    }

    public ApiResponse(T data) {
        this.m_data = data;
    }

}
