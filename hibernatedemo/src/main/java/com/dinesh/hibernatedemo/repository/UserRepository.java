package com.dinesh.hibernatedemo.repository;

import com.dinesh.hibernatedemo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {
    List<User> findByNameContaining(String name);
}
