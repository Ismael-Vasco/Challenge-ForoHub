package com.aluracursos.challengeForo.models.topicos;

import com.aluracursos.challengeForo.models.cursos.Curso;
import com.aluracursos.challengeForo.models.usuarios.Autor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record DatosregistroTopico(

        @NotBlank
        String titulo,
        @NotBlank
        String mensajes,
        @NotNull
        Long idAutor,
        @NotNull
        Long idCurso){
}