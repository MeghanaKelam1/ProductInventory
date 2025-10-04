package com.app.inventory_service.repo;

import com.app.inventory_service.dto.AvailabilityDTO;
import com.app.inventory_service.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepo extends JpaRepository<Inventory,Long> {
//    @Query("SELECT SUM(i.quantity) FROM Inventory i WHERE i.productId = :productId")
//    Integer getTotalStockByProductId(@Param("productId") Long productId);

    @Query("SELECT SUM(i.quantity) FROM Inventory i WHERE i.productId = :productId")
    Integer getTotalStockByProductId(@Param("productId") Long productId);

    Inventory findByProductId(Long productId);

    @Query("SELECT new com.app.inventory_service.dto.AvailabilityDTO(i.quantity, a.store, a.deliveryTime) " +
            "FROM Inventory i JOIN i.availability a WHERE i.productId = :productId")
    AvailabilityDTO getAvailabilityDetails(@Param("productId") Long productId);
}
