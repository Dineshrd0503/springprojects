package com.dinesh.repository;

import com.dinesh.model.Book;
import java.util.List;

public interface BookRepository {
    void save(Book book);
    List<Book> findAll();
    Book findById(int id);
}