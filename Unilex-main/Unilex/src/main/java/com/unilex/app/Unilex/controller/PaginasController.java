package com.unilex.app.Unilex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.unilex.app.Unilex.service.ProcesoJudicialService;
import org.springframework.beans.factory.annotation.Autowired;

@Controller
public class PaginasController {

    @Autowired
    private ProcesoJudicialService procesoService;

    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }

    @GetMapping("/registro")
    public String registro() {
        return "auth/registro";
    }

    @GetMapping("/quienes-somos")
    public String quienesSomos() {
        return "paginas/quienes-somos";
    }

    @GetMapping("/servicios")
    public String servicios() {
        return "paginas/servicios";
    }

    @GetMapping("/planes")
    public String planes() {
        return "paginas/planes";
    }

    @GetMapping("/consultas")
    public String consultas() {
        return "paginas/consultas";
    }

    @GetMapping("/actuaciones")
    public String actuaciones() {
        return "procesos/actuaciones";
    }

    @GetMapping("/perfil")
    public String perfil(Model model) {
        // Datos simulados del abogado/usuario
        model.addAttribute("nombre", "Dra. Carolina Martínez");
        model.addAttribute("email", "carolina.martinez@unilex.co");
        model.addAttribute("tarjetaProfesional", "TP-382910-T");
        model.addAttribute("plan", "Plan Pro Legal (Activo)");
        model.addAttribute("procesosMonitoreados", 12);
        return "usuario/perfil";
    }

    @GetMapping("/asistente-ia")
    public String asistenteIA(Model model) {
        return "ia/asistente-ia";
    }


}