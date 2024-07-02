package com.aluracursos.challengeForo.models.usuarios;

public record DatosListadoAutor(Long id,
                                String nombre,
                                String correoElectronico) {


    public DatosListadoAutor(Autor autor) {
        this(autor.getId(),
                autor.getNombre(),
                autor.getCorreoElectronico());
    }
}


