package com.dinesh.hibernatedemo.repository;

import com.dinesh.hibernatedemo.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author,Long> {
    List<Author> findByNameContaining(String name);

}

