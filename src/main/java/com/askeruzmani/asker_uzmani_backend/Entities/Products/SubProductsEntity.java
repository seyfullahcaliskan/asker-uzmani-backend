package com.askeruzmani.asker_uzmani_backend.Entities.Products;

import com.askeruzmani.asker_uzmani_backend.Entities.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "sub_products")
public class SubProductsEntity extends BaseEntity {

    @Column(name = "main_product_id")
    private UUID mainProductId;

    @Column(name = "product_id")
    private UUID productId;

    public UUID getMainProductId() {
        return mainProductId;
    }

    public void setMainProductId(UUID mainProductId) {
        this.mainProductId = mainProductId;
    }

    public UUID getProductId() {
        return productId;
    }

    public void setProductId(UUID productId) {
        this.productId = productId;
    }
}
