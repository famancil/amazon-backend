package com.java.biblioteca.demo.services.user;

import com.java.biblioteca.demo.entities.User;

import java.util.List;

public interface IUserService {

    public User save(User user );

    public void delete( Long id );

    public List<User> findAll();

    public User findById( Long id );

    public User findByUsername( String username );
}
