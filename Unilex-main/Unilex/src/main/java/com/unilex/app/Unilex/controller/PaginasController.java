package com.unilex.app.Unilex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@Controller
public class PaginasController {

    @GetMapping("/")
    public String inicio(Model model) {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }

    // --- AGREGAR AQUÍ EL CÓDIGO SUBRAYADO ---

    @PostMapping("/login")
    public String procesarLogin(@RequestParam("email") String email, HttpSession session) {
        // Al iniciar sesión guarda el nombre en la sesión HTTP de Spring
        session.setAttribute("usuarioNombre", "Dra. Carolina Martínez");
        return "redirect:/procesos/lista";
    }

    @GetMapping("/logout")
    public String cerrarSesion(HttpSession session) {
        session.invalidate(); // Limpia la sesión
        return "redirect:/";
    }

    // ---------------------------------------

    @GetMapping("/registro")
    public String registro() {
        return "auth/registro";
    }

    @GetMapping("/quienes-somos")
    public String quienesSomos(Model model) {
        return "paginas/quienes-somos";
    }

    @GetMapping("/servicios")
    public String servicios(Model model) {
        return "paginas/servicios";
    }

    @GetMapping("/planes")
    public String planes(Model model) {
        return "paginas/planes";
    }

    @GetMapping("/consultas")
    public String consultas(Model model) {
        return "paginas/consultas";
    }

    @GetMapping("/actuaciones")
    public String actuaciones(Model model) {
        return "procesos/actuaciones";
    }

    @GetMapping("/asistente-ia")
    public String asistenteIA(Model model) {
        return "ia/asistente-ia";
    }

    @GetMapping("/perfil")
    public String perfil(Model model) {
        model.addAttribute("usuarioNombre", "Dra. Carolina Martínez");
        model.addAttribute("email", "carolina.martinez@unilex.co");
        model.addAttribute("plan", "Plan Pro Legal (Activo)");
        return "usuario/perfil";
    }
}