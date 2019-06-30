package com.javaguru.SoloShoppingList.service;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

@Entity (name = "Products")
@Table
public class Product {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "description")
    private String description;


    @Column(columnDefinition = "enum('FRUIT', 'VEGETABLE', 'MILK', 'MEAT', 'FISH', 'ALCOHOL')", name = "category")
    @Enumerated(EnumType.STRING)
    private Category category;

    @Column(name = "discount")
    private BigDecimal discount;

    @Column(name = "discountPrice")
    private BigDecimal discountPrice;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        if (discount == null || discount.intValue() == 0) {
            this.discount = null;
            this.discountPrice = null;
        } else if (discount.intValue() > 0 && discount.intValue() <= 80) {
            this.discount = discount;
            setDiscountPrice();
        } else {
            System.out.println("Discount must be in range 0 - 80");
        }
    }

    private void setDiscountPrice() {
        BigDecimal toSubtract = discount.multiply(price.divide(new BigDecimal("100")));
        discountPrice = price.subtract(toSubtract).setScale(2, RoundingMode.HALF_EVEN);
    }

    public BigDecimal getDiscountPrice() {
        return discountPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(name, product.name) &&
                Objects.equals(price, product.price) &&
                Objects.equals(description, product.description) &&
                category == product.category &&
                Objects.equals(discount, product.discount) &&
                Objects.equals(discountPrice, product.discountPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, description, category, discount, discountPrice);
    }

    @Override
    public String toString() {
        String info = "Product: " + this.getId() + '\n' +
                "name = " + name + '\n' +
                "price = " + price + '\n' +
                "productCategory = " + category + '\n';
        if (discount != null && discount.intValue() != 0) {
            info = info + "discount = " + discount + "%" + '\n' +
                    "discountPrice = " + discountPrice + '\n';
        }
        if (description != null) {
            info = info + "description = " + description;
        }
        return info;
    }
}