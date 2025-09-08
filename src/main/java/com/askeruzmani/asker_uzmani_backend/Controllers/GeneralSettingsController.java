package com.askeruzmani.asker_uzmani_backend.Controllers;

import com.askeruzmani.asker_uzmani_backend.Controllers.api.Paths;
import com.askeruzmani.asker_uzmani_backend.Entities.GeneralSettingsEntity;
import com.askeruzmani.asker_uzmani_backend.Services.GeneralSettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @PutMapping("/{id}")
    public ResponseEntity<GeneralSettingsEntity> update(@PathVariable UUID id, @RequestBody GeneralSettingsEntity Bank) {
        return ResponseEntity.ok(generalSettingsService.update(id, Bank));
    }
}
