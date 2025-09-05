package com.askeruzmani.asker_uzmani_backend;

import com.askeruzmani.asker_uzmani_backend.Entities.BankAccountsEntity;
import com.askeruzmani.asker_uzmani_backend.Repositories.BankAccountsRepository;
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
public class BankAccountsService {

    @Autowired
    private BankAccountsRepository bankAccountsRepository;

    @Autowired
    private com.askeruzmani.asker_uzmani_backend.Services.BankAccountsService bankAccountsService;

    private BankAccountsEntity bankAccountsEntity;

    @Test
    void testSave() {
        BankAccountsEntity bankAccountsEntity = new BankAccountsEntity();
        bankAccountsEntity.setIban("TR860004600112888000189417");
        bankAccountsEntity.setDisplayIban("TR86 0004 6001 1288 8000 1894 17");
        bankAccountsEntity.setHolder("Talip HAN");
        bankAccountsEntity.setBankId(UUID.fromString("4f4413d8-b8e9-417d-a367-a95c2869da27"));
        BankAccountsEntity saved = bankAccountsService.save(bankAccountsEntity);

        assertNotNull(saved.getId());
        assertEquals("TR860004600112888000189417", saved.getIban());
    }
}
