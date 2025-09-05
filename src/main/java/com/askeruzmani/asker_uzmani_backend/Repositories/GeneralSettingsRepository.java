package com.askeruzmani.asker_uzmani_backend.Repositories;

import com.askeruzmani.asker_uzmani_backend.Entities.GeneralSettingsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface GeneralSettingsRepository extends JpaRepository<GeneralSettingsEntity, UUID> {

}