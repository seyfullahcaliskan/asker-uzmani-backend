package com.askeruzmani.asker_uzmani_backend.Entities.Products;

import com.askeruzmani.asker_uzmani_backend.Entities.BaseEntity;
import com.askeruzmani.asker_uzmani_backend.Enums.YesNoEnum;
import jakarta.persistence.*;

@Entity
@Table(name = "product")
public class ProductEntity extends BaseEntity {

    @Column(name = "category", nullable = false)
    private String category;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "is_set", nullable = false)
    private YesNoEnum isSet;

    @Column(name = "name")
    private String name;

    @Column(name = "slug")
    private String slug;

    @Column(name = "description")
    private String description;

    @Column(name = "main_image_path")
    private String main_image_path;

    @Column(name = "price")
    private Integer price;

    @Column(name = "cart_price")
    private Integer cartPrice;

    @Column(name = "stock")
    private Integer stock;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public YesNoEnum getIsSet() {
        return isSet;
    }

    public void setIsSet(YesNoEnum isSet) {
        this.isSet = isSet;
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

    public String getMain_image_path() {
        return main_image_path;
    }

    public void setMain_image_path(String main_image_path) {
        this.main_image_path = main_image_path;
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
}
