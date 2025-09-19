package com.askeruzmani.asker_uzmani_backend.Repositories.Products;

import com.askeruzmani.asker_uzmani_backend.Entities.Products.SubProductsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SubProductsRepository extends JpaRepository<SubProductsEntity, UUID> {

    @Query("SELECT sp FROM SubProductsEntity sp " +
            "JOIN ProductEntity p ON sp.productId = p.id " +
            "WHERE sp.mainProductId = :productId AND p.status = com.askeruzmani.asker_uzmani_backend.Enums.StatusEnum.ACTIVE")
    List<SubProductsEntity> findActiveByMainProductId(UUID productId);
}