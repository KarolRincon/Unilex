package com.unilex.app.Unilex.repository;

import com.unilex.app.Unilex.model.Abogado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AbogadoRepository extends JpaRepository<Abogado, Long> {
}