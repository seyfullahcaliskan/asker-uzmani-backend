package com.askeruzmani.asker_uzmani_backend.Entities.Views;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import java.util.UUID;

@Entity
@Immutable
@Subselect("""
    SELECT * FROM public.vm_products
""")
public class VmProductsEntity {

    @Id
    private UUID id;

    private String category;
    private String name;
    private String slug;
    private String description;
    private String mainImagePath;
    private Integer price;
    private Integer cartPrice;
    private Integer stock;

    @Column(columnDefinition = "json")
    private String images;

    @Column(columnDefinition = "json")
    private String sizes;

    @Column(columnDefinition = "json")
    private String subProducts;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMainImagePath() {
        return mainImagePath;
    }

    public void setMainImagePath(String mainImagePath) {
        this.mainImagePath = mainImagePath;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getCartPrice() {
        return cartPrice;
    }

    public void setCartPrice(Integer cartPrice) {
        this.cartPrice = cartPrice;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getSizes() {
        return sizes;
    }

    public void setSizes(String sizes) {
        this.sizes = sizes;
    }

    public String getSubProducts() {
        return subProducts;
    }

    public void setSubProducts(String subProducts) {
        this.subProducts = subProducts;
    }
}
