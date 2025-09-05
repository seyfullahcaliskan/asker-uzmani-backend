package com.askeruzmani.asker_uzmani_backend.Entities.Products;

import com.askeruzmani.asker_uzmani_backend.Entities.BaseEntity;
import com.askeruzmani.asker_uzmani_backend.Enums.YesNoEnum;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "product_images")
public class ProductImagesEntity extends BaseEntity {

    @Column(name = "product_id")
    private UUID productId;

    @Column(name = "path")
    private String path;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "is_main_image")
    private YesNoEnum isMainImage;

    public UUID getProductId() {
        return productId;
    }

    public void setProductId(UUID productId) {
        this.productId = productId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public YesNoEnum getIsMainImage() {
        return isMainImage;
    }

    public void setIsMainImage(YesNoEnum isMainImage) {
        this.isMainImage = isMainImage;
    }
}
