package com.askeruzmani.asker_uzmani_backend.Repositories.Products;

import com.askeruzmani.asker_uzmani_backend.Entities.Products.SubProductsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SubProductsRepository extends JpaRepository<SubProductsEntity, UUID> {

}