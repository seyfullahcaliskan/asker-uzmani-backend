package com.askeruzmani.asker_uzmani_backend.Repositories;

import com.askeruzmani.asker_uzmani_backend.Entities.NavlinksEntity;
import com.askeruzmani.asker_uzmani_backend.Enums.StatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface NavlinksRepository extends JpaRepository<NavlinksEntity, UUID> {
    List<NavlinksEntity> findByStatusOrderBySequenceNoAsc(StatusEnum status);
}
