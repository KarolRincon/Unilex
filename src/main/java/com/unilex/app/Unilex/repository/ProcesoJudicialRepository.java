package com.unilex.app.Unilex.repository;

import com.unilex.app.Unilex.model.ProcesoJudicial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcesoJudicialRepository extends JpaRepository<ProcesoJudicial, Long> {
}