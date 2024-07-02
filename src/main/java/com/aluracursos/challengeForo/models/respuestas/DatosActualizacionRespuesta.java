package com.aluracursos.challengeForo.models.respuestas;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record DatosActualizacionRespuesta(
        @NotNull
        Long id,
        @NotBlank
        String solucion){
}