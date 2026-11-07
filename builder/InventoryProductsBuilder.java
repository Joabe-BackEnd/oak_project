package com.system.piratas.oak.builder;

import com.system.piratas.oak.model.Inventory;
import com.system.piratas.oak.model.InventoryProducts;
import com.system.piratas.oak.model.Products;

import java.time.LocalDate;

public class InventoryProductsBuilder {

    private Integer id;
    private LocalDate inventoryDate;
    private Integer inventoryQuantity;
    private Products products;
    private Inventory Inventory;

    public static InventoryProductsBuilder createInventoryProductsBuilder() {
        return new InventoryProductsBuilder();
    }

    public InventoryProductsBuilder id(Integer id) {
        this.id = id;
        return this;
    }

    public InventoryProductsBuilder inventoryDate(LocalDate inventoryDate) {
        this.inventoryDate = inventoryDate;
        return this;
    }

    public InventoryProductsBuilder inventoryQuantity(Integer inventoryQuantity) {
        this.inventoryQuantity = inventoryQuantity;
        return this;
    }

    public InventoryProductsBuilder products(Products products) {
        this.products = products;
        return this;
    }

    public InventoryProductsBuilder inventory(Inventory inventory) {
        this.Inventory = inventory;
        return this;
    }

    public InventoryProducts build() {
        return new InventoryProducts(id,inventoryDate,inventoryQuantity,products,Inventory);
    }


}
