package com.askeruzmani.asker_uzmani_backend.Repositories.Products;

import com.askeruzmani.asker_uzmani_backend.Entities.Products.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, UUID> {

}