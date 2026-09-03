package com.unilex.app.Unilex.Service;

import com.unilex.app.Unilex.Model.ProcesoJudicial;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProcesoJudicialService {

    public List<ProcesoJudicial> listarProcesos() {
        // Retornamos una lista estática simulando la base de datos por ahora
        return List.of(
                new ProcesoJudicial(1L, "11001310300120230012300", "Juzgado 1 Civil del Circuito", "Demanda", "Abierto"),
                new ProcesoJudicial(2L, "11001400300220230045600", "Juzgado 2 Promiscuo Municipal", "Tutela", "Revisión")
        );
    }
}