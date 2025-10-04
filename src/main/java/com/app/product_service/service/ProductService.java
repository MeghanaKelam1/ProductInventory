package com.app.product_service.service;

import com.app.product_service.Feign.InventoryClient;
import com.app.product_service.dto.AvailabilityDTO;
import com.app.product_service.dto.InventoryDTO;
import com.app.product_service.repo.ProductRepo;
import com.app.product_service.exception.InvalidProductException;
import com.app.product_service.exception.ProductNotFoundException;
import com.app.product_service.model.DeliveryInfo;
import com.app.product_service.model.Product;
import com.app.product_service.model.ProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepo productRepo;

    @Autowired
    InventoryClient inventoryClient;

    //to get all the products
    public List<Product> getAllProducts() {
        List<Product> productList = productRepo.findAll();
        if (productList.isEmpty()) {
            throw new ProductNotFoundException("No product records found");
        }
        return productList;
    }

    //to add a new product
    public void addProduct(Product product) {
        if (product.getProductName() == null || product.getPrice() <= 0 || product.getDescription() == null) {
            throw new InvalidProductException("Product data is invalid or incomplete");
        }
        productRepo.save(product);

    }

    //to get a product via id
    public ProductResponse getProductById(Long id) {

        Product product = productRepo.findById(id).orElseThrow(() -> new ProductNotFoundException("product with id: " + id + " is not found"));
        Integer stockQuantity = inventoryClient.getStockQuantity(product.getId()).getBody();
        if (stockQuantity == null) {
            throw new IllegalArgumentException("Stock quantity response is null for product ID " + product.getId());
        }

        ProductResponse productResponse = new ProductResponse(
                product.getId(),
                product.getProductName(),
                product.getDescription(),
                product.getPrice(),
                stockQuantity
        );


        return productResponse;
    }
    //to get delivery details from the availability repo
    public DeliveryInfo getDeliveryDetails(String name, Integer quantity) {
        Product product = productRepo.findByProductName(name).orElseThrow(() -> new RuntimeException("Product not found"));

        InventoryDTO inventory = inventoryClient.getInventoryDetails(product.getId()).getBody();
        AvailabilityDTO availability = inventoryClient.getAvailability(product.getId()).getBody();

        if (inventory.getQuantity() < quantity) {
            throw new RuntimeException("Insufficient stock");
        }

        return new DeliveryInfo(
                product.getProductName(),
                quantity,
                inventory.getQuantity(),
                availability.getStoreType(),
                availability.getDeliveryTime()
        );
    }
}
