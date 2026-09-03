package com.unilex.app.Unilex.Controller;

import com.unilex.app.Unilex.Model.ProcesoJudicial;
import com.unilex.app.Unilex. Service.ProcesoJudicialService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProcesoViewController {

    private final ProcesoJudicialService procesoService;

    // Inyección de dependencias por constructor
    public ProcesoViewController(ProcesoJudicialService procesoService) {
        this.procesoService = procesoService;
    }

    @GetMapping("/procesos")
    public String verListaProcesos(Model model) {
        // Obtenemos los datos desde el servicio
        var listaProcesos = procesoService.listarProcesos();

        // Enviamos la lista a la vista mediante Model
        model.addAttribute("procesos", listaProcesos);
        model.addAttribute("titulo", "Unilex - Sistema de Gestión de Procesos Judiciales");

        // Retorna el nombre de la plantilla HTML (procesos.html en templates)
        return "procesos";
    }
}