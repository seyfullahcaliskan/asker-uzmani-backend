package com.askeruzmani.asker_uzmani_backend;

import com.askeruzmani.asker_uzmani_backend.Entities.NavlinksEntity;
import com.askeruzmani.asker_uzmani_backend.Enums.StatusEnum;
import com.askeruzmani.asker_uzmani_backend.Repositories.NavlinksRepository;
import com.askeruzmani.asker_uzmani_backend.Services.NavlinksService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(value = false)  // test sonunda DB temizlenir
class NavlinksServiceIntegrationTest {

    @Autowired
    private NavlinksRepository navlinksRepository;

    @Autowired
    private NavlinksService navlinksService;

    private NavlinksEntity testEntity;



    @BeforeEach
    void setUp() {
        testEntity = new NavlinksEntity();
        testEntity.setLabel("Home");
        testEntity.setHref("/home");
        testEntity.setStatus(StatusEnum.ACTIVE);
        navlinksRepository.save(testEntity);  // test DB’ye başlangıç kaydı
    }

    @Test
    void testGetAll() {
        List<NavlinksEntity> allNavlinks = navlinksService.getAll();
        assertFalse(allNavlinks.isEmpty());
        assertTrue(allNavlinks.stream().anyMatch(n -> "Home".equals(n.getLabel())));
    }

    @Test
    void testGetOne() {
        UUID id = testEntity.getId();
        NavlinksEntity navlink = navlinksService.getOne(id).orElse(null);
        assertNotNull(navlink);
        assertEquals("Home", navlink.getLabel());
    }

    @Test
    void testSave() {
        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken("testUser", "password")
        );
        NavlinksEntity newEntity = new NavlinksEntity();
        newEntity.setLabel("Contact");
        newEntity.setHref("/contact");
        newEntity.setStatus(StatusEnum.ACTIVE);
        NavlinksEntity saved = navlinksService.save(newEntity);

        assertNotNull(saved.getId());
        assertEquals("Contact", saved.getLabel());
    }

    @Test
    void testUpdate() {
        testEntity.setLabel("Updated Home");
        NavlinksEntity updated = navlinksService.update(testEntity.getId(), testEntity);
        assertEquals("Updated Home", updated.getLabel());
    }

    @Test
    void testDelete() {
        navlinksService.delete(testEntity.getId());

        NavlinksEntity deleted = navlinksService.getOne(testEntity.getId()).orElse(null);
        assertNotNull(deleted);
        assertEquals(StatusEnum.CLOSED, deleted.getStatus());
    }

    @Test
    void testManualInsertAndFind() {
        NavlinksEntity navlink = new NavlinksEntity();
        navlink.setLabel("About");
        navlink.setHref("/about");
        navlink.setStatus(StatusEnum.ACTIVE);

        navlinksRepository.save(navlink);

        List<NavlinksEntity> all = navlinksService.getAll();
        assertTrue(all.stream().anyMatch(n -> "About".equals(n.getLabel())));
    }
}
