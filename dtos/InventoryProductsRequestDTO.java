package com.system.piratas.oak.dtos;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record InventoryProductsRequestDTO(
        @NotNull(message = "Nome do produto é obrigatório.")
        Integer productId,
        @NotNull(message = "Quantidade do produto é obrigatória.")
        @Min(value = 0,message = "Quantidade não pode ser menor que 0.")
        Integer quantity
) {
}
