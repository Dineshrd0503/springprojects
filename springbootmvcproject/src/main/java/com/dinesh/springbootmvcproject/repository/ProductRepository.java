package com.dinesh.springbootmvcproject.repository;

import com.dinesh.springbootmvcproject.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Repository
public class ProductRepository {
    @Autowired
    private final JdbcTemplate jdbcTemplate;
    public ProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Product save(Product product){
        String sql="insert into products(name,price,quantity) values(?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, product.getName());
            ps.setDouble(2, product.getPrice());
            ps.setDouble(3, product.getQuantity());
            return ps;
        }, keyHolder);

        // Retrieve the generated ID from the KeyHolder and set it on the product object
        if (keyHolder.getKey() != null) {
            product.setId(keyHolder.getKey().intValue());
        }
        return product;

    }

    public void update(Product product){
        String sql="update products set name=?, price=?, quantity=? where id=?";
        jdbcTemplate.update(sql, product.getName(), product.getPrice(), product.getQuantity(), product.getId());
    }

    public void delete(int id){
        String sql="delete from products where id=?";
        jdbcTemplate.update(sql, id);
    }

    public List<Product> findAll(){
        String sql="select * from products";
        return jdbcTemplate.query(sql,new ProductRowMapper());
    }

    private static class ProductRowMapper implements RowMapper<Product>{
        @Override
        public Product mapRow(ResultSet rs,int rowNum) throws SQLException{
            return new Product(rs.getInt("id"),rs.getString("name"),rs.getDouble("price"),rs.getDouble("quantity"));
        }
    }

    public Product findById(int id) {
        String sql = "SELECT * FROM products WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new ProductRowMapper(), id);
    }


}
