package com.unilex.app.Unilex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PaginasController {

    @GetMapping("/")
    public String index() {
        return "index"; // Retorna index.html
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

    @GetMapping("/asistente-legal-ia")
    public String asistenteLegalIa() {
        return "ia/asistente-ia";
    }

    @GetMapping("/monitoreo-automatico")
    public String monitoreoAutomatico() {
        return "paginas/monitoreo-automatico";
    }

    @GetMapping("/seguridad-legaltech")
    public String seguridadLegaltech() {
        return "paginas/seguridad-legaltech";
    }

    @GetMapping("/consultas")
    public String consultas() {
        return "paginas/consultas";
    }
    @GetMapping("/auth/login")
    public String login() {
        return "/auth/login";
    }
    @GetMapping("/auth/registro")
    public String registro() {
        return "/auth/registro";
    }
}