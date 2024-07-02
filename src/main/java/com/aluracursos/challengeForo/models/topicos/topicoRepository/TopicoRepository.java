package com.aluracursos.challengeForo.models.topicos.topicoRepository;


import com.aluracursos.challengeForo.models.topicos.Topico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TopicoRepository extends JpaRepository<Topico, Long> {
    Page<Topico> findByStatusTrue(Pageable paginacion);

    @Query(value = "select * from Topicos t where id=:id and status=1", nativeQuery = true)
    Topico findIdByStatusTrue(Long id);
}
