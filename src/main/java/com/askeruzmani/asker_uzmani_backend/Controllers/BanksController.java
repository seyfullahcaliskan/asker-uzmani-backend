package com.askeruzmani.asker_uzmani_backend.Controllers;

import com.askeruzmani.asker_uzmani_backend.Controllers.api.Paths;
import com.askeruzmani.asker_uzmani_backend.Entities.BanksEntity;
import com.askeruzmani.asker_uzmani_backend.Services.BanksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(Paths.BANKS)
public class BanksController {

    @Autowired
    private BanksService banksService;

    @GetMapping
    public ResponseEntity<List<BanksEntity>> getAll() {
        return ResponseEntity.ok(banksService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BanksEntity> getOne(@PathVariable UUID id) {
        Optional<BanksEntity> Bank = banksService.getOne(id);
        return Bank.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<BanksEntity> create(@RequestBody BanksEntity Bank) {
        return ResponseEntity.ok(banksService.save(Bank));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BanksEntity> update(@PathVariable UUID id, @RequestBody BanksEntity Bank) {
        return ResponseEntity.ok(banksService.update(id, Bank));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        banksService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
