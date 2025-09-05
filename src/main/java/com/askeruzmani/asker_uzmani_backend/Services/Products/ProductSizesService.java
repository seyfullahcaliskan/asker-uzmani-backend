package com.askeruzmani.asker_uzmani_backend.Services.Products;

import com.askeruzmani.asker_uzmani_backend.Entities.Products.ProductSizesEntity;
import com.askeruzmani.asker_uzmani_backend.Repositories.Products.ProductSizesRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductSizesService {

    @Autowired
    private ProductSizesRepository productSizesRepository;

    public List<ProductSizesEntity> getAll() {
        return productSizesRepository.findAll();
    }

    public Optional<ProductSizesEntity> getOne(UUID id) {
        return productSizesRepository.findById(id);
    }

    public ProductSizesEntity save(ProductSizesEntity productSize) {
        return productSizesRepository.save(productSize);
    }

    public ProductSizesEntity update(UUID id, ProductSizesEntity productSize) {
        Optional<ProductSizesEntity> existing = productSizesRepository.findById(id);
        if (existing.isPresent()) {
            ProductSizesEntity toUpdate = existing.get();
            BeanUtils.copyProperties(productSize, toUpdate, "id");
            return productSizesRepository.save(toUpdate);
        } else {
            throw new RuntimeException("Product size not found with id " + id);
        }
    }

    public void delete(UUID id) {
        Optional<ProductSizesEntity> existing = productSizesRepository.findById(id);
        if (existing.isPresent()) {
            ProductSizesEntity toDelete = existing.get();
            toDelete.softDelete();
            productSizesRepository.save(toDelete);
        } else {
            throw new RuntimeException("Product size not found with id " + id);
        }
    }
}
