package com.askeruzmani.asker_uzmani_backend.Services.Views;

import com.askeruzmani.asker_uzmani_backend.Entities.Views.VmProductsEntity;
import com.askeruzmani.asker_uzmani_backend.Repositories.Views.VmProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class VmProductsService {

    @Autowired
    private VmProductsRepository vmProductsRepository;

    public List<VmProductsEntity> getAll() {
        return vmProductsRepository.findAll();
    }

    public Optional<VmProductsEntity> getOne(UUID id) {
        return vmProductsRepository.findById(id);
    }
}
