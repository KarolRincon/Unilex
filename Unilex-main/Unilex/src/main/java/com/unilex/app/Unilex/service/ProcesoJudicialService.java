package com.unilex.app.Unilex.service;

import com.unilex.app.Unilex.dto.ProcesoJudicialDTO;
import com.unilex.app.Unilex.model.ProcesoJudicial;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProcesoJudicialService {

    private final List<ProcesoJudicial> listaProcesos = new ArrayList<>();
    private Long contadorId = 1L;

    public List<ProcesoJudicial> obtenerTodos() {
        return listaProcesos;
    }

    public void registrarProcesoDesdeDTO(ProcesoJudicialDTO dto) {
        ProcesoJudicial nuevo = new ProcesoJudicial(
                contadorId++,
                dto.getRadicado(),
                dto.getDemandante(),
                dto.getDemandado(),
                dto.getDespachoJudicial(),
                dto.getEstadoActual()
        );
        listaProcesos.add(nuevo);
    }
}