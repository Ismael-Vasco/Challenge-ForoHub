package com.aluracursos.challengeForo.models.topicos;


public record DatosDetalleTopico(
        Long id,
        String titulo,
        String mensajes,
        Long idAutor,
        Long idCurso) {

    public DatosDetalleTopico (Topico topico){
        this(topico.getId(), topico.getTitulo(), topico.getMensajes(),
                topico.getAutor().getId(),
                topico.getCurso().getId());
    }
}
