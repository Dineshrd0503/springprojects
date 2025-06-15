package com.dinesh.springjdbcproject.service;

import com.dinesh.springjdbcproject.model.Product;
import com.dinesh.springjdbcproject.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }
    public void createProduct(Product product) {
        productRepository.save(product);
    }

    public void deleteProduct(int id){
        productRepository.delete(id);
    }

    public List<Product> displayProducts(){
        return productRepository.findAll();
    }

    public void updateProduct(Product product,int id) {
        Product product1=productRepository.findById(id);
        if (product1 == null) {
            throw new IllegalArgumentException("Product with ID " + product.getId() + " does not exist.");

        }
        productRepository.update(product);
    }

    public void findProductById(int id) {
        Product product = productRepository.findById(id);
        if (product != null) {
            System.out.println("Product found: " + product);
        } else {
            System.out.println("Product with ID " + id + " not found.");
        }
    }


}
