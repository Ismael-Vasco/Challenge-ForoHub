package com.aluracursos.challengeForo.models.respuestas;


import com.aluracursos.challengeForo.models.topicos.Topico;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record DatosDetalleRespuesta(
        Long id,
        Long topico_id,
        String titulo,
        String mensajes,
        LocalDateTime fechaCreacion,
        String getAutorPregunta,
        String getAutorRespuesta,
        LocalDateTime fechaRespuesta,
        String solucion) {

    public DatosDetalleRespuesta(Respuesta respuesta){
        this(respuesta.getId(),
                respuesta.getIdTopico().getId(),
                respuesta.getTitulo(),
                respuesta.getMensajes(),
                respuesta.getFechaCreacion(),
                respuesta.getAutorPregunta(),
                respuesta.getAutorRespuesta().getNombre(),
                respuesta.getFechaRespuesta(),
                respuesta.getSolucion());
    }
}
