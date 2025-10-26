package com.dinesh.hibernatedemo.service;

import com.dinesh.hibernatedemo.model.User;
import com.dinesh.hibernatedemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public User saveUser(User user){
        return userRepository.save(user);
    }
    public Optional<User> getUserById(Long id){
        return userRepository.findById(id);
    }
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
    public List<User> searchUsers(String name){
        return userRepository.findByNameContaining(name);
    }
    public User updateUser(User user){
        return userRepository.save(user);
    }
    public String deleteUser(Long id){
        if(!userRepository.existsById(id))
            return "User Not Found";
        userRepository.deleteById(id);
        return "User Deleted";
    }
    public User searchById(Long id){
        return userRepository.findById(id).orElse(null);
    }




}
