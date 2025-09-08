package com.askeruzmani.asker_uzmani_backend;

import com.askeruzmani.asker_uzmani_backend.Entities.Products.SubProductsEntity;
import com.askeruzmani.asker_uzmani_backend.Services.Products.SubProductsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Transactional
@Rollback(value = false)
public class SubProductServiceTest {
    
    @Autowired
    private SubProductsService subProductsService;

    @Test
    void testSave() {
        SubProductsEntity subProductsEntity = new SubProductsEntity();
        subProductsEntity.setMainProductId(UUID.fromString("00fe1a7e-747a-4bf4-a62b-b0dd7f94793d"));
        subProductsEntity.setProductId(UUID.fromString("00c4f782-ca94-4a43-853d-fa2952159ce9"));
        subProductsEntity.setCount(12);
        SubProductsEntity saved = subProductsService.save(subProductsEntity);
        assertNotNull(saved.getId());
        assertEquals(UUID.fromString("00c4f782-ca94-4a43-853d-fa2952159ce9"), saved.getProductId());
    }
}