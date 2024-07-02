package com.aluracursos.challengeForo.models.usuarios.autorRepository;

import com.aluracursos.challengeForo.models.usuarios.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {
}
