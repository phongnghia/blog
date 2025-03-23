package com.resume.blog.entity.jpa;

import com.resume.blog.entity.base.BaseEntity;
import com.resume.blog.utils.ERole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table( name = "role" )
public class RoleEntity extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column( length = 20 )
    private ERole name;

    public RoleEntity(ERole name) {
        this.name = name;
    }

}
