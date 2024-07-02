package com.aluracursos.challengeForo.models.usuarios;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "autores")
@Entity(name = "Autor")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String correoElectronico;
    private String contraseña;


    public Autor(DatosregistroAutor datosregistroAutor) {
        this.nombre = datosregistroAutor.nombre();
        this.correoElectronico = datosregistroAutor.correoElectronico();
        this.contraseña = datosregistroAutor.contraseña();
    }


    public void actualizarDatos(DatosActualizarAutor datosActualizarAutor) {
        if (datosActualizarAutor.nombre() != null) {
            this.nombre = datosActualizarAutor.nombre();
        }
        if (datosActualizarAutor.correoElectronico() != null) {
            this.correoElectronico = datosActualizarAutor.correoElectronico();
        }
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public String getContraseña() {
        return contraseña;
    }
}
