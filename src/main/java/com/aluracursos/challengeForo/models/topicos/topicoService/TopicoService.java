package com.aluracursos.challengeForo.models.topicos.topicoService;

import com.aluracursos.challengeForo.models.cursos.Curso;
import com.aluracursos.challengeForo.models.cursos.CursoRepository;
import com.aluracursos.challengeForo.infra.errores.ValidacionDeIntegridad;
import com.aluracursos.challengeForo.models.topicos.DatosActualizarTopico;
import com.aluracursos.challengeForo.models.topicos.DatosDetalleTopico;
import com.aluracursos.challengeForo.models.topicos.DatosregistroTopico;
import com.aluracursos.challengeForo.models.topicos.Topico;
import com.aluracursos.challengeForo.models.topicos.topicoRepository.TopicoRepository;
import com.aluracursos.challengeForo.models.usuarios.autorRepository.AutorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private CursoRepository cursoRepository;


    public DatosDetalleTopico registrar(DatosregistroTopico datos) {

        if (!autorRepository.findById(datos.idAutor()).isPresent()) {
            throw new ValidacionDeIntegridad("Este id para el Autor no fue encontrado");
        }

        if (datos.idCurso() != null && !cursoRepository.existsById(datos.idCurso())) {
            throw new ValidacionDeIntegridad("Este id para el curso no fue encontrado");
        }

//        validadores.forEach(v-> v.validar(datos));

        var autor = autorRepository.findById(datos.idAutor()).get();
        var curso = seleccionarCurso(datos);


        var topico = new Topico(datos, autor, curso);

        topicoRepository.save(topico);

        return new DatosDetalleTopico(topico);

    }

    private Curso seleccionarCurso(DatosregistroTopico datos) {
        if (datos.idCurso() != null) {
            return cursoRepository.getReferenceById(datos.idCurso());
        } else {
            throw new ValidacionDeIntegridad("No existe Curso registrado para ese topico - REGISTRALO -");
        }
    }


    public DatosDetalleTopico actializar(DatosActualizarTopico datos) {

        if (datos.id() != null && !topicoRepository.existsById(datos.id())) {
            throw new ValidacionDeIntegridad("Este id para ese topico no fue encontrado");
        } else {
            Topico topico = topicoRepository.getReferenceById(datos.id());
            topico.actualizarDatos(datos);

            return new DatosDetalleTopico(topico);
        }
    }

    public void eliminar(Long id) {
        if (id != null && !topicoRepository.existsById(id)) {
            throw new ValidacionDeIntegridad("Este id para ese topico no fue encontrado - No se Puede eliminar-");
        } else {
            Topico topico = topicoRepository.getReferenceById(id);
            topico.desactivarTopico();
        }
    }
}
