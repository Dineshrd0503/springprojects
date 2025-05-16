package com.dinesh.service;

import com.dinesh.model.Book;
import com.dinesh.model.User;
import com.dinesh.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LibraryService {
    private BookRepository primaryRepository;
    private BookRepository databaseRepository;
    private List<User> users;

    @Autowired
    public LibraryService(@Qualifier("inMemoryBookRepository") BookRepository primaryRepository,
                          @Qualifier("databaseBookRepository") BookRepository databaseRepository) {
        this.primaryRepository = primaryRepository;
        this.databaseRepository = databaseRepository;
    }

    @Autowired
    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Autowired
    @Qualifier("initialBooks")
    private List<Book> initialBooks;

    public void addBook(Book book) {
        primaryRepository.save(book);
        databaseRepository.save(book);
    }

    public void borrowBook(int bookId, int userId) {
        Book book = primaryRepository.findById(bookId);
        if (book != null && !book.isBorrowed()) {
            book.setBorrowed(true);
            System.out.println("Book " + book.getTitle() + " borrowed by User ID " + userId);
        } else {
            System.out.println("Book is not available or already borrowed");
        }
    }

    public void returnBook(int bookId) {
        Book book = primaryRepository.findById(bookId);
        if (book != null && book.isBorrowed()) {
            book.setBorrowed(false);
            System.out.println("Book " + book.getTitle() + " returned");
        } else {
            System.out.println("Book not found or not borrowed");
        }
    }

    public void listBooks() {
        System.out.println("Books in primary repository: " + primaryRepository.findAll());
        System.out.println("Books in database repository: " + databaseRepository.findAll());
    }

    public void initializeBooks() {
        initialBooks.forEach(primaryRepository::save);
        System.out.println("Initialized books: " + initialBooks);
    }
}