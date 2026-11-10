package com.system.piratas.oak.controller;

import com.system.piratas.oak.dtos.InventoryProductsRequestDTO;
import com.system.piratas.oak.dtos.InventoryProductsResponseDTO;
import com.system.piratas.oak.dtos.ProductRequestDTO;
import com.system.piratas.oak.dtos.ProductResponseDTO;
import com.system.piratas.oak.mapper.Mapper;
import com.system.piratas.oak.model.InventoryProducts;
import com.system.piratas.oak.service.InventoryProductsService;
import com.system.piratas.oak.service.InventoryService;
import com.system.piratas.oak.service.ProductsService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/inventario")
public class InventoryController {

    private ProductsService  productsService;
    private InventoryService inventoryService;
    private InventoryProductsService inventoryProductsService;
    private Mapper mapper;

    public InventoryController(ProductsService productsService
            , InventoryService inventoryService
            , InventoryProductsService inventoryProductsService
            ,  Mapper mapper) {
        this.productsService = productsService;
        this.inventoryService = inventoryService;
        this.inventoryProductsService = inventoryProductsService;
        this.mapper = mapper;
    }

    /*
    saveProduct
    saveInventoryProduct
    DeleteItem

     */
    @PostMapping("/salvar/produto")
    public ResponseEntity<ProductResponseDTO> saveProduct(@Valid @RequestBody ProductRequestDTO productRequestDTO){

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(productsService.saveProducts(productRequestDTO));
    }

    @PatchMapping("/atualizar/produto")
    public ResponseEntity<InventoryProductsResponseDTO> updateProduct(@Valid @RequestBody InventoryProductsRequestDTO dto){
        return ResponseEntity.status(HttpStatus.OK).body(inventoryProductsService.patchInventoryProducts(dto));
    }

    @GetMapping("/hoje")
    public ResponseEntity<List<InventoryProductsResponseDTO>> productsList(){
        return ResponseEntity.status(HttpStatus.OK).body(inventoryService.todayInventory());
    }

    @GetMapping("/produtos")
    public ResponseEntity<List<ProductResponseDTO>> getProductsList(){
        return ResponseEntity.status(HttpStatus.OK).body(
                productsService.getProductsList().stream()
                    .map(mapper::productsToProductResponseDTO)
                    .toList()
        );
    }

    @GetMapping("/buscar/inventario/{date}")
    public ResponseEntity<List<InventoryProductsResponseDTO>> inventoryByDate(@PathVariable LocalDate date){
        return ResponseEntity.status(HttpStatus.OK).body(
                inventoryService.dayInventory(date)
        );
    }

    @DeleteMapping("deletar/produto/{id}")
    public ResponseEntity<String> deleteProductById(@PathVariable Integer id){
        String response = productsService.getProductById(id).name();
        productsService.deleteProductsById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("O produto "+ response +" foi deletado");
    }



    


}
