package com.dinesh.hibernatedemo.controller;


import com.dinesh.hibernatedemo.model.Author;
import com.dinesh.hibernatedemo.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/authors")
public class AuthorController {
    private AuthorService authorService;
            @Autowired
            public AuthorController(AuthorService authorService){
        this.authorService=authorService;
            }
    @GetMapping("/allAuthors")
    public ResponseEntity<List<Author>> getAllAuthors(){
        List<Author> authors=authorService.findAllAuthors();
        return ResponseEntity.ok(authors);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Author>> getAuthorById(@PathVariable Long id){
        Optional<Author> author=authorService.getAuthorById(id);
        return ResponseEntity.ok(author);
    }


    @PostMapping("/addAuthor")
    public ResponseEntity<Author> createAuthor(@RequestBody Author author){
        Author newAuthor=authorService.saveAuthor(author);
        return ResponseEntity.ok(newAuthor);

    }

    @PutMapping("/updateAuthor/{id}")
    public ResponseEntity<Author> updateAuthor(@PathVariable Long id,@RequestBody Author author){
        Author updatedAuthor = authorService.updateAuthor(author);
        return ResponseEntity.ok(updatedAuthor);
    }

    @DeleteMapping("/deleteAuthor/{id}")
    public ResponseEntity<Optional<String>> deleteAuthor(@PathVariable Long id){
        authorService.deleteAuthor(id);
        return ResponseEntity.ok(Optional.of("Author with id: " + id + " deleted successfully"));
    }
}
