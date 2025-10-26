package com.dinesh.hibernatedemo.service;

import com.dinesh.hibernatedemo.model.Author;
import com.dinesh.hibernatedemo.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {
    private AuthorRepository authorRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Author saveAuthor(Author author){
        return authorRepository.save(author);
    }
    public Optional<Author> getAuthorById(Long id){
        return authorRepository.findById(id);
    }
    public List<Author> findAllAuthors(){
        return authorRepository.findAll();
    }
    public List<Author> searchAuthors(String name){
        return authorRepository.findByNameContaining(name);
    }
    public Author updateAuthor(Author author){
        return authorRepository.save(author);
    }
    public void deleteAuthor(Long id){
        authorRepository.deleteById(id);
    }



}
