package com.askeruzmani.asker_uzmani_backend.Entities.Products;

import com.askeruzmani.asker_uzmani_backend.Entities.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "product_sizes")
public class ProductSizesEntity extends BaseEntity {

    @Column(name = "product_id")
    private UUID productId;

    @Column(name = "value")
    private String value;

    public UUID getProductId() {
        return productId;
    }

    public void setProductId(UUID productId) {
        this.productId = productId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
