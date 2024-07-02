package com.aluracursos.challengeForo.models.topicos;

import com.aluracursos.challengeForo.models.cursos.Curso;
import com.aluracursos.challengeForo.models.respuestas.Respuesta;
import com.aluracursos.challengeForo.models.usuarios.Autor;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Table(name = "topicos")
@Entity(name = "Topico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String mensajes;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaActualizacion;
    private Boolean status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id")
    private Autor autor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curso_id")
    private Curso curso;

    private Long respuesta;



    public Topico(DatosregistroTopico datosregistroTopico,
                  Autor autor,
                  Curso curso) {
        this.titulo = datosregistroTopico.titulo();
        this.mensajes = datosregistroTopico.mensajes();
        this.fechaCreacion = LocalDateTime.of(LocalDate.now(), LocalTime.now());
        this.fechaActualizacion = LocalDateTime.of(LocalDate.now(), LocalTime.now());
        this.status = true;
        this.autor = autor;
        this.curso = curso;
        this.respuesta = null;
    }

//    public Topico(DatosregistroTopico datosregistroTopico) {
//    }

    public void actualizarDatos(DatosActualizarTopico datosActualizarTopico) {
        if (datosActualizarTopico.titulo() != null) {
            this.titulo = datosActualizarTopico.titulo();
        }
        if (datosActualizarTopico.mensajes() != null) {
            this.mensajes = datosActualizarTopico.mensajes();
        }
        this.fechaActualizacion = LocalDateTime.of(LocalDate.now(), LocalTime.now());
    }

    public void setRespuesta(Respuesta respuesta) {
        this.respuesta = respuesta.getId();
    }
    public void setRespuestaNull() {
        this.respuesta = null;
    }

    public void desactivarTopico() {
        this.status = false;
    }

    public Long getId() {
        return id;
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

    public LocalDateTime getFechaActualizacion() {
        return fechaActualizacion;
    }

    public Boolean getStatus() {
        return status;
    }

    public Autor getAutor() {
        return autor;
    }

    public Curso getCurso() {
        return curso;
    }

    public Long getRespuesta() {
        return respuesta;
    }
}