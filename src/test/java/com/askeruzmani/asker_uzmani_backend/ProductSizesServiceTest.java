package com.askeruzmani.asker_uzmani_backend;

import com.askeruzmani.asker_uzmani_backend.Entities.Products.ProductSizesEntity;
import com.askeruzmani.asker_uzmani_backend.Enums.YesNoEnum;
import com.askeruzmani.asker_uzmani_backend.Services.Products.ProductImagesService;
import com.askeruzmani.asker_uzmani_backend.Services.Products.ProductSizesService;
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
public class ProductSizesServiceTest {

    @Autowired
    private ProductSizesService productSizesService;

    @Test
    void testSave() {
        ProductSizesEntity productSizesEntity = new ProductSizesEntity();
        productSizesEntity.setProductId(UUID.fromString("00c4f782-ca94-4a43-853d-fa2952159ce9"));
        productSizesEntity.setValue("42");
        ProductSizesEntity saved = productSizesService.save(productSizesEntity);
        assertNotNull(saved.getId());
        assertEquals(UUID.fromString("00c4f782-ca94-4a43-853d-fa2952159ce9"), saved.getProductId());
    }
}
