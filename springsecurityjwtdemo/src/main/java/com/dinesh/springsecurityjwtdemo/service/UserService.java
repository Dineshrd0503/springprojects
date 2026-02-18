package com.dinesh.springsecurityjwtdemo.service;


import com.dinesh.springsecurityjwtdemo.exception.UserNotFoundException;
import com.dinesh.springsecurityjwtdemo.model.Users;
import com.dinesh.springsecurityjwtdemo.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UsersRepository usersRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Users> getUsers() {
        return usersRepository.findAll();
    }

    public Users addUser(Users user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return usersRepository.save(user);
    }

    public Users updateUser(Users user) {
        Users u=usersRepository.findById(user.getId()).
                orElseThrow(()-> new UserNotFoundException("item not found with id "+ user.getId()));

        u.setUsername(user.getUsername());
        if (user.getPassword() != null && !user.getPassword().isEmpty()) {
            u.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        u.setRole(user.getRole());
        return usersRepository.save(u);


    }

    public Void deleteUser(Integer id) {
        Users u=usersRepository.findById(id).
                orElseThrow(()-> new UserNotFoundException("item not found with id "+ id));
        usersRepository.delete(u);
        return null;
    }

    public Users getUser(Integer id) {
        return usersRepository.findById(id).
                orElseThrow(()-> new UserNotFoundException("item not found with id "+ id));

    }
}
