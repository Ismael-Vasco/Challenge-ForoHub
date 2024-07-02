package com.aluracursos.challengeForo.models.topicos;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record DatosRespuestaTopico(
        Long id,
        String titulo,
        String mensajes,
        LocalDateTime fechaCreacion,
        LocalDateTime fechaActualizacion,
        Boolean status,
        Long idAutor,
        Long idCurso
) {
}
