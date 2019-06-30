package com.javaguru.SoloShoppingList.repository;

import com.javaguru.SoloShoppingList.service.*;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
@Profile("hibernate")
public class HibernateRepository implements ProductRepository {

    private final SessionFactory sessionFactory;

    @Autowired
    public HibernateRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Product add(Product product) {
        sessionFactory.getCurrentSession().save(product);
        return product;
    }

    @Override
    public Optional<Product> getProductById(Long id) {
        Product product = (Product) sessionFactory.getCurrentSession().createCriteria(Product.class)
                .add(Restrictions.eq("id", id)).uniqueResult();
        return Optional.ofNullable(product);
    }

    @Override
    public void removeProductById(Long id) {
        Product product = getProductById(id).orElseThrow(() ->
                new IllegalArgumentException("Product with id " + id + " not found"));
        sessionFactory.getCurrentSession().delete(product);
    }

    @Override
    public void removeAllProducts() {
        sessionFactory.getCurrentSession().clear();
    }

    @Override
    public void printAll() {
        List<Product> products = sessionFactory.getCurrentSession().createCriteria(Product.class).list();

        for (Product i : products) {
            System.out.println(i);
            System.out.println();
        }
    }

    @Override
    public boolean containsProduct(Product product) {
        String name = product.getName();
        String query = "select case when count(*)> 0 " +
                "then true else false end " +
                "from Products pr where pr.name = '" +
                name + "'";
        return (boolean) sessionFactory.getCurrentSession().createQuery(query).setMaxResults(1).uniqueResult();
    }

    @Override
    public long getStorageSize() {
        String query = "select count(*) from Products";
        return (long) sessionFactory.getCurrentSession().createQuery(query).setMaxResults(1).uniqueResult();
    }

    @Override
    public void changeName(Long id, String newName) {
        Product product = getProductById(id).orElseThrow(() ->
                new IllegalArgumentException("Product with id " + id + " not found"));
        product.setName(newName);
        sessionFactory.getCurrentSession().saveOrUpdate(product);
    }

    @Override
    public void changePrice(Long id, BigDecimal newPrice) {
        Product product = getProductById(id).orElseThrow(() ->
                new IllegalArgumentException("Product with id " + id + " not found"));
        product.setPrice(newPrice);
        sessionFactory.getCurrentSession().saveOrUpdate(product);
    }

    @Override
    public void changeDiscount(Long id, BigDecimal discount) {
        Product product = getProductById(id).orElseThrow(() ->
                new IllegalArgumentException("Product with id " + id + " not found"));
        product.setDiscount(discount);
        sessionFactory.getCurrentSession().saveOrUpdate(product);
    }

    @Override
    public void changeDescription(Long id, String newDescription) {
        Product product = getProductById(id).orElseThrow(() ->
                new IllegalArgumentException("Product with id " + id + " not found"));
        product.setDescription(newDescription);
        sessionFactory.getCurrentSession().saveOrUpdate(product);
    }
}
