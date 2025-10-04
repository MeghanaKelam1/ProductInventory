package com.app.product_service.controller;

import com.app.product_service.Feign.InventoryClient;
import com.app.product_service.dto.AvailabilityDTO;
import com.app.product_service.model.DeliveryInfo;
import com.app.product_service.model.Product;
import com.app.product_service.model.ProductResponse;
import com.app.product_service.repo.ProductRepo;
import com.app.product_service.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    ProductService productService;
    @Autowired
    private InventoryClient inventoryClient;
    @Autowired
    private ProductRepo productRepo;

    @GetMapping("products")
    public ResponseEntity<List<Product>> getAllProducts(){
        List<Product> productList = productService.getAllProducts();
        return ResponseEntity.ok(productList);
    }

    @GetMapping("products/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable Long id){
       ProductResponse response =  productService.getProductById(id);
       return ResponseEntity.ok(response);
    }

    @PostMapping("products")
    public ResponseEntity<String> addProduct( @Valid  @RequestBody Product product ){
      productService.addProduct(product);
      return ResponseEntity.status(HttpStatus.CREATED).body("Stock added succesfully");
    }

    @GetMapping("products/delivery/{productName}/{requestedQuantity}")
    public ResponseEntity<AvailabilityDTO> getDeliveryDetails(
            @PathVariable String productName,
            @PathVariable Integer requestedQuantity) {

        // Lookup productId from productName
        Long productId = productRepo.findByProductName(productName)
                .orElseThrow(() -> new RuntimeException("Product not found"))
                .getId();
        // Call inventory service
        AvailabilityDTO dto = inventoryClient.getAvailabilityDetails(productId, requestedQuantity).getBody();

        return ResponseEntity.ok(dto);
    }



}
