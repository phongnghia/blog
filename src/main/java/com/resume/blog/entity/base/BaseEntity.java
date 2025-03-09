package com.resume.blog.entity.base;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

import java.util.UUID;

@Data
@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @Column( name = "id", nullable = false )
    protected UUID id;

}
