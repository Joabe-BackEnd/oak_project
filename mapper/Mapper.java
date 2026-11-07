package com.system.piratas.oak.mapper;

import com.system.piratas.oak.builder.InventoryProductsBuilder;
import com.system.piratas.oak.builder.ProductsBuilder;
import com.system.piratas.oak.dtos.InventoryProductsResponseDTO;
import com.system.piratas.oak.dtos.InventoryProductsRequestDTO;
import com.system.piratas.oak.dtos.ProductRequestDTO;
import com.system.piratas.oak.dtos.ProductResponseDTO;
import com.system.piratas.oak.model.InventoryProducts;
import com.system.piratas.oak.model.Products;
import com.system.piratas.oak.repository.InventoryProductsRepository;
import com.system.piratas.oak.repository.ProductsRepository;
import org.springframework.stereotype.Component;

@Component
public class Mapper {

    private ProductsRepository  productsRepository;
    private InventoryProductsRepository inventoryProductsRepository;


    public Mapper(ProductsRepository productsRepository,
                  InventoryProductsRepository inventoryProductsRepository) {
        this.productsRepository = productsRepository;
        this.inventoryProductsRepository = inventoryProductsRepository;
    }

    public Products productsRequestToProducts(ProductRequestDTO productsDTO) {
        return ProductsBuilder
                .createProductsBuilder()
                .name(productsDTO.name())
                .description(productsDTO.description())
                .build();
    }

    public ProductResponseDTO  productsToProductResponseDTO(Products products) {
        return new ProductResponseDTO(products.getName(),products.getDescription());
    }

    public InventoryProducts inventoryProductsRequestDTOToInventoryProducts(InventoryProductsRequestDTO requestDTO){
        return InventoryProductsBuilder
                .createInventoryProductsBuilder()
                .inventoryQuantity(requestDTO.quantity())
                .products(productsRepository.findById(requestDTO.productId()).get())
                .build();
    }

    public InventoryProductsResponseDTO inventoryProductsToInventoryProductsResponseDTO(InventoryProducts inventoryProducts) {
        return new InventoryProductsResponseDTO(
                inventoryProducts.getProducts().getName(),
                inventoryProducts.getInventoryProductsQuantity()
        );
    }




}
