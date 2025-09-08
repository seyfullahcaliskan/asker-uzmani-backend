package com.askeruzmani.asker_uzmani_backend.Controllers;

import com.askeruzmani.asker_uzmani_backend.Controllers.api.Paths;
import com.askeruzmani.asker_uzmani_backend.Entities.Views.VmProductsEntity;
import com.askeruzmani.asker_uzmani_backend.Services.Views.VmProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(Paths.PRODUCTS)
public class ProductController {

    @Autowired
    private VmProductsService vmProductsService;

    @GetMapping
    public ResponseEntity<List<VmProductsEntity>> getAll() {
        return ResponseEntity.ok(vmProductsService.getAll());
    }

        @GetMapping("/{id}")
    public ResponseEntity<VmProductsEntity> getOne(@PathVariable UUID id) {
        Optional<VmProductsEntity> product = vmProductsService.getOne(id);
        return product.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}