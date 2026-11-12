package com.unilex.app.Unilex.controller;

import com.unilex.app.Unilex.model.Abogado;
import com.unilex.app.Unilex.repository.AbogadoRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.Map;

@Controller
public class AuthController {

    private final AbogadoRepository abogadoRepository;


    private static final Map<String, String> usuariosRegistrados = new HashMap<>();

    static {

        usuariosRegistrados.put("admin@unilex.co", "123456");
    }

    public AuthController(AbogadoRepository abogadoRepository) {
        this.abogadoRepository = abogadoRepository;
    }

    @GetMapping("/login")
    public String mostrarLogin(HttpSession session) {
        if (session.getAttribute("usuarioNombre") != null) {
            return "redirect:/procesos/lista";
        }
        return "auth/login";
    }

    @PostMapping("/login")
    public String procesarLogin(@RequestParam String usuario,
                                @RequestParam String password,
                                HttpSession session,
                                RedirectAttributes redirect) {

        if (usuario == null || usuario.isBlank()) {
            redirect.addFlashAttribute("error", "El nombre de usuario no puede estar vacío.");
            return "redirect:/login";
        }

        String usuarioLimpio = usuario.trim().toLowerCase();


        if (!usuariosRegistrados.containsKey(usuarioLimpio) || !usuariosRegistrados.get(usuarioLimpio).equals(password)) {
            redirect.addFlashAttribute("error", "Usuario o contraseña incorrectos. Verifique sus credenciales.");
            return "redirect:/login"; // RECHAZA SI LA CLAVE ESTÁ MAL
        }


        session.setAttribute("usuarioNombre", usuarioLimpio);
        return "redirect:/procesos/lista";
    }

    @GetMapping("/registro")
    public String mostrarRegistro(HttpSession session) {
        if (session.getAttribute("usuarioNombre") != null) {
            return "redirect:/procesos/lista";
        }
        return "auth/registro";
    }

    @PostMapping("/registro")
    public String procesarRegistro(@RequestParam String nombre,
                                   @RequestParam String usuario,
                                   @RequestParam String password,
                                   @RequestParam String rol,
                                   @RequestParam(required = false) String especialidad,
                                   RedirectAttributes redirect) {

        if (usuario == null || usuario.isBlank() || password == null || password.isBlank()) {
            redirect.addFlashAttribute("error", "Completa todos los campos obligatorios.");
            return "redirect:/registro";
        }

        String usuarioLimpio = usuario.trim().toLowerCase();


        usuariosRegistrados.put(usuarioLimpio, password);


        if ("ABOGADO".equalsIgnoreCase(rol)) {
            String espFinal = (especialidad != null && !especialidad.isBlank()) ? especialidad : "Derecho General";
            Abogado nuevoAbogado = new Abogado(null, nombre, usuarioLimpio, espFinal);
            abogadoRepository.save(nuevoAbogado);
        }

        redirect.addFlashAttribute("mensajeExito", "¡Registro exitoso! Ya puedes iniciar sesión con tu contraseña.");
        return "redirect:/login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

    @GetMapping("/cambiar-tema")
    public String cambiarTema(@RequestParam String tema,
                              HttpServletResponse response,
                              @RequestHeader(value = "Referer", required = false) String referer) {

        Cookie cookie = new Cookie("preferenciaTema", tema);
        cookie.setMaxAge(7 * 24 * 60 * 60);
        cookie.setPath("/");
        response.addCookie(cookie);

        return "redirect:" + (referer != null ? referer : "/");
    }
}