package com.dinesh.telecominventory.repository;

import com.dinesh.telecominventory.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}