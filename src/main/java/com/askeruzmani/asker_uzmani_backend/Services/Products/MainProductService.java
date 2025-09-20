package com.askeruzmani.asker_uzmani_backend.Services.Products;

import com.askeruzmani.asker_uzmani_backend.Entities.Products.*;
import com.askeruzmani.asker_uzmani_backend.Enums.YesNoEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class MainProductService {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductImagesService productImagesService;

    @Autowired
    private ProductSizesService productSizesService;

    @Autowired
    private SubProductsService subProductsService;

    @Transactional
    public void saveNewProduct(ProductDetailDTO productRequest) {
        try {
            ProductEntity product = saveProduct(productRequest);
            saveProductImages(product.getId(), productRequest);
            saveProductSizes(product.getId(), productRequest);
            saveSubProducts(product.getId(), productRequest);
        } catch (Exception e) {
            throw new RuntimeException("Ürün kaydetme işlemi başarısız oldu", e);
        }
    }

    private ProductEntity saveProduct(ProductDetailDTO productRequest) {
        ProductEntity newProduct = new ProductEntity();
        newProduct.setName(productRequest.getName());
        newProduct.setSlug(productRequest.getSlug());
        newProduct.setCategory(productRequest.getCategory());
        newProduct.setPrice(productRequest.getPrice());
        newProduct.setCartPrice(productRequest.getCartPrice());
        newProduct.setDescription(productRequest.getDescription());
        newProduct.setIsSet(productRequest.getIsSet());
        newProduct.setMainImagePath(productRequest.getMainImagePath());
        newProduct.setStock(productRequest.getStock() != null ? productRequest.getStock() : 99999999);
        return productService.save(newProduct);
    }

    private void saveProductImages(UUID productId, ProductDetailDTO productRequest) {
        if (productRequest.getImages() == null || productRequest.getMainImagePath() == null) return;

        ProductImagesEntity mainImage = new ProductImagesEntity();
        mainImage.setProductId(productId);
        mainImage.setPath(productRequest.getMainImagePath());
        mainImage.setIsMainImage(YesNoEnum.YES);
        productImagesService.save(mainImage);

        for (ProductImagesEntity productImage : productRequest.getImages()) {
            ProductImagesEntity newProductImages = new ProductImagesEntity();
            newProductImages.setProductId(productId);
            newProductImages.setPath(productImage.getPath());
            newProductImages.setIsMainImage(productImage.getIsMainImage() != null ? productImage.getIsMainImage() : YesNoEnum.NO);
            productImagesService.save(newProductImages);
        }
    }

    private void saveProductSizes(UUID productId, ProductDetailDTO productRequest) {
        if (productRequest.getSizes() == null) return;

        for (ProductSizesEntity productSize : productRequest.getSizes()) {
            ProductSizesEntity newProductSize = new ProductSizesEntity();
            newProductSize.setProductId(productId);
            newProductSize.setValue(productSize.getValue());
            productSizesService.save(newProductSize);
        }
    }

    private void saveSubProducts(UUID productId, ProductDetailDTO productRequest) {
        if (productRequest.getSubProducts() == null) return;

        for (SubProductsEntity subProduct : productRequest.getSubProducts()) {
            SubProductsEntity newSubProduct = new SubProductsEntity();
            newSubProduct.setMainProductId(productId);
            newSubProduct.setProductId(subProduct.getProductId());
            newSubProduct.setCount(subProduct.getCount() != null ? subProduct.getCount() : 1);
            subProductsService.save(newSubProduct);
        }
    }

    @Transactional
    public void updateProduct(UUID productId, ProductDetailDTO dto) {
        ProductEntity existing = productService.getOne(productId)
                .orElseThrow(() -> new RuntimeException("Ürün bulunamadı: " + productId));

        existing.setName(dto.getName());
        existing.setSlug(dto.getSlug());
        existing.setCategory(dto.getCategory());
        existing.setPrice(dto.getPrice());
        existing.setCartPrice(dto.getCartPrice());
        existing.setDescription(dto.getDescription());
        existing.setIsSet(dto.getIsSet());
        existing.setMainImagePath(dto.getMainImagePath());
        existing.setStock(dto.getStock() != null ? dto.getStock() : 99999999);
        productService.save(existing);

        List<ProductImagesEntity> existingImages = productImagesService.getAllWithProductId(productId);
        Map<String, ProductImagesEntity> existingImagesMap = existingImages.stream()
                .collect(Collectors.toMap(ProductImagesEntity::getPath, img -> img));

        for (ProductImagesEntity img : dto.getImages()) {
            ProductImagesEntity exist = existingImagesMap.get(img.getPath());
            if (exist != null) {
                if (!Objects.equals(exist.getIsMainImage(), img.getIsMainImage())) {
                    exist.setIsMainImage(img.getIsMainImage()!= null ? img.getIsMainImage() : YesNoEnum.NO);
                    productImagesService.save(exist);
                }
                existingImagesMap.remove(img.getPath());
            } else {
                ProductImagesEntity newImg = new ProductImagesEntity();
                newImg.setProductId(productId);
                newImg.setPath(img.getPath());
                newImg.setIsMainImage(img.getIsMainImage() != null ? img.getIsMainImage() : YesNoEnum.NO);
                productImagesService.save(newImg);
            }
        }
        for (ProductImagesEntity imgToDelete : existingImagesMap.values()) {
            productImagesService.delete(imgToDelete.getId());
        }

        List<ProductSizesEntity> existingSizes = productSizesService.getAllWithProductId(productId);
        Map<String, ProductSizesEntity> existingSizesMap = existingSizes.stream()
                .collect(Collectors.toMap(ProductSizesEntity::getValue, s -> s));

        for (ProductSizesEntity size : dto.getSizes()) {
            ProductSizesEntity exist = existingSizesMap.get(size.getValue());
            if (exist != null) {
                existingSizesMap.remove(size.getValue());
            } else {
                ProductSizesEntity newSize = new ProductSizesEntity();
                newSize.setProductId(productId);
                newSize.setValue(size.getValue());
                productSizesService.save(newSize);
            }
        }
        for (ProductSizesEntity sToDelete : existingSizesMap.values()) {
            productSizesService.delete(sToDelete.getId());
        }

        List<SubProductsEntity> existingSubs = subProductsService.getAllWithMainProductId(productId);
        Map<UUID, SubProductsEntity> existingSubsMap = existingSubs.stream()
                .collect(Collectors.toMap(SubProductsEntity::getProductId, sp -> sp));

        for (SubProductsEntity sub : dto.getSubProducts()) {
            SubProductsEntity exist = existingSubsMap.get(sub.getProductId());
            if (exist != null) {
                if (!Objects.equals(exist.getCount(), sub.getCount())) {
                    exist.setCount(sub.getCount());
                    subProductsService.save(exist);
                }
                existingSubsMap.remove(sub.getProductId());
            } else {
                SubProductsEntity add = new SubProductsEntity();
                add.setMainProductId(productId);
                add.setProductId(sub.getProductId());
                add.setCount(sub.getCount() != null ? sub.getCount() : 1);
                subProductsService.save(add);
            }
        }
        for (SubProductsEntity spToDelete : existingSubsMap.values()) {
            subProductsService.delete(spToDelete.getId());
        }
    }

    public void deleteProduct(UUID productId) {
        productService.delete(productId);
        productImagesService.deleteWithProductId(productId);
        productSizesService.deleteWithProductId(productId);
        subProductsService.deleteWithMainProductId(productId);
    }


}
