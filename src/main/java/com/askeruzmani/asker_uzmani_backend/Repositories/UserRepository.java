package com.askeruzmani.asker_uzmani_backend.Repositories;


import com.askeruzmani.asker_uzmani_backend.Entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUsername(String username);

    UserEntity findByEmail(String email);

    UserEntity findByPhoneNumber1(String phoneNumber1);

    UserEntity findByIdentityNumber(String identityNumber);

    boolean findByVkn(String vkn);

    UserEntity findById(UUID userId);
}
