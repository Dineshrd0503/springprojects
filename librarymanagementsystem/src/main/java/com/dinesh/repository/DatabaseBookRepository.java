package com.dinesh.repository;

import com.dinesh.model.Book;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("databaseBookRepository")
public class DatabaseBookRepository implements BookRepository {
    private List<Book> books = new ArrayList<>();

    @Override
    public void save(Book book) {
        books.add(book);
        System.out.println("Saved to database: " + book);
    }

    @Override
    public List<Book> findAll() {
        return new ArrayList<>(books);
    }

    @Override
    public Book findById(int id) {
        return books.stream().filter(book -> book.getId() == id).findFirst().orElse(null);
    }
}