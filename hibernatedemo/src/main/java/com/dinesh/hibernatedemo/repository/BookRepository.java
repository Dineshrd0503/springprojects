package com.dinesh.hibernatedemo.repository;

import com.dinesh.hibernatedemo.model.Book;
import jakarta.persistence.QueryHint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("SELECT b FROM Book b WHERE b.title like %:title%")
    @QueryHints(@QueryHint(name =org.hibernate.jpa.HibernateHints.HINT_CACHEABLE, value = "true"))
    List<Book> findByTitleContaining(String title);

    // FIX: Added a query to fetch books and their associated borrowers eagerly.
    // This prevents LazyInitializationException when serializing the list of books.
    @Query("SELECT DISTINCT b FROM Book b LEFT JOIN FETCH b.borrowers")
    List<Book> findAllWithBorrowers();
}
