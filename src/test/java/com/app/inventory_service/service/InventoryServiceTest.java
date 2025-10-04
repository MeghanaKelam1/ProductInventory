package com.app.inventory_service.service;

import com.app.inventory_service.exception.InvalidInventoryException;
import com.app.inventory_service.exception.InventoryNotFoundException;
import com.app.inventory_service.model.Inventory;
import com.app.inventory_service.repo.InventoryRepo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.verify;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class InventoryServiceTest {
    @Mock
    InventoryRepo inventoryRepo;
    @InjectMocks
    InventoryService inventoryService;

    @DisplayName("Should return all the inventory records when data exists")
    @Test
    void getAllStocks_shouldReturnListOfInventories_whenInventoryExists()
    {
        Inventory inventory1 = new Inventory(1001L, 150);
        Inventory inventory2 = new Inventory(1002L, 80);
        Inventory inventory3 = new Inventory(1003L, 120);
        Inventory inventory4 = new Inventory(1004L, 60);
        Inventory inventory5 = new Inventory(1005L, 40);
        Inventory inventory6 = new Inventory(1006L, 10);
        Inventory inventory7 = new Inventory(1007L, 80);
        Inventory inventory8 = new Inventory(1008L, 20);
        Inventory inventory9 = new Inventory(1009L, 2);

        given(inventoryRepo.findAll()).willReturn(List.of(inventory1,inventory2, inventory3,inventory4,
        inventory5,inventory6,inventory7,inventory8,inventory9));

        List<Inventory> inventoryList = inventoryService.getAllStock();

        assertThat(inventoryList).isNotNull();
        assertThat(inventoryList.size()).isEqualTo(9);//optional
        assertThat(inventoryList).containsExactly(inventory1,inventory2, inventory3,inventory4,
                inventory5,inventory6,inventory7,inventory8,inventory9);
        verify(inventoryRepo).findAll(); //optional
    }


    @DisplayName("Should throw Inventory not found exception when inventory list is empty")
    @Test
    void getAllStocks_shouldThrowException_whenInventoryNotExists(){
        given(inventoryRepo.findAll()).willReturn(List.of());
        assertThrows(InventoryNotFoundException.class, () -> inventoryService.getAllStock());
        verify(inventoryRepo).findAll();
    }

    @DisplayName("Should add inventory records")
    @Test
    void addStock_shouldAddInventory(){
        Inventory inventory = new Inventory(1001L,150);
        inventory.setId(1000L);
        inventoryService.addStock(inventory);
        verify(inventoryRepo).save(inventory);
    }

    @DisplayName("Should throw Invalid Inventory exception when adding invalid inventory")
    @Test
    void addStock_shouldThrowException_whenInvalidInventory(){
        Inventory invalidInventory = new Inventory(null,null);
        invalidInventory.setId(null);
        assertThrows(InvalidInventoryException.class, () -> inventoryService.addStock(invalidInventory));
    }
    @DisplayName("Should throw RuntimeException when repository fails to save inventory")
    @Test
    void addStock_shouldThrowException_whenRepositoryFails() {
        Inventory inventory = new Inventory(1001L, 150);
        inventory.setId(1000L);

        given(inventoryRepo.save(inventory)).willThrow(new RuntimeException("Database error"));

        assertThrows(RuntimeException.class, () -> inventoryService.addStock(inventory));
        verify(inventoryRepo).save(inventory);
    }

    @DisplayName("Should return count of the stock quantity when inventory is valid")
    @Test
    void getStockQuantity_shouldReturnStockQuantity(){
        Long productId = 1001L;
        Integer expectedValue = 150;

        given(inventoryRepo.getTotalStockByProductId(productId)).willReturn(expectedValue);
        Integer actualQuantity = inventoryService.getStockQuantity(productId);
        assertThat(actualQuantity).isEqualTo(expectedValue);
        verify(inventoryRepo).getTotalStockByProductId(productId);
    }
    @DisplayName("Should throw Invalid Inventory exception when product id is null")
    @Test
    void getStockQuantity_shouldThrowException_whenInvalidInventory(){
        assertThrows(InvalidInventoryException.class, () -> inventoryService.getStockQuantity(null));
    }
    @DisplayName("Should throw Invalid Inventory exception when count is null for given id ")
    @Test
    void getStockQuantity_shouldThrowException_whenCountIsNull(){
        Long productId = 1001L;
        given(inventoryRepo.getTotalStockByProductId(productId)).willReturn(null);
        assertThrows(InventoryNotFoundException.class,()->inventoryService.getStockQuantity(productId));
        verify(inventoryRepo).getTotalStockByProductId(productId);
    }
}

//testcase for checking the constraints on product size