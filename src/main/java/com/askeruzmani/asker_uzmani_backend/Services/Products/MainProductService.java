package com.askeruzmani.asker_uzmani_backend.Services.Products;

import com.askeruzmani.asker_uzmani_backend.Entities.Products.ProductImagesEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MainProductService {

    @Autowired
    ProductService productService;

    @Autowired
    ProductImagesService productImagesService;

    @Autowired
    ProductSizesService productSizesService;

    @Autowired
    SubProductsService subProductsService;

}
