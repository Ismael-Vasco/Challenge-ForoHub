package com.aluracursos.challengeForo.controller;


import com.aluracursos.challengeForo.infra.errores.ValidacionDeIntegridad;
import com.aluracursos.challengeForo.models.respuestas.DatosActualizacionRespuesta;
import com.aluracursos.challengeForo.models.respuestas.DatosDetalleRespuesta;
import com.aluracursos.challengeForo.models.respuestas.DatosRegistroRespuesta;
import com.aluracursos.challengeForo.models.respuestas.Respuesta;
import com.aluracursos.challengeForo.models.respuestas.respuestaRepository.RespuestaRepository;
import com.aluracursos.challengeForo.models.respuestas.respuestaService.RespuestaService;
import com.aluracursos.challengeForo.models.topicos.topicoRepository.TopicoRepository;
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
@RequestMapping("/respuestas")
//@SecurityRequirement(name = "bearer-key")
//@SuppressWarnings("all")
public class RespuestaController {

    @Autowired
    private RespuestaService service;

    @Autowired
    private RespuestaRepository respuestaRepository;

    @Autowired
    private TopicoRepository topicoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity resgistrar(@RequestBody @Valid DatosRegistroRespuesta datos) throws ValidacionDeIntegridad {
        var response = service.registrar(datos);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Page<DatosDetalleRespuesta>> mostrar(@PageableDefault(size = 10) Pageable paginacion) {
        return  ResponseEntity.ok(respuestaRepository.findAll(paginacion).map(DatosDetalleRespuesta::new));
    }

    @PutMapping
    @Transactional
    public ResponseEntity actualizar(@RequestBody @Valid DatosActualizacionRespuesta datosActualizacionRespuesta) throws ValidacionDeIntegridad {
        var response = service.actializar(datosActualizacionRespuesta);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        Respuesta respuesta = respuestaRepository.getReferenceById(id);
        var eliminar = service.eliminar(id);
        // eliminar conexion a la tabla de pregunta, pero queda guardado el registro
        topicoRepository.save(eliminar);
//        respuestaRepository.deleteById(id);
        return ResponseEntity.ok("Se eliminó correctamente el ID de respuesta "+ respuesta.getId());
    }
}
