package com.aluracursos.challengeForo.models.usuarios;

import jakarta.validation.constraints.*;


public record DatosregistroAutor(

        @NotBlank
        String nombre,
        @NotBlank
        @Email
        String correoElectronico,
        @NotBlank
        String contraseña){
}
