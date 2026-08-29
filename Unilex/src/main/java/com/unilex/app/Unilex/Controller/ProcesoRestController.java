package com.unilex.app.Unilex.Controller;

import com.unilex.app.Unilex.Model.ProcesoJudicial;
import com.unilex.app.Unilex.Service.ProcesoJudicialService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ProcesoRestController {

    private final ProcesoJudicialService procesoService;

    public ProcesoRestController(ProcesoJudicialService procesoService) {
        this.procesoService = procesoService;
    }

    @GetMapping("/saludo")
    public String saludo() {
        return "Proyecto Unilex funcionando e IA lista para integrar.";
    }

    @GetMapping("/procesos")
    public List<ProcesoJudicial> procesos() {
        return procesoService.listarProcesos();
    }
}