package com.system.piratas.oak.builder;

import com.system.piratas.oak.model.InventoryProducts;
import com.system.piratas.oak.model.Products;

import java.util.List;

public class ProductsBuilder {
    private Integer id;
    private String name;
    private String description;
    private Boolean inStock;
    private List<InventoryProducts>  inventoryProducts;

    public static ProductsBuilder createProductsBuilder() {
        return new ProductsBuilder();
    }

    public ProductsBuilder id(Integer id) {
        this.id = id;
        return this;
    }

    public ProductsBuilder name(String name) {
        this.name = name;
        return this;
    }

    public ProductsBuilder description(String description) {
        this.description = description;
        return this;
    }

    public ProductsBuilder inStock(Boolean inStock) {
        this.inStock = inStock;
        return this;
    }

    public ProductsBuilder inventoryProducts(List<InventoryProducts> inventoryProducts) {
        this.inventoryProducts = inventoryProducts;
        return this;
    }

    public Products build() {
        return new Products(id,name,description,inStock,inventoryProducts);
    }


}
