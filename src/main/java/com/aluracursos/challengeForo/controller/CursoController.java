package com.aluracursos.challengeForo.controller;


import com.aluracursos.challengeForo.models.cursos.*;
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
@RequestMapping("/cursos")
//@SecurityRequirement(name = "bearer-key")
public class CursoController {

    @Autowired
    private CursoRepository cursoRepository;

    @PostMapping
    @Transactional
//    @Operation(summary = "Registra un nuevo curso en la base de datos")
    public ResponseEntity<DatosDetalleCurso> registrarCurso(@RequestBody @Valid DatosregistroCurso datosregistroCurso,
                                                            UriComponentsBuilder uriComponentsBuilder) {
        Curso curso = cursoRepository.save(new Curso(datosregistroCurso));
        DatosDetalleCurso datosRespuestaCurso = new DatosDetalleCurso(curso.getId(),curso.getNombre(),curso.getCategoria());
        URI url = uriComponentsBuilder.path("/cursos/{id}").buildAndExpand(curso.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaCurso);
    }

    @GetMapping
//    @Operation(summary = "Obtiene el listado de cursos de a 2")
    public ResponseEntity<Page<DatosListadoCurso>> listadoCursos(@PageableDefault(size = 10) Pageable paginacion) {
        return ResponseEntity.ok(cursoRepository.findAll(paginacion).map(DatosListadoCurso::new));
    }

    @PutMapping
    @Transactional
//    @Operation(summary = "Actualiza los datos de un cursos existente")
    public ResponseEntity actualizarCursos(@RequestBody @Valid DatosActualizarCurso datosActualizarCurso) {
        Curso curso = cursoRepository.getReferenceById(datosActualizarCurso.id());
        curso.actualizarDatos(datosActualizarCurso);
        return ResponseEntity.ok(new DatosDetalleCurso(curso.getId(),curso.getNombre(),curso.getCategoria()));
    }

    @DeleteMapping("/{id}")
//    @Operation(summary = "Obtiene los registros del cursos con ID")
    public ResponseEntity<String> retornaDatosAutor(@PathVariable Long id) {
        Curso curso = cursoRepository.getReferenceById(id);
        cursoRepository.deleteById(id);
        return ResponseEntity.ok("Se eliminó correctamente el ID "+ curso.getId() + " " + curso.getNombre() +"De " + curso.getCategoria());
    }
}