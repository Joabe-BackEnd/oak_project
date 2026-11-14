package com.system.piratas.oak.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UsuarioRequestDTO(
        @NotBlank(message = "Por favor preencha o campo corretamente.")
        @NotNull(message = "Por favor preencha o campo corretamente.")
        String name,
        @NotBlank(message = "Por favor preencha o campo corretamente.")
        @NotNull(message = "Por favor preencha o campo corretamente.")
        String password
) {
}
