package com.aluracursos.challengeForo.controller;


import com.aluracursos.challengeForo.models.usuarios.*;
import com.aluracursos.challengeForo.models.usuarios.autorRepository.AutorRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/autores")
//@SecurityRequirement(name = "bearer-key")
public class AutorController {

    @Autowired
    private AutorRepository autorRepository;

    @PostMapping
    @Transactional
//    @Operation(summary = "Registra un nuevo autor en la base de datos")
    public ResponseEntity<DatosRespuestaAutor> registrarAutor(@RequestBody @Valid DatosregistroAutor datosregistroAutor,
                                                               UriComponentsBuilder uriComponentsBuilder) {
        Autor autor = autorRepository.save(new Autor(datosregistroAutor));
        DatosRespuestaAutor datosRespuestaAutor = new DatosRespuestaAutor(autor.getId(),autor.getNombre(),autor.getCorreoElectronico(),
                autor.getContraseña());
        URI url = uriComponentsBuilder.path("/autores/{id}").buildAndExpand(autor.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaAutor);
    }

    @GetMapping
//    @Operation(summary = "Obtiene el listado de autores de a 2")
    public ResponseEntity<Page<DatosListadoAutor>> listadoAutores(@PageableDefault(size = 10) Pageable paginacion) {
        return ResponseEntity.ok(autorRepository.findAll(paginacion).map(DatosListadoAutor::new));
    }

    @PutMapping
    @Transactional
//    @Operation(summary = "Actualiza los datos de un autor existente")
    public ResponseEntity actualizarAutor(@RequestBody @Valid DatosActualizarAutor datosActualizarAutor) {
        Autor autor = autorRepository.getReferenceById(datosActualizarAutor.id());
        autor.actualizarDatos(datosActualizarAutor);
        return ResponseEntity.ok(new DatosRespuestaAutor(autor.getId(),autor.getNombre(),autor.getCorreoElectronico(),
                autor.getContraseña()));
    }

    @DeleteMapping("/{id}")
//    @Operation(summary = "Obtiene los registros del autores con ID")
    public ResponseEntity<String> retornaDatosAutor(@PathVariable Long id) {
        Autor autor = autorRepository.getReferenceById(id);
        autorRepository.deleteById(id);
        return ResponseEntity.ok("Se eliminó correctamente el ID "+ autor.getId() + " " + autor.getNombre());
    }
}