package com.askeruzmani.asker_uzmani_backend.Repositories.Products;

import com.askeruzmani.asker_uzmani_backend.Entities.Products.ProductImagesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductImagesRepository extends JpaRepository<ProductImagesEntity, UUID> {

    @Query("SELECT pi FROM ProductImagesEntity pi " +
            "JOIN ProductEntity p ON pi.productId = p.id " +
            "WHERE pi.productId = :productId AND p.status = com.askeruzmani.asker_uzmani_backend.Enums.StatusEnum.ACTIVE")
    List<ProductImagesEntity> findActiveByProductId(UUID productId);
}