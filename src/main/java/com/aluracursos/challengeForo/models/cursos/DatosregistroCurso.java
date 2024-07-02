package com.aluracursos.challengeForo.models.cursos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record DatosregistroCurso(

        @NotBlank
        String nombre,
        @NotNull
        Categoria categoria){
}
