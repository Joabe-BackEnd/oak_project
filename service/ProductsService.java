package com.system.piratas.oak.service;

import com.system.piratas.oak.builder.InventoryProductsBuilder;
import com.system.piratas.oak.dtos.ProductRequestDTO;
import com.system.piratas.oak.dtos.ProductResponseDTO;
import com.system.piratas.oak.exceptions.ListaDeProdutosVaziaException;
import com.system.piratas.oak.exceptions.ProdutoInexistenteException;
import com.system.piratas.oak.exceptions.ProdutoRepetidoException;
import com.system.piratas.oak.mapper.Mapper;
import com.system.piratas.oak.model.InventoryProducts;
import com.system.piratas.oak.model.Products;
import com.system.piratas.oak.repository.InventoryProductsRepository;
import com.system.piratas.oak.repository.InventoryRepository;
import com.system.piratas.oak.repository.ProductsRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ProductsService {

    private final ProductsRepository productsRepository;
    private final Mapper mapper;
    private final InventoryRepository inventoryRepository;
    private final InventoryProductsRepository inventoryProductsRepository;

    public ProductsService(ProductsRepository productsRepository,
                           Mapper mapper,
                           InventoryRepository inventoryRepository,
                           InventoryProductsRepository inventoryProductsRepository) {
        this.productsRepository = productsRepository;
        this.mapper = mapper;
        this.inventoryRepository = inventoryRepository;
        this.inventoryProductsRepository = inventoryProductsRepository;
    }

    //salvarProduto
    //excluirProduto(sem apagar os registros anteriores)
    //listaDeProdutos

    public ProductResponseDTO saveProducts(ProductRequestDTO productsDTO) {
        if (productsRepository.findByNameIgnoreCase(productsDTO.name()).isPresent()) {
            throw new ProdutoRepetidoException("Este produto já está cadastrado");
        }
        if (inventoryRepository.findByInventoryDate(LocalDate.now()).isPresent()){
            Products p = productsRepository.save(mapper.productsRequestToProducts(productsDTO));

            inventoryProductsRepository.save(InventoryProductsBuilder.createInventoryProductsBuilder()
                    .inventory(inventoryRepository.findByInventoryDate(LocalDate.now()).get())
                    .inventoryDate(LocalDate.now())
                    .products(p)
                    .inventoryQuantity(0)
                    .build()
            );
            return mapper.productsToProductResponseDTO(p);
        }
        return mapper.productsToProductResponseDTO(productsRepository.save(mapper.productsRequestToProducts(productsDTO)));
    }

    public List<Products> getProductsList() {
        if (productsRepository.findAll().isEmpty()) {
            throw new ListaDeProdutosVaziaException("Lista de produtos vazia");
        }
        return productsRepository.findAll().stream().filter(p -> p.getInStock().equals(true)).toList();
    }

    public ProductResponseDTO getProductById(Integer id){
        if (productsRepository.findById(id).isEmpty()){
            throw new ProdutoInexistenteException("Produto inexistente.");
        }
        return mapper.productsToProductResponseDTO(productsRepository.findById(id).get());
    }

    public void deleteProductsById(Integer id) {
        if (productsRepository.findById(id).isEmpty()){
            throw new ProdutoInexistenteException("Produto inexistente.");
        }
        Products p = productsRepository.findById(id).get();
        p.setInStock(false);
        productsRepository.save(p);
    }



}
