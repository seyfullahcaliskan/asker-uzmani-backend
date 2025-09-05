package com.askeruzmani.asker_uzmani_backend.Services.Products;

import com.askeruzmani.asker_uzmani_backend.Entities.Products.ProductImagesEntity;
import com.askeruzmani.asker_uzmani_backend.Repositories.Products.ProductImagesRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductImagesService {

    @Autowired
    private ProductImagesRepository productImagesRepository;
    
    public List<ProductImagesEntity> getAll() {
        return productImagesRepository.findAll();
    }

    public Optional<ProductImagesEntity> getOne(UUID id) {
        return productImagesRepository.findById(id);
    }

    public ProductImagesEntity save(ProductImagesEntity productImage) {
        return productImagesRepository.save(productImage);
    }

    public ProductImagesEntity update(UUID id, ProductImagesEntity productImage) {
        Optional<ProductImagesEntity> existing = productImagesRepository.findById(id);
        if (existing.isPresent()) {
            ProductImagesEntity toUpdate = existing.get();
            BeanUtils.copyProperties(productImage, toUpdate, "id");
            return productImagesRepository.save(toUpdate);
        } else {
            throw new RuntimeException("Product image not found with id " + id);
        }
    }

    public void delete(UUID id) {
        Optional<ProductImagesEntity> existing = productImagesRepository.findById(id);
        if (existing.isPresent()) {
            ProductImagesEntity toDelete = existing.get();
            toDelete.softDelete();
            productImagesRepository.save(toDelete);
        } else {
            throw new RuntimeException("Product image not found with id " + id);
        }
    }
}