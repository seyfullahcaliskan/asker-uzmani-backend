package com.askeruzmani.asker_uzmani_backend;

import com.askeruzmani.asker_uzmani_backend.Entities.Products.ProductImagesEntity;
import com.askeruzmani.asker_uzmani_backend.Enums.YesNoEnum;
import com.askeruzmani.asker_uzmani_backend.Services.Products.ProductImagesService;
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
public class ProductImagesServiceTest {
    
    @Autowired
   private ProductImagesService productImagesService;

    @Test
    void testSave() {
        ProductImagesEntity productImagesEntity = new ProductImagesEntity();
        productImagesEntity.setProductId(UUID.fromString("00fe1a7e-747a-4bf4-a62b-b0dd7f94793d"));
        productImagesEntity.setPath("https://files.cdn-files-a.com/uploads/11063128/2000_68920b02babd4.jpg");
        productImagesEntity.setIsMainImage(YesNoEnum.YES);
        ProductImagesEntity saved = productImagesService.save(productImagesEntity);
        assertNotNull(saved.getId());
        assertEquals(UUID.fromString("00fe1a7e-747a-4bf4-a62b-b0dd7f94793d"), saved.getProductId());
    }
}
