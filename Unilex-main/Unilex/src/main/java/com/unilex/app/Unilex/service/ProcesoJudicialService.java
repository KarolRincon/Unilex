package com.unilex.app.Unilex.service;

import com.unilex.app.Unilex.model.ProcesoJudicial;
import com.unilex.app.Unilex.repository.ProcesoJudicialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProcesoJudicialService {

    @Autowired
    private ProcesoJudicialRepository procesoRepository;

    public List<ProcesoJudicial> obtenerTodos() {
        return procesoRepository.findAll();
    }

    public ProcesoJudicial obtenerPorId(Long id) {
        return procesoRepository.findById(id).orElse(null);
    }

    public ProcesoJudicial guardar(ProcesoJudicial proceso) {
        return procesoRepository.save(proceso);
    }

    public void eliminar(Long id) {
        procesoRepository.deleteById(id);
    }
}