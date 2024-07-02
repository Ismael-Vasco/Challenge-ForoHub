package com.aluracursos.challengeForo.models.respuestas;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;


public record DatosRegistroRespuesta(
        @NotNull
        Long idTopico,
        @NotNull
        Long autorRespuesta,
        @NotBlank
        String solucion){
}