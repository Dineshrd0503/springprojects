package com.dinesh.config;

import com.dinesh.model.Book;
import com.dinesh.model.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@ComponentScan(basePackages = "com.dinesh")
public class AppConfig {

    @Bean
    public List<Book> initialBooks() {
        List<Book> books = new ArrayList<>();
        books.add(new Book(1, "Java Programming", "John Doe"));
        books.add(new Book(2, "Spring Basics", "Jane Smith"));
        return books;
    }

    @Bean
    public List<User> users() {
        List<User> users = new ArrayList<>();
        users.add(new User(1, "Alice"));
        users.add(new User(2, "Bob"));
        return users;
    }

    @Bean
    public Book newBook() {
        return new Book(3, "Design Patterns", "Erich Gamma");
    }
}