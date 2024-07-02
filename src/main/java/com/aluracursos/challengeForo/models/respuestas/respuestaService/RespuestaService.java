package com.aluracursos.challengeForo.models.respuestas.respuestaService;


import com.aluracursos.challengeForo.infra.errores.ValidacionDeIntegridad;
import com.aluracursos.challengeForo.models.respuestas.DatosActualizacionRespuesta;
import com.aluracursos.challengeForo.models.respuestas.DatosDetalleRespuesta;
import com.aluracursos.challengeForo.models.respuestas.DatosRegistroRespuesta;
import com.aluracursos.challengeForo.models.respuestas.Respuesta;
import com.aluracursos.challengeForo.models.respuestas.respuestaRepository.RespuestaRepository;
import com.aluracursos.challengeForo.models.topicos.Topico;
import com.aluracursos.challengeForo.models.topicos.topicoRepository.TopicoRepository;
import com.aluracursos.challengeForo.models.usuarios.autorRepository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RespuestaService {

    @Autowired
    private RespuestaRepository respuestaRepository;

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private AutorRepository autorRepository;

    public DatosDetalleRespuesta registrar(DatosRegistroRespuesta datos) {

        if (!topicoRepository.findById(datos.idTopico()).isPresent()) {
            throw new ValidacionDeIntegridad("Este id para el Topico no fue encontrado");
        }
        if (!topicoRepository.findIdByStatusTrue(datos.idTopico()).getStatus()) {
            throw new ValidacionDeIntegridad("Este Topicó ya ha sido eliminado");
        }

        if (!autorRepository.findById(datos.autorRespuesta()).isPresent()) {
            throw new ValidacionDeIntegridad("Este id para el Autor no fue encontrado");
        }


//        validadores.forEach(v-> v.validar(datos));

        var autor = autorRepository.findById(datos.autorRespuesta()).get();
        var topico = seleccionarTopico(datos);


        var respuesta = new Respuesta(topico, autor, datos);

        respuestaRepository.save(respuesta);
        var update = topicoRepository.getReferenceById(respuesta.getIdTopico().getId());
        update.setRespuesta(respuesta);



        return new DatosDetalleRespuesta(respuesta);

    }

    private Topico seleccionarTopico(DatosRegistroRespuesta datos) {
        if (datos.idTopico() != null) {
            return topicoRepository.getReferenceById(datos.idTopico());
        } else {
            throw new ValidacionDeIntegridad("No existe Topico  - REGISTRALO UN TOPICO -");
        }
    }

    public DatosDetalleRespuesta actializar(DatosActualizacionRespuesta datos) {

        if (datos.id() != null && !respuestaRepository.existsById(datos.id())) {
            throw new ValidacionDeIntegridad("Este id para actualizar esa respuesta no fue encontrado");
        } else {
            Respuesta respuesta = respuestaRepository.getReferenceById(datos.id());
            respuesta.actualizarDatos(datos);

            return new DatosDetalleRespuesta(respuesta);
        }
    }

    public Topico eliminar(Long id) {
        if (id != null && !respuestaRepository.existsById(id)) {
            throw new ValidacionDeIntegridad("Este id para esa respuesta no fue encontrado - No se Puede eliminar-");
        } else {
            Respuesta respuesta = respuestaRepository.getReferenceById(id);;
            Topico desactivar = topicoRepository.getReferenceById(respuesta.getIdTopico().getId());
            desactivar.setRespuestaNull();

            return desactivar;
        }
    }
}