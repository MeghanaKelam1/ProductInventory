package com.app.inventory_service.controller;

import com.app.inventory_service.dto.AvailabilityDTO;
import com.app.inventory_service.dto.InventoryDTO;
import com.app.inventory_service.model.Availability;
import com.app.inventory_service.model.Inventory;
import com.app.inventory_service.repo.AvailabilityRepo;
import com.app.inventory_service.repo.InventoryRepo;
import com.app.inventory_service.service.InventoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("inventory")
public class InventoryController {
    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @Autowired
    private InventoryRepo inventoryRepo;

    @Autowired
    private AvailabilityRepo availabilityRepo;

    //to get entire stock from the inventory
    @GetMapping("inventory")
    public ResponseEntity<List<Inventory>> getAllStock() {
        List<Inventory> stockList = inventoryService.getAllStock();
        return ResponseEntity.ok(stockList);
    }
    //adding a new stock to the inventory
    @PostMapping("inventory")
    public ResponseEntity<String> addStock(@RequestBody @Valid Inventory inventory) {
        inventoryService.addStock(inventory);
        return ResponseEntity.status(HttpStatus.CREATED).body("stock added succesfully");
    }
    //to get specific product id details
    @GetMapping("inventory/{productId}")
    public ResponseEntity<Integer> getStockQuantity(@PathVariable Long productId) {
        Integer count = inventoryService.getStockQuantity(productId);
        return ResponseEntity.ok(count);
    }

    @GetMapping("inventory/details/{productId}")
    public ResponseEntity<InventoryDTO> getInventory(@PathVariable Long productId) {
        Inventory inventory = inventoryRepo.findByProductId(productId);
        if (inventory == null) {
            return ResponseEntity.notFound().build();
        }
        InventoryDTO dto = new InventoryDTO(inventory.getProductId(), inventory.getQuantity());
        return ResponseEntity.ok(dto);
    }

    //to get the availability of a particular product
    //This is where joins concept is used
    //productId(Product) -> Availability
    @GetMapping("inventory/availability/{productId}/{requestedQuantity}")
    public ResponseEntity<AvailabilityDTO> getAvailability(
            @PathVariable Long productId,
            @PathVariable Integer requestedQuantity) {

        AvailabilityDTO dto = inventoryService.getAvailabilityDetails(productId, requestedQuantity);

        if (dto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null);
        }

        return ResponseEntity.ok(dto);
    }
}

