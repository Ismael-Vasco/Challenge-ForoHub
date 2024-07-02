package com.aluracursos.challengeForo.models.cursos;



public record DatosListadoCurso(Long id,
                                String nombre,
                                Categoria categoria) {


    public DatosListadoCurso(Curso curso) {
        this(curso.getId(),
                curso.getNombre(),
                curso.getCategoria());
    }
}


