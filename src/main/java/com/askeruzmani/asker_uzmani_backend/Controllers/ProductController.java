package com.askeruzmani.asker_uzmani_backend.Controllers;

import com.askeruzmani.asker_uzmani_backend.Controllers.api.Paths;
import com.askeruzmani.asker_uzmani_backend.Entities.Products.ProductDetailDTO;
import com.askeruzmani.asker_uzmani_backend.Entities.Views.VmProductsEntity;
import com.askeruzmani.asker_uzmani_backend.Services.Products.MainProductService;
import com.askeruzmani.asker_uzmani_backend.Services.Views.VmProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(Paths.PRODUCTS)
public class ProductController {

    @Autowired
    private VmProductsService vmProductsService;

    @Autowired
    private MainProductService mainProductService;

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

    @PostMapping
    public ResponseEntity<Map<String, Object>> saveProduct(@RequestBody ProductDetailDTO productRequest) {
        Map<String, Object> response = new HashMap<>();
        try {
            mainProductService.saveNewProduct(productRequest);

            response.put("success", true);
            response.put("message", "Ürün başarıyla kaydedildi");

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Ürün kaydedilirken hata oluştu: " + e.getMessage());

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @DeleteMapping
    public ResponseEntity<Map<String, Object>> deleteProduct(@RequestBody UUID productId) {
        Map<String, Object> response = new HashMap<>();
        try {
            mainProductService.deleteProduct(productId);

            response.put("success", true);
            response.put("message", "Ürün başarıyla silindi.");

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Ürün silinirken hata oluştu: " + e.getMessage());

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PutMapping("/{productId}")
    public ResponseEntity<Map<String, Object>> updateProduct(
            @PathVariable UUID productId,
            @RequestBody ProductDetailDTO productRequest) {

        Map<String, Object> resp = new HashMap<>();
        try {
            mainProductService.updateProduct(productId, productRequest);
            resp.put("success", true);
            resp.put("message", "Ürün başarıyla güncellendi");
            return ResponseEntity.ok(resp);
        } catch (Exception e) {
            resp.put("success", false);
            resp.put("message", "Güncelleme hatası: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resp);
        }
    }

}