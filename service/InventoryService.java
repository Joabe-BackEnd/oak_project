package com.system.piratas.oak.service;

import com.system.piratas.oak.builder.InventoryProductsBuilder;
import com.system.piratas.oak.dtos.InventoryProductsResponseDTO;
import com.system.piratas.oak.exceptions.InventarioInexistenteException;
import com.system.piratas.oak.mapper.Mapper;
import com.system.piratas.oak.model.Inventory;
import com.system.piratas.oak.model.InventoryProducts;
import com.system.piratas.oak.model.Products;
import com.system.piratas.oak.repository.InventoryProductsRepository;
import com.system.piratas.oak.repository.InventoryRepository;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class InventoryService {

    private InventoryRepository inventoryRepository;
    private Mapper mapper;
    private ProductsService  productsService;
    private InventoryProductsRepository inventoryProductsRepository;

    public InventoryService(InventoryRepository inventoryRepository, Mapper mapper, ProductsService productsService,  InventoryProductsRepository inventoryProductsRepository) {
        this.inventoryRepository = inventoryRepository;
        this.mapper = mapper;
        this.productsService = productsService;
        this.inventoryProductsRepository = inventoryProductsRepository;
    }

    //abrir inventario do dia
    public List<InventoryProductsResponseDTO> todayInventory() {
        if (inventoryRepository.findByInventoryDate(LocalDate.now()).isEmpty()) {

            Inventory i = new Inventory();
            inventoryRepository.save(i);


            for(Products products : productsService.getProductsList()){

                InventoryProducts forInsert = InventoryProductsBuilder
                        .createInventoryProductsBuilder()
                        .products(products)
                        .inventoryQuantity(0)
                        .inventory(inventoryRepository.findByInventoryDate(LocalDate.now()).get())
                        .build();
                inventoryProductsRepository.save(forInsert);
            }

            return inventoryProductsRepository.findByInventoryProductsDate(LocalDate.now()).get()
                    .stream()
                    .map(mapper::inventoryProductsToInventoryProductsResponseDTO).
                    toList();
        }

        return inventoryRepository.findByInventoryDate(LocalDate.now()).get().getProductsList()
                .stream()
                .map(mapper::inventoryProductsToInventoryProductsResponseDTO)
                .toList();
    }

    //exibir inventario da data requerida
    public List<InventoryProductsResponseDTO> dayInventory(LocalDate date) {
        if (inventoryRepository.findByInventoryDate(date).isEmpty()) {
            throw new InventarioInexistenteException("Não existe inventario da data requerida.");
        }
        return inventoryProductsRepository.findByInventoryProductsDate(date).get()
                .stream()
                .map(mapper::inventoryProductsToInventoryProductsResponseDTO)
                .toList();
    }


}
