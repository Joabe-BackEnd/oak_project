package com.system.piratas.oak.repository;

import com.system.piratas.oak.model.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductsRepository extends JpaRepository<Products, Integer> {

    Optional<Products> findByName(String name);
    Optional<Products> findByNameIgnoreCase(String name);
}
