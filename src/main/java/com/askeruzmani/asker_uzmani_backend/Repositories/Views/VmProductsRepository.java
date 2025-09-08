package com.askeruzmani.asker_uzmani_backend.Repositories.Views;

import com.askeruzmani.asker_uzmani_backend.Entities.Views.VmProductsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VmProductsRepository extends JpaRepository<VmProductsEntity, UUID> {

}
