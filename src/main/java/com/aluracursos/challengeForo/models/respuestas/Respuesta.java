package com.aluracursos.challengeForo.models.respuestas;


import com.aluracursos.challengeForo.models.topicos.Topico;
import com.aluracursos.challengeForo.models.usuarios.Autor;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Table(name = "respuestas")
@Entity(name = "Respuesta")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Respuesta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topico_id")
    private Topico idTopico;

    private String titulo;

    private String mensajes;

    private LocalDateTime fechaCreacion;

    private String autorPregunta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_respuesta")
    private Autor autorRespuesta;

    private LocalDateTime fechaRespuesta;

    private String solucion;

    public Respuesta(Topico topico, Autor autor, DatosRegistroRespuesta datosRegistroRespuesta) {
        this.idTopico = topico;
        this.titulo = topico.getTitulo();
        this.mensajes = topico.getMensajes();
        this.fechaCreacion = topico.getFechaCreacion();
        this.autorPregunta = topico.getAutor().getNombre();
        this.autorRespuesta = autor;
        this.fechaRespuesta = LocalDateTime.of(LocalDate.now(), LocalTime.now());
        this.solucion = datosRegistroRespuesta.solucion();
        topico.setRespuesta(Respuesta.this);
    }

    public void actualizarDatos(DatosActualizacionRespuesta datosActualizarRespuesta) {
        if (datosActualizarRespuesta.solucion() != null) {
            this.solucion = datosActualizarRespuesta.solucion();
        }
        this.fechaRespuesta = LocalDateTime.of(LocalDate.now(), LocalTime.now());
    }

    public Long getId() {
        return id;
    }

    public Topico getIdTopico() {
        return idTopico;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getMensajes() {
        return mensajes;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public String getAutorPregunta() {
        return autorPregunta;
    }

    public Autor getAutorRespuesta() {
        return autorRespuesta;
    }

    public LocalDateTime getFechaRespuesta() {
        return fechaRespuesta;
    }

    public String getSolucion() {
        return solucion;
    }
}


