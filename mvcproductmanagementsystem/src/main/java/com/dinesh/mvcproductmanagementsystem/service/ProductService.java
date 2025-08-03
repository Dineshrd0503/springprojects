package com.dinesh.mvcproductmanagementsystem.service;

import com.dinesh.mvcproductmanagementsystem.model.Product;
import com.dinesh.mvcproductmanagementsystem.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    /**
     * Deletes a product by its ID.
     * The controller will handle exceptions if the product does not exist.
     */
    public void deleteProduct(Integer id) {
        // No need for a try-catch here. Let the controller handle exceptions
        // like EmptyResultDataAccessException if the ID is not found.
        productRepository.deleteById(id);
    }

    public Optional<Product> findProductById(Integer id) {
        return productRepository.findById(id);
    }

    public List<Product> searchProductsByExample(Integer id, String name, String category, Integer quantity) {
        Product probe = new Product();
        // Set the non-primitive types first. Nulls will be ignored by the matcher.
        probe.setName(name);
        probe.setCategory(category);

        // Dynamically build a list of fields to ignore in the query.
        List<String> ignorePaths = new ArrayList<>();
        // Always ignore fields that are not on the search form.
        ignorePaths.add("price");
        ignorePaths.add("description");

        // --- FIX: Conditionally handle primitive-type fields ---

        if (id != null) {
            // If an ID is provided, set it on the probe for the search.
            probe.setId(id);
        } else {
            // If the user didn't provide an ID, ignore the 'id' field completely.
            ignorePaths.add("id");
        }

        if (quantity != null) {
            // If a quantity is provided, set it on the probe.
            probe.setQuantity(quantity);
        } else {
            // If the user didn't provide a quantity, ignore the 'quantity' field.
            ignorePaths.add("quantity");
        }

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withIgnoreCase()
                .withIgnoreNullValues()
                // Apply the dynamically built list of ignored paths.
                .withIgnorePaths(ignorePaths.toArray(new String[0]));

        Example<Product> example = Example.of(probe, matcher);

        return productRepository.findAll(example);
    }
}