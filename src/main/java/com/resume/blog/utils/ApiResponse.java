package com.resume.blog.utils;

public class ApiResponse<T> {

    private T m_data;

    private String m_message;

    public ApiResponse(T data, String message){
        this.m_data = data;
        this.m_message = message;
    }

    public String getMessage() {
        return m_message;
    }

    public void setMessage(String m_message) {
        this.m_message = m_message;
    }

    public T getData() {
        return m_data;
    }

    public void setData(T m_data) {
        this.m_data = m_data;
    }

}
