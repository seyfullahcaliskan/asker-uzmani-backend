package com.askeruzmani.asker_uzmani_backend;

import com.askeruzmani.asker_uzmani_backend.Entities.Products.ProductEntity;
import com.askeruzmani.asker_uzmani_backend.Enums.YesNoEnum;
import com.askeruzmani.asker_uzmani_backend.Services.Products.ProductService;
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
public class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @Test
    void testSave() {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setCategory("Askerlik Setleri");
productEntity.setIsSet(YesNoEnum.YES);
productEntity.setName("Nano Dört Mevsim Askeri Çorap 12'li Set");
productEntity.setSlug("nano-dort-mevsim-askeri-corap-12-liset");
productEntity.setPrice(465);
productEntity.setCartPrice(350);
productEntity.setDescription("Nano Dört Mevsim Askeri Çorap 12'li Set");
productEntity.setMainImagePath("https://files.cdn-files-a.com/uploads/11063128/2000_68920b02babd4.jpg");
productEntity.setStock(999999);
        ProductEntity saved = productService.save(productEntity);

        assertNotNull(saved.getId());
        assertEquals("Nano Dört Mevsim Askeri Çorap 12'li Set", saved.getName());
    }
}
