package com.dinesh.mvcproductmanagementsystem.repository;

import com.dinesh.mvcproductmanagementsystem.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Integer>, JpaSpecificationExecutor<Product> {



    List<Product> findByCategoryIgnoreCase(String category);

    List<Product> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);

    List<Product> findByNameIgnoreCase(String name);

    List<Product> findByQuantity(int quantity);

    @Query("select distinct p.category from Product p order by p.category")
    List<String> findDistinctCategories();
}
