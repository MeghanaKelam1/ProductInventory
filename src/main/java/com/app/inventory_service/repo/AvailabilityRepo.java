package com.app.inventory_service.repo;

import com.app.inventory_service.model.Availability;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvailabilityRepo extends JpaRepository<Availability,Integer> {
    Availability findByAvailabilityId(Long availabilityId);
}
