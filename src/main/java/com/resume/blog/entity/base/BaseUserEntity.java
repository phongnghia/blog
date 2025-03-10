package com.resume.blog.entity.base;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public abstract class BaseUserEntity extends BaseEntity {

    @Column
    protected String username;

    @Column
    protected String firstName;

    @Column
    protected String lastName;

    @Column
    protected String email;

    @Column
    protected String mobile;

    @Column (length = 512)
    protected String profile;

    @Column (length = 512)
    protected String intro;

}
