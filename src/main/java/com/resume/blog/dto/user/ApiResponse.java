package com.resume.blog.dto.user;

public class ApiResponse<T> {

    private T m_data;

    private String m_message;

    public ApiResponse(T data, String message){
        this.m_data = data;
        this.m_message = message;
    }

    public String getM_message() {
        return m_message;
    }

    public void setM_message(String m_message) {
        this.m_message = m_message;
    }

    public T getM_data() {
        return m_data;
    }

    public void setM_data(T m_data) {
        this.m_data = m_data;
    }

}
