package com.dinesh.springjdbcproject.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.dinesh.springjdbcproject.model.Product;
import com.dinesh.springjdbcproject.rowmapper.ProductRowMapper;

import java.util.List;

@Repository
public class ProductRepository {
    @Autowired
    private final JdbcTemplate jdbcTemplate;
    public ProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(Product product){
        String sql = "INSERT INTO products (id, name, quantity,price) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, product.getId(), product.getName(), product.getQuantity(), product.getPrice());
    }

    public List<Product> findAll(){
        String sql="select * from products";
        return jdbcTemplate.query(sql, new ProductRowMapper());
    }


}
