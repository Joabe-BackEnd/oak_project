package com.system.piratas.oak.service;

import com.system.piratas.oak.dtos.InventoryProductsRequestDTO;
import com.system.piratas.oak.dtos.InventoryProductsResponseDTO;
import com.system.piratas.oak.exceptions.ProdutoInexistenteException;
import com.system.piratas.oak.mapper.Mapper;
import com.system.piratas.oak.model.InventoryProducts;
import com.system.piratas.oak.model.Products;
import com.system.piratas.oak.repository.InventoryProductsRepository;
import com.system.piratas.oak.repository.ProductsRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class InventoryProductsService {

    private InventoryProductsRepository inventoryProductsRepository;
    private Mapper mapper;
    private ProductsRepository productsRepository;

    public InventoryProductsService(InventoryProductsRepository inventoryProductsRepository,
                                    Mapper mapper,
                                    ProductsRepository productsRepository) {
        this.inventoryProductsRepository = inventoryProductsRepository;
        this.mapper = mapper;
        this.productsRepository = productsRepository;
    }

    /*
    criar produto do inventario
    atualizar produto do inventario
    excluir produto do inventario (já está em Products)


    public InventoryProducts saveInventoryProduct(InventoryProductsRequestDTO requestDTO){
        if (productsRepository.findById(requestDTO.productId()).isEmpty()){
            throw new ProdutoInexistenteException("Produto inexistente.");
        } else if (!productsRepository.findById(requestDTO.productId()).get().getInStock().equals(true)) {
            throw new ProdutoInexistenteException("Produto fora de estoque");
        }
        return mapper.inventoryProductsRequestDTOToInventoryProducts(requestDTO);
    }
     */

    public InventoryProductsResponseDTO patchInventoryProducts(InventoryProductsRequestDTO requestDTO){
        if (productsRepository.findById(requestDTO.productId()).isEmpty()){
            throw new ProdutoInexistenteException("Produto inexistente.");
        } else if (!productsRepository.findById(requestDTO.productId()).get().getInStock().equals(true)) {
            throw new ProdutoInexistenteException("Produto fora de estoque");
        }
        InventoryProducts patch = inventoryProductsRepository.findByInventoryProductsDateAndProducts_Id(LocalDate.now(),requestDTO.productId()).get();
        patch.setInventoryProductsQuantity(requestDTO.quantity());
        inventoryProductsRepository.save(patch);
        return mapper.inventoryProductsToInventoryProductsResponseDTO(patch);
    }





}
