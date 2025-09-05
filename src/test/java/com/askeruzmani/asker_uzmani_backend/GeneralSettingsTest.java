package com.askeruzmani.asker_uzmani_backend;

import com.askeruzmani.asker_uzmani_backend.Entities.GeneralSettingsEntity;
import com.askeruzmani.asker_uzmani_backend.Enums.StatusEnum;
import com.askeruzmani.asker_uzmani_backend.Enums.YesNoEnum;
import com.askeruzmani.asker_uzmani_backend.Repositories.GeneralSettingsRepository;
import com.askeruzmani.asker_uzmani_backend.Services.GeneralSettingsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Transactional
@Rollback(value = false)  // test sonunda DB temizlenir
public class GeneralSettingsTest {

    @Autowired
    private GeneralSettingsRepository generalSettingsRepository;

    @Autowired
    private GeneralSettingsService generalSettingsService;

    private GeneralSettingsEntity generalSettingsEntity;

    @Test
    void testSave() {
        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken("testUser", "password")
        );
        GeneralSettingsEntity newEntity = new GeneralSettingsEntity();
        newEntity.setFreeCargo(YesNoEnum.NO);
        newEntity.setFreeCargoPrice(3000);
        newEntity.setCargoPrice(100);
        newEntity.setOnlinePayment(YesNoEnum.NO);
        GeneralSettingsEntity saved = generalSettingsService.save(newEntity);

        assertNotNull(saved.getId());
        assertEquals(3000, saved.getCargoPrice());
    }
}
