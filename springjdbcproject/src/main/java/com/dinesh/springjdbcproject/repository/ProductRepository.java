package com.dinesh.springjdbcproject.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import com.dinesh.springjdbcproject.model.Product;
import java.sql.ResultSet;


import java.sql.SQLException;
import java.util.List;

@Repository
public class ProductRepository {
    @Autowired
    private final JdbcTemplate jdbcTemplate;
    public ProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(Product product){
        String sql = "INSERT INTO products (id, name, quantity,price) VALUES (?, ?, ?,?)";
        jdbcTemplate.update(sql, product.getId(), product.getName(), product.getQuantity(), product.getPrice());
    }

    public List<Product> findAll(){
        String sql="select * from products";
        return jdbcTemplate.query(sql, new ProductRowMapper());
    }

    private static class ProductRowMapper implements RowMapper<Product> {
        @Override
        public Product mapRow(ResultSet rs,int rowNum) throws SQLException{
            return new Product(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getDouble("price"),
                    rs.getDouble("quantity")


            );
        }
    }

    public Product findById(int id) {
        String sql = "SELECT * FROM products WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new ProductRowMapper(),id);
    }

    public void update(Product product) {
        String sql = "UPDATE products SET name = ?, price = ?, quantity = ? WHERE id = ?";
        jdbcTemplate.update(sql, product.getName(), product.getPrice(), product.getQuantity(), product.getId());
    }

    public void delete(int id){
        String sql="delete from products where id=?";
        jdbcTemplate.update(sql,id);
    }


}
