package com.askeruzmani.asker_uzmani_backend.Repositories;

import com.askeruzmani.asker_uzmani_backend.Entities.BanksEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BanksRepository extends JpaRepository<BanksEntity, UUID> {

}
