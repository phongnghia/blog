package com.resume.blog.entity.jpa;

import com.resume.blog.entity.base.BaseUserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table( name = "user")
public class UserEntity extends BaseUserEntity {

    @Column ( nullable = false )
    private String passwordHash;

    @Column ( nullable = false )
    private String username;

    @OneToMany ( mappedBy = "author", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PostEntity> posts;

    public UUID getId(){
        return id;
    }

    public void setId(UUID id){
        this.id = (this.id == null) ? UUID.randomUUID() : id;
    }

}
