package com.app.inventory_service.service;

import com.app.inventory_service.dto.AvailabilityDTO;
import com.app.inventory_service.repo.InventoryRepo;
import com.app.inventory_service.exception.InvalidInventoryException;
import com.app.inventory_service.exception.InventoryNotFoundException;
import com.app.inventory_service.model.Inventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class InventoryService {

    @Autowired
    InventoryRepo inventoryRepo;

    public void addStock(Inventory inventory) {
        if (inventory == null || inventory.getId() == null || inventory.getProductId() == null || inventory.getQuantity() == null) {
            throw new InvalidInventoryException("Inventory data is invalid or incomplete");
        }
        inventoryRepo.save(inventory);

    }

    public Integer getStockQuantity(Long productId) {
        if (productId == null) {
            throw new InvalidInventoryException("Product id can't be null");
        }
        Integer count = inventoryRepo.getTotalStockByProductId(productId);
        if (count == null) {
            throw new InventoryNotFoundException("No stock found for the product id " + productId);
        }
        return count;
    }

    public List<Inventory> getAllStock() {
        List<Inventory> stockList = inventoryRepo.findAll();
        if (stockList.isEmpty()) {
            throw new InventoryNotFoundException("No inventory records found");
        }
        return stockList;
    }

    public AvailabilityDTO getAvailabilityDetails(Long productId, Integer requestedQuantity) {
        AvailabilityDTO dto = inventoryRepo.getAvailabilityDetails(productId);

        if (dto == null) {
            throw new InventoryNotFoundException("No availability data found for product id " + productId);
        }

        if (dto.getAvailableQuantity() < requestedQuantity) {
            throw new RuntimeException("Insufficient stock");
        }

        return dto;
    }

}
