package com.system.piratas.oak.builder;

import com.system.piratas.oak.model.Inventory;
import com.system.piratas.oak.model.InventoryProducts;

import java.time.LocalDate;
import java.util.List;

public class InventoryBuilder {
    private Integer id;
    private LocalDate inventoryDate;
    private List<InventoryProducts> productsList;

    public static InventoryBuilder create() {
        return new InventoryBuilder();
    }

    public InventoryBuilder id(Integer id) {
        this.id = id;
        return this;
    }

    public InventoryBuilder inventoryDate(LocalDate inventoryDate) {
        this.inventoryDate = inventoryDate;
        return this;
    }

    public InventoryBuilder productsList(List<InventoryProducts> productsList) {
        this.productsList = productsList;
        return this;
    }

    public Inventory build() {
        return new Inventory(id, inventoryDate, productsList);
    }
}
