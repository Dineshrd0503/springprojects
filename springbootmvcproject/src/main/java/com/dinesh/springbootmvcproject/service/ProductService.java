package com.dinesh.springbootmvcproject.service;

import com.dinesh.springbootmvcproject.model.Product;
import com.dinesh.springbootmvcproject.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private ProductRepository productRepository;
    @Autowired
    public ProductService(ProductRepository productRepository) {

        this.productRepository = productRepository;
    }
    public List<Product> displayProducts() {
        return productRepository.findAll();
    }

    public void createProduct(Product product) {
        List<Product> existingProducts = productRepository.findAll();
        boolean exists=
                existingProducts.stream()
                        .anyMatch(existing -> existing.getName().equalsIgnoreCase(product.getName()));
        if (exists) {
            throw new IllegalArgumentException("Product with name " + product.getName() + " already exists.");
        }
        productRepository.save(product);


    }

    public Product findProductById(int id) {
        Product product = productRepository.findById(id);
        if (product == null) {
            throw new IllegalArgumentException("Product with ID " + id + " does not exist.");
        }
        return product;
    }

    public void updateProduct(Product product, int id) {
        Product existingProduct = productRepository.findById(id);
        if (existingProduct == null) {
            throw new IllegalArgumentException("Product with ID " + id + " does not exist.");
        }
        productRepository.update(product);
    }

    public void deleteProduct(int id) {
        Product product = productRepository.findById(id);
        if (product == null) {
            throw new IllegalArgumentException("Product with ID " + id + " does not exist.");
        }
        productRepository.delete(id);
    }
}
