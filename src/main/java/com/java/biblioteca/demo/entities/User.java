package com.java.biblioteca.demo.entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table( name="users" )
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column( name="id" )
    @GeneratedValue( strategy=GenerationType.IDENTITY )
    private Long id;

    @NotEmpty
    @Column( unique=true, length=30 )
    private String username;

    @NotEmpty
    @Column( length=70 )
    private String password;

    @Email
    @NotEmpty
    @Column( unique=true, length=50 )
    private String email;

    // @NotEmpty
    @Column(  )
    @Temporal( TemporalType.DATE )
    private Date createdAt;

    @PrePersist
    public void prePersist() {
        createdAt = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
