package com.dinesh.mvcproductmanagementsystem.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 50)
    @NotBlank(message = "Name is mandatory")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String name;

    @Column(length = 255)
    @Size(max = 255, message = "Description must be less than 255 characters")
    private String description;

    @Column(nullable = false, precision = 10, scale = 2)
    @NotNull(message = "Price is mandatory")
    @DecimalMin(value = "0.01", message = "Price must be greater than 0")
    @Digits(integer = 8, fraction = 2, message = "Price must be a valid decimal number.")
    private BigDecimal price;

    @Column(nullable = false)
    @NotNull(message = "Quantity is mandatory")
    @Min(value = 0, message = "Quantity cannot be negative")
    private int quantity;

    // --- NEWLY ADDED FIELD ---
    @Column(nullable = false, length = 50)
    @NotBlank(message = "Category is mandatory")
    private String category;

    public Product() {
    }

    // Updated constructor to include the category
    public Product(int id, String name, String description, BigDecimal price, int quantity, String category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.category = category; // Initialize the new field
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // --- GETTER AND SETTER FOR CATEGORY ---
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}