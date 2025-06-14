package com.dinesh.springjdbcproject.model;

public class Product {
    private int id;
    private String name;
    private double price;
   private double quantity;

    public Product(int id, String name, double price, double quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;

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

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
