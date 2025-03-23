package com.resume.blog.entity.base;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

    @Id
    @Column( name = "id", nullable = false )
    protected UUID id;

    @Column( name = "create_date_at", updatable = false )
    @CreatedDate
    @Field( type = FieldType.Date, format = DateFormat.date_hour_minute_second_millis)
    protected LocalDateTime createDateAt;

    @Column( name = "modified_date_at" )
    @LastModifiedDate
    @Field( type = FieldType.Date, format = DateFormat.date_hour_minute_second_millis)
    protected LocalDateTime modifiedDateAt;

}
