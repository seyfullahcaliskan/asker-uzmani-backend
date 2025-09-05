package com.askeruzmani.asker_uzmani_backend.Services.Products;

import com.askeruzmani.asker_uzmani_backend.Entities.Products.ProductEntity;
import com.askeruzmani.asker_uzmani_backend.Repositories.Products.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<ProductEntity> getAll() {
        return productRepository.findAll();
    }

    public Optional<ProductEntity> getOne(UUID id) {
        return productRepository.findById(id);
    }

    public ProductEntity save(ProductEntity product) {
        return productRepository.save(product);
    }

    public ProductEntity update(UUID id, ProductEntity product) {
        Optional<ProductEntity> existing = productRepository.findById(id);
        if (existing.isPresent()) {
            ProductEntity toUpdate = existing.get();
            BeanUtils.copyProperties(product, toUpdate, "id");
            return productRepository.save(toUpdate);
        } else {
            throw new RuntimeException("Product not found with id " + id);
        }
    }

    public void delete(UUID id) {
        Optional<ProductEntity> existing = productRepository.findById(id);
        if (existing.isPresent()) {
            ProductEntity toDelete = existing.get();
            toDelete.softDelete();
            productRepository.save(toDelete);
        } else {
            throw new RuntimeException("Product not found with id " + id);
        }
    }
}