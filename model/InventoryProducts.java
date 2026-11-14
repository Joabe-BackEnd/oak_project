package com.system.piratas.oak.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventoryProducts implements Serializable {
    private static final long serializableVersionID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDate inventoryProductsDate;
    private Integer inventoryProductsQuantity;

    @PrePersist
    public void prePersist(){
        inventoryProductsDate = LocalDate.now();
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "products_id")
    private Products products;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inventory_id,")
    private Inventory inventory;
}
