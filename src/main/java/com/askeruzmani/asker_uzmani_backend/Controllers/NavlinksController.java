package com.askeruzmani.asker_uzmani_backend.Controllers;

import com.askeruzmani.asker_uzmani_backend.Entities.NavlinksEntity;
import com.askeruzmani.asker_uzmani_backend.Services.NavlinksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/navlinks")
public class NavlinksController {

    @Autowired
    private NavlinksService navlinksService;

    @GetMapping
    public ResponseEntity<List<NavlinksEntity>> getAll() {
        return ResponseEntity.ok(navlinksService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<NavlinksEntity> getOne(@PathVariable UUID id) {
        Optional<NavlinksEntity> navlink = navlinksService.getOne(id);
        return navlink.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<NavlinksEntity> create(@RequestBody NavlinksEntity navlink) {
        return ResponseEntity.ok(navlinksService.save(navlink));
    }

    @PutMapping("/{id}")
    public ResponseEntity<NavlinksEntity> update(@PathVariable UUID id, @RequestBody NavlinksEntity navlink) {
        return ResponseEntity.ok(navlinksService.update(id, navlink));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        navlinksService.delete(id);
        return ResponseEntity.noContent().build();
    }
}