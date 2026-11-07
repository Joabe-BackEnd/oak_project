package com.system.piratas.oak.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import java.util.List;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 100, nullable = false)
    private String name;
    @Column(length = 100)
    private String description;
    @Column(name = "inStock")
    private Boolean inStock;

    @OneToMany(mappedBy = "products")
    private List<InventoryProducts> inventoryProducts;

    @PrePersist
    public void prePersist(){
        inStock = true;
    }

}
