package com.unilex.app.Unilex.service;

import com.unilex.app.Unilex.model.Abogado;
import com.unilex.app.Unilex.model.ProcesoJudicial;
import com.unilex.app.Unilex.repository.AbogadoRepository;
import com.unilex.app.Unilex.repository.ProcesoJudicialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProcesoJudicialService {

    @Autowired
    private ProcesoJudicialRepository procesoRepository;

    @Autowired
    private AbogadoRepository abogadoRepository;

    // Búsqueda paginada
    public Page<ProcesoJudicial> buscarProcesosPaginados(String palabraClave, int numPagina, int tamanoPagina) {
        Pageable pageable = PageRequest.of(numPagina - 1, tamanoPagina);
        if (palabraClave != null && !palabraClave.trim().isEmpty()) {
            return procesoRepository.findByNumeroRadicadoContainingIgnoreCaseOrDespachoContainingIgnoreCase(
                    palabraClave, palabraClave, pageable);
        }
        return procesoRepository.findAll(pageable);
    }

    public List<Abogado> listarAbogados() {
        return abogadoRepository.findAll();
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