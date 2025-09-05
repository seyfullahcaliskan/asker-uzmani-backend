package com.askeruzmani.asker_uzmani_backend.Services.Products;

import com.askeruzmani.asker_uzmani_backend.Entities.Products.SubProductsEntity;
import com.askeruzmani.asker_uzmani_backend.Repositories.Products.SubProductsRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SubProductsService {

    @Autowired
    private SubProductsRepository subProductsRepository;

    public List<SubProductsEntity> getAll() {
        return subProductsRepository.findAll();
    }

    public Optional<SubProductsEntity> getOne(UUID id) {
        return subProductsRepository.findById(id);
    }

    public SubProductsEntity save(SubProductsEntity subProduct) {
        return subProductsRepository.save(subProduct);
    }

    public SubProductsEntity update(UUID id, SubProductsEntity subProduct) {
        Optional<SubProductsEntity> existing = subProductsRepository.findById(id);
        if (existing.isPresent()) {
            SubProductsEntity toUpdate = existing.get();
            BeanUtils.copyProperties(subProduct, toUpdate, "id");
            return subProductsRepository.save(toUpdate);
        } else {
            throw new RuntimeException("Sub product not found with id " + id);
        }
    }

    public void delete(UUID id) {
        Optional<SubProductsEntity> existing = subProductsRepository.findById(id);
        if (existing.isPresent()) {
            SubProductsEntity toDelete = existing.get();
            toDelete.softDelete();
            subProductsRepository.save(toDelete);
        } else {
            throw new RuntimeException("Sub product not found with id " + id);
        }
    }
}
