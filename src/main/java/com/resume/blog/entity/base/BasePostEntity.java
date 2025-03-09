package com.resume.blog.entity.base;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.UUID;

@Data
@MappedSuperclass
public abstract class BasePostEntity extends BaseEntity {

    @Column
    protected String title;

    @Column
    protected String metaTitle;

    @Column ( length = 512 )
    protected String summary;

    @Column ( length = 512 )
    protected String content;

    @Column
    @DateTimeFormat
    protected Date createDate;

    @Column
    @DateTimeFormat
    protected Date updateDate;

    @Column
    protected Boolean published;

}
