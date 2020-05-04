package com.java.biblioteca.demo.services.user;

import com.java.biblioteca.demo.entities.User;
import com.java.biblioteca.demo.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {

    @Autowired
    IUserRepository userRepository;

    @Override
    public User save(User user) { return userRepository.save(user);}

    @Override
    public void delete(Long id) { userRepository.deleteById(id);}

    @Override
    public List<User> findAll() { return (List<User>) userRepository.findAll();}

    @Override
    public User findById(Long id) { return userRepository.findById(id).orElse(null);}

    @Override
    public User findByUsername(String username) { return userRepository.findByUsername(username);}
}
