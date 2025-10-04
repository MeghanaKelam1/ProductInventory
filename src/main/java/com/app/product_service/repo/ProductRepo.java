package com.app.product_service.repo;

import com.app.product_service.dto.InventoryDTO;
import com.app.product_service.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepo extends JpaRepository<Product,Long> {
//    Optional<Product> findByName(String name);
//
//    InventoryDTO findByName(String productName);

    Optional<Product> findByProductName(String productName);

}
