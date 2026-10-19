package com.unilex.app.Unilex.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthController {

    @GetMapping("/login")
    public String mostrarLogin(HttpSession session, Model model) {
        if (session.getAttribute("usuarioNombre") != null) {
            return "redirect:/procesos/lista";
        }
        return "auth/login";
    }

    @PostMapping("/login")
    public String procesarLogin(@RequestParam("nombreUsuario") String nombreUsuario,
                                HttpSession session,
                                RedirectAttributes redirectAttributes) {

        if (nombreUsuario == null || nombreUsuario.trim().isEmpty()) {
            redirectAttributes.addFlashAttribute("mensajeError", "El nombre de usuario no puede estar vacío.");
            return "redirect:/login";
        }

        // 1. Control de sesiones mediante HttpSession
        session.setAttribute("usuarioNombre", nombreUsuario.trim());
        redirectAttributes.addFlashAttribute("mensajeExito", "¡Bienvenido/a a Unilex, " + nombreUsuario + "!");
        return "redirect:/procesos/lista";
    }

    @GetMapping("/logout")
    public String cerrarSesion(HttpSession session, RedirectAttributes redirectAttributes) {
        // Invalidate destruye la sesión HTTP
        session.invalidate();
        redirectAttributes.addFlashAttribute("mensajeExito", "Ha cerrado sesión correctamente.");
        return "redirect:/login";
    }

    // 2. Cookie de preferencia (Tema Claro/Oscuro)
    @GetMapping("/cambiar-tema")
    public String cambiarTema(@RequestParam("tema") String tema,
                              HttpServletResponse response,
                              @RequestHeader(value = "Referer", required = false) String referer) {

        Cookie cookieTema = new Cookie("preferenciaTema", tema);
        cookieTema.setMaxAge(7 * 24 * 60 * 60); // Caduca en 7 días
        cookieTema.setPath("/");
        response.addCookie(cookieTema);

        return "redirect:" + (referer != null ? referer : "/procesos/lista");
    }
}