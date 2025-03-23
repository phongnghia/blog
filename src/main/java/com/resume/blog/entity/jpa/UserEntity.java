package com.resume.blog.entity.jpa;

import com.resume.blog.entity.base.BaseUserEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table( name = "user")
public class UserEntity extends BaseUserEntity {

    @Column( nullable = false )
    private String passwordHash;

    @Column ( nullable = false )
    private String username;

    @OneToMany( mappedBy = "author", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PostEntity> posts;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn( name = "user_id" ),
            inverseJoinColumns = @JoinColumn( name = "role_id" )
    )
    private Set<RoleEntity> roles = new HashSet<>();

}
