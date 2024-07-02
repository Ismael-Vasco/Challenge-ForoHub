package com.aluracursos.challengeForo.models.usuarios;

import jakarta.validation.constraints.NotNull;

public record DatosActualizarAutor(
        @NotNull
        Long id,
        String nombre,
        String correoElectronico) {
}
