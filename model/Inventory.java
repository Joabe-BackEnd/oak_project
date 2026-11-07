package com.system.piratas.oak.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private LocalDate inventoryDate;
    @OneToMany(mappedBy = "inventory")
    private List<InventoryProducts> productsList;

    @PrePersist
    public void prePersist(){
        inventoryDate = LocalDate.now();
    }
}
