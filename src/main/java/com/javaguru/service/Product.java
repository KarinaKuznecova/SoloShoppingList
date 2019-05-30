package com.javaguru.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class Product {

    private Long id;
    private String name;
    private BigDecimal price;
    private String description;
    private Category category;
    private BigDecimal discount;
    private BigDecimal discountPrice;

    Long getId() {
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

    void setDiscount(BigDecimal discount) {
        if (discount.intValue() > 0 && discount.intValue() <= 80) {
            this.discount = discount;
            setDiscountPrice();
        } else if (discount.intValue() == 0) {
            this.discount = null;
            this.discountPrice = null;
        } else {
            System.out.println("Discount must be in range 0 - 80");
        }
    }

    private void setDiscountPrice() {
        BigDecimal toSubtract = discount.multiply(((getPrice().divide(new BigDecimal("100")))));
        discountPrice = getPrice().subtract(toSubtract).setScale(2, RoundingMode.HALF_EVEN);
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