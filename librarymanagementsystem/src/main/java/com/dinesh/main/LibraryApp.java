package com.dinesh.main;
import com.dinesh.model.*;
import com.dinesh.config.AppConfig;
import com.dinesh.service.LibraryService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class LibraryApp {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        LibraryService libraryService = context.getBean(LibraryService.class);
        System.out.println("")

        libraryService.initializeBooks();
        libraryService.listBooks();
        libraryService.borrowBook(1, 1);
        libraryService.listBooks();
        libraryService.returnBook(1);
        libraryService.listBooks();
        libraryService.addBook(context.getBean("newBook", Book.class));
        libraryService.listBooks();
    }
}