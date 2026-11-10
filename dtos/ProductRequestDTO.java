package com.system.piratas.oak.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductRequestDTO(
        @NotBlank(message = "Nome do produto é obrigatório.")
        @NotNull(message = "Nome do produto é obrigatório.")
        String name,
        @NotBlank(message = "Descrição do produto é obrigatória.")
        @NotNull(message = "Descrição do produto é obrigatória.")
        String description
) {
}
