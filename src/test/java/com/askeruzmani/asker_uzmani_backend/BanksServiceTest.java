package com.askeruzmani.asker_uzmani_backend;

import com.askeruzmani.asker_uzmani_backend.Entities.BanksEntity;
import com.askeruzmani.asker_uzmani_backend.Enums.StatusEnum;
import com.askeruzmani.asker_uzmani_backend.Repositories.BanksRepository;
import com.askeruzmani.asker_uzmani_backend.Services.BanksService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Transactional
@Rollback(value = false)
public class BanksServiceTest {

    @Autowired
    private BanksRepository banksRepository;

    @Autowired
    private BanksService banksService;

    private BanksEntity banksEntity;

    @Test
    void testSave() {
        BanksEntity banksEntity = new BanksEntity();
        banksEntity.setName("Ziraat Bankası");
        banksEntity.setLogoUrl("https://upload.wikimedia.org/wikipedia/commons/thumb/f/ff/Ziraat_Bankas%C4%B1_logo.svg/1024px-Ziraat_Bankas%C4%B1_logo.svg.png");
        BanksEntity saved = banksService.save(banksEntity);

        assertNotNull(saved.getId());
        assertEquals("Akbank", saved.getName());
    }
}
