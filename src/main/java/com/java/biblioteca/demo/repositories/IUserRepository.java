package com.java.biblioteca.demo.repositories;

import com.java.biblioteca.demo.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface IUserRepository extends CrudRepository<User,Long> {

    User findByUsername( String username );
}
