package com.askeruzmani.asker_uzmani_backend.Controllers;

import com.askeruzmani.asker_uzmani_backend.Controllers.api.Paths;
import com.askeruzmani.asker_uzmani_backend.Entities.GeneralSettingsEntity;
import com.askeruzmani.asker_uzmani_backend.Services.GeneralSettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(Paths.GENERAL_SETTINGS)
public class GeneralSettingsController {

    @Autowired
    GeneralSettingsService generalSettingsService;

    @GetMapping
    public ResponseEntity<List<GeneralSettingsEntity>> getAll() {
        return ResponseEntity.ok(generalSettingsService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GeneralSettingsEntity> getOne(@PathVariable UUID id) {
        Optional<GeneralSettingsEntity> Bank = generalSettingsService.getOne(id);
        return Bank.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<GeneralSettingsEntity> create(@RequestBody GeneralSettingsEntity Bank) {
        return ResponseEntity.ok(generalSettingsService.save(Bank));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GeneralSettingsEntity> update(@PathVariable UUID id, @RequestBody GeneralSettingsEntity Bank) {
        return ResponseEntity.ok(generalSettingsService.update(id, Bank));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        generalSettingsService.delete(id);
        return ResponseEntity.noContent().build();
    }
    
}
