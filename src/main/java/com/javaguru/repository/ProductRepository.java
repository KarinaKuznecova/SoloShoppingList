package com.javaguru.repository;

import com.javaguru.service.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Component
@Profile("mainProfile")
public class ProductRepository implements Repository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Product add(Product product) {
        String query = "insert into products (name, price, description, category, discount, discountPrice) values (" +
                "?, ?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, product.getName());
            ps.setBigDecimal(2, product.getPrice());
            ps.setString(3, product.getDescription());
            ps.setString(4, product.getCategory().toString());
            ps.setBigDecimal(5, product.getDiscount());
            ps.setBigDecimal(6, product.getDiscountPrice());
            return ps;
        }, keyHolder);
        product.setId(keyHolder.getKey().longValue());
        return product;

    }

    public Optional<Product> getProductById(Long id) {
        String query = "select * from products where id = " + id;
        List<Product> products = jdbcTemplate.query(query, new BeanPropertyRowMapper(Product.class));
        if (!products.isEmpty()) {
            return Optional.ofNullable(products.get(0));
        }
        return Optional.empty();
    }

    public void removeProductById(Long id) {
        String query = "delete from products where id = " + id;
        jdbcTemplate.execute(query);
    }

    public void removeAllProducts() {
        String query = "delete from products";
        jdbcTemplate.execute(query);
    }

    public void printAll() {
        String query = "select * from products";
        List<Product> products = jdbcTemplate.query(query, new BeanPropertyRowMapper(Product.class));
        for (Product i : products){
            System.out.println(i);
            System.out.println();
        }
    }

    public boolean containsProduct(Product product) {
        String name = product.getName();
        String query = "select case when count(*)> 0 " +
                "then true else false end " +
                "from products pr where pr.name = '" +
                name + "'";
        return jdbcTemplate.queryForObject(query, Boolean.class);
    }

    public long getStorageSize() {
        String query = "select count(*) from products";
        return jdbcTemplate.queryForObject(query, Long.class);
    }

    public void changeName(Long id, String newName){
        String query = "update products set name = '" + newName + "' where id = " + id;
        jdbcTemplate.execute(query);
    }

    public void changePrice(Long id, BigDecimal newPrice) {
        String query = "update products set price = " + newPrice + " where id = " + id;
        jdbcTemplate.execute(query);
    }

    public void changeDiscount(Long id, BigDecimal discount) {
        String query = "update products set discount = " + discount + " where id = " + id;
        jdbcTemplate.execute(query);
    }

    public void changeDescription(Long id, String newDescription) {
        String query = "update products set description = '" + newDescription + "' where id = " + id;
        jdbcTemplate.execute(query);
    }
}
