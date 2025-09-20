package com.askeruzmani.asker_uzmani_backend.Repositories;

import com.askeruzmani.asker_uzmani_backend.Entities.Orders.OrdersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrdersRepository extends JpaRepository<OrdersEntity, UUID> {

    Optional<OrdersEntity> findByMerchantOid(String merchantOid);

}