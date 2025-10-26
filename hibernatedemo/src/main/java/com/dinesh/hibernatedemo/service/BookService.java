package com.dinesh.hibernatedemo.service;


import com.dinesh.hibernatedemo.model.Book;
import com.dinesh.hibernatedemo.repository.BookRepository;
import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private BookRepository bookRepository;
    private EntityManager entityManager;

    @Autowired
    public BookService(BookRepository bookRepository, EntityManager entityManager) {
        this.bookRepository = bookRepository;
        this.entityManager = entityManager;

    }

    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    public Optional<Book> getBookById(Long id) {
        // FIX: Replaced manual session management with the correct repository method.
        // This prevents LazyInitializationException and is the standard Spring Data JPA approach.
        return bookRepository.findById(id);
    }

    public List<Book> searchBooks(String title) {
        return bookRepository.findByTitleContaining(title);
    }

    public void evictCache() {
        entityManager.unwrap(Session.class).getSessionFactory().getCache().evictAll();
    }

    public List<Book> getAllBooks(){
        // FIX: Changed to use the new repository method that eagerly fetches borrowers.
        // This is the final fix for the LazyInitializationException on the 'allBooks' endpoint.
        return bookRepository.findAllWithBorrowers();
    }

    public Book updateBook(Book book){
        return bookRepository.save(book);
    }

    public String deleteBook(Long id){
        if(!bookRepository.existsById(id))
            return "Book Not Found";
        bookRepository.deleteById(id);
        return "Book Deleted";
    }

}
