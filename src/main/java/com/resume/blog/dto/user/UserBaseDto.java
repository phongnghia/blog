package com.resume.blog.dto.user;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public abstract class UserBaseDto {

    protected UUID id;

    protected String username;

    protected String firstName;

    protected String lastName;

    protected String email;

    protected String mobile;

    protected String profile;

    protected String intro;

    protected LocalDateTime createDateAt;

    protected LocalDateTime modifiedDateAt;

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id){
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

}
