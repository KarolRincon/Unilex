package com.unilex.app.Unilex.repository;

import com.unilex.app.Unilex.model.ProcesoJudicial;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcesoJudicialRepository extends JpaRepository<ProcesoJudicial, Long> {

    // Búsqueda por número de radicado o despacho conteniendo texto (ignora mayúsculas/minúsculas)
    Page<ProcesoJudicial> findByNumeroRadicadoContainingIgnoreCaseOrDespachoContainingIgnoreCase(
            String radicado, String despacho, Pageable pageable);
}