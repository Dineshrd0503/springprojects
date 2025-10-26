package com.dinesh.hibernatedemo.controller;

import com.dinesh.hibernatedemo.model.Book;
import com.dinesh.hibernatedemo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {
    private BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/addBook")
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        Book savedBook = bookService.addBook(book);
        return ResponseEntity.ok(savedBook);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Optional<Book> book = bookService.getBookById(id);
        return book.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

    }

    @GetMapping("/allBooks")
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        return ResponseEntity.ok(books);
    }

    @PutMapping("/updateBook/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book bookDetails) {

        Optional<Book> optionalBook = bookService.getBookById(id);
        if (optionalBook.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        // FIX: Corrected the update logic.
        // Now it correctly updates the existing book with the new details.
        Book bookToUpdate = optionalBook.get();
        bookToUpdate.setTitle(bookDetails.getTitle());
        bookToUpdate.setAuthor(bookDetails.getAuthor());

        Book updatedBook = bookService.updateBook(bookToUpdate);
        return ResponseEntity.ok(updatedBook);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.deleteBook(id));

    }


}
