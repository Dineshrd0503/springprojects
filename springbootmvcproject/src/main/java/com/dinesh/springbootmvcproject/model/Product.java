package com.dinesh.springbootmvcproject.model;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Min;
public class Product {

    private int id;

    @NotBlank(message = "Product name cannot is required")
    private String name;

    @Min(value=0, message = "Price must be greater than or equal to 0")
    private double price;

    @Min(value=0, message = "Quantity must be greater than or equal to 0")
    private double quantity;

    public Product(int id, String name, double price, double quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
    public Product() {
        // Default constructor
    }
    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", quantity='" + quantity + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
}
