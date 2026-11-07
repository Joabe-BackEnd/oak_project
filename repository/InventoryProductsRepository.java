package com.system.piratas.oak.repository;

import com.system.piratas.oak.model.InventoryProducts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface InventoryProductsRepository extends JpaRepository<InventoryProducts, Integer> {
    Optional<List<InventoryProducts>> findByInventoryProductsDate(LocalDate inventoryProductsDate);
    Optional<InventoryProducts> findByInventoryProductsDateAndProducts_Id(
            LocalDate date,
            Integer id
    );
}
