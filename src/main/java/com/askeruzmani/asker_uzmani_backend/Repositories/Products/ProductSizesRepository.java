package com.askeruzmani.asker_uzmani_backend.Repositories.Products;

import com.askeruzmani.asker_uzmani_backend.Entities.Products.ProductSizesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductSizesRepository extends JpaRepository<ProductSizesEntity, UUID> {

    @Query("SELECT ps FROM ProductSizesEntity ps " +
            "JOIN ProductEntity p ON ps.productId = p.id " +
            "WHERE ps.productId = :productId AND p.status = com.askeruzmani.asker_uzmani_backend.Enums.StatusEnum.ACTIVE")
    List<ProductSizesEntity> findActiveByProductId(UUID productId);
}