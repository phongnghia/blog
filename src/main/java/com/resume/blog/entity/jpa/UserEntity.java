package com.resume.blog.entity.jpa;

import com.resume.blog.entity.base.BaseUserEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table( name = "user")
public class UserEntity extends BaseUserEntity {

    @Column(name = "id", nullable = false)
    private UUID id;

    @Column ( nullable = false )
    private String passwordHash;

    @Column ( nullable = false )
    protected String username;

    public UUID getId(){
        return id;
    }

    public void setId(UUID id){
        this.id = (this.id == null) ? UUID.randomUUID() : id;
    }

}
