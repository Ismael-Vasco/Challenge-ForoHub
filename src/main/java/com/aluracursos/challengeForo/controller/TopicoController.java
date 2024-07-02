package com.aluracursos.challengeForo.controller;


import com.aluracursos.challengeForo.infra.errores.ValidacionDeIntegridad;
import com.aluracursos.challengeForo.models.topicos.DatosActualizarTopico;
import com.aluracursos.challengeForo.models.topicos.DatosDetalleTopico;
import com.aluracursos.challengeForo.models.topicos.DatosregistroTopico;
import com.aluracursos.challengeForo.models.topicos.topicoRepository.TopicoRepository;
import com.aluracursos.challengeForo.models.topicos.topicoService.TopicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@Controller
@ResponseBody
@RequestMapping("/topicos")
//@SecurityRequirement(name = "bearer-key")
//@SuppressWarnings("all")
public class TopicoController {

    @Autowired
    private TopicoService service;

    @Autowired
    private TopicoRepository topicoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity resgistrar(@RequestBody @Valid DatosregistroTopico datos) throws ValidacionDeIntegridad {
        var response = service.registrar(datos);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Page<DatosDetalleTopico>> mostrar(@PageableDefault(size = 10) Pageable paginacion) {
        return ResponseEntity.ok(topicoRepository.findByStatusTrue(paginacion).map(DatosDetalleTopico::new));
    }

    @PutMapping
    @Transactional
    public ResponseEntity actualizar(@RequestBody @Valid DatosActualizarTopico datosActualizarTopico) throws ValidacionDeIntegridad {
        var response = service.actializar(datosActualizarTopico);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.ok("Se eliminó correctamente");
    }
}
