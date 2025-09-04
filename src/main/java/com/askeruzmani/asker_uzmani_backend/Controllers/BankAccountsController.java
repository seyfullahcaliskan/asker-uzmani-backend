package com.askeruzmani.asker_uzmani_backend.Controllers;

import com.askeruzmani.asker_uzmani_backend.Controllers.api.Paths;
import com.askeruzmani.asker_uzmani_backend.Entities.BankAccountsEntity;
import com.askeruzmani.asker_uzmani_backend.Services.BankAccountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(Paths.BANK_ACCOUNTS)
public class BankAccountsController {

    @Autowired
    private BankAccountsService bankAccountsService;

    @GetMapping
    public ResponseEntity<List<BankAccountsEntity>> getAll() {
        return ResponseEntity.ok(bankAccountsService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BankAccountsEntity> getOne(@PathVariable UUID id) {
        Optional<BankAccountsEntity> BankAccount = bankAccountsService.getOne(id);
        return BankAccount.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<BankAccountsEntity> create(@RequestBody BankAccountsEntity BankAccount) {
        return ResponseEntity.ok(bankAccountsService.save(BankAccount));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BankAccountsEntity> update(@PathVariable UUID id, @RequestBody BankAccountsEntity BankAccount) {
        return ResponseEntity.ok(bankAccountsService.update(id, BankAccount));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        bankAccountsService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
