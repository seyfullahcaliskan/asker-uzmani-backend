package com.askeruzmani.asker_uzmani_backend.Entities.Products;

import com.askeruzmani.asker_uzmani_backend.Entities.BaseEntity;
import com.askeruzmani.asker_uzmani_backend.Enums.YesNoEnum;
import jakarta.persistence.*;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;

import java.util.List;

@Entity
@Table(name = "product")
@FilterDef(
        name = "statusFilter",
        parameters = @ParamDef(name = "status", type = Integer.class)
)
@Filter(
        name = "statusFilter",
        condition = "status = :status"
)
public class ProductDetailDTO extends BaseEntity {

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
    private String mainImagePath;

    @Column(name = "price")
    private Integer price;

    @Column(name = "cart_price")
    private Integer cartPrice;

    @Column(name = "stock")
    private Integer stock;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    @Filter(name = "statusFilter", condition = "status = :status")
    private List<ProductImagesEntity> images;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    @Filter(name = "statusFilter", condition = "status = :status")
    private List<ProductSizesEntity> sizes;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "main_product_id")
    @Filter(name = "statusFilter", condition = "status = :status")
    private List<SubProductsEntity> subProducts;

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

    public List<ProductImagesEntity> getImages() {
        return images;
    }

    public void setImages(List<ProductImagesEntity> images) {
        this.images = images;
    }

    public List<ProductSizesEntity> getSizes() {
        return sizes;
    }

    public void setSizes(List<ProductSizesEntity> sizes) {
        this.sizes = sizes;
    }

    public List<SubProductsEntity> getSubProducts() {
        return subProducts;
    }

    public void setSubProducts(List<SubProductsEntity> subProducts) {
        this.subProducts = subProducts;
    }
}