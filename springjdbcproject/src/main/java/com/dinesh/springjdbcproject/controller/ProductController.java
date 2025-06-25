package com.dinesh.springjdbcproject.controller;

import com.dinesh.springjdbcproject.model.Product;
import com.dinesh.springjdbcproject.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ProductController {
    @Autowired
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    Scanner sc = new Scanner(System.in);

    public void start() {
        while (true) {
            System.out.println("1. Create Product");
            System.out.println("2. Delete Product");
            System.out.println("3. Display Products");
            System.out.println("4. Update Product");
            System.out.println("5. find product by id");

            int choice = 0;
            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input, please enter a number.");
                continue;
            }

            switch (choice) {

                case 1 -> {
                    System.out.println("Creating a new product...");
                    System.out.println("enter product id:");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.println("enter product name:");
                    String productName = sc.nextLine();
                    System.out.println("enter product price:");
                    double productPrice = sc.nextDouble();
                    System.out.println("enter product quantity:");
                    double productQuantity = sc.nextDouble();

                    productService.createProduct(new Product(id, productName, productQuantity, productPrice));
                    System.out.println("Product created successfully!");
                }
                case 2 -> {
                    System.out.println("Deleting a product...");
                    System.out.println("enter product id:");
                    int id = sc.nextInt();
                    productService.deleteProduct(id);
                    System.out.println("Product with ID " + id + " deleted successfully!");
                }
                case 3 -> {
                    System.out.println("Displaying all products...");
                    productService.displayProducts().forEach(System.out::println);
                    System.out.println("Total products: " + productService.displayProducts().size());
                }
                case 4 -> {
                    System.out.println("Updating a product...");
                    System.out.println("enter product id:");
                    int id = sc.nextInt();
                    sc.nextLine(); // Consume newline character
                    System.out.println("enter new product name:");
                    String productName = sc.nextLine();
                    System.out.println("enter new product price:");
                    double productPrice = sc.nextDouble();
                    System.out.println("enter new product quantity:");
                    double productQuantity = sc.nextDouble();

                    Product updatedProduct = new Product(id, productName, productPrice, productQuantity);
                    productService.updateProduct(updatedProduct, id);
                    System.out.println("Product with ID " + id + " updated successfully!");
                }
                case 5 -> {
                    System.out.println("Finding a product by ID...");
                    System.out.println("enter product id:");
                    int id = sc.nextInt();
                    productService.findProductById(id);




                }
                default -> System.out.println("Invalid choice, please try again.");
            }

        }
    }
}
