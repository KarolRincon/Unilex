package com.unilex.app.Unilex.controller;

import com.unilex.app.Unilex.model.ProcesoJudicial;
import com.unilex.app.Unilex.service.ProcesoJudicialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/procesos")
public class ProcesoWebController {

    @Autowired
    private ProcesoJudicialService procesoService;

    @GetMapping("/lista")
    public String listarProcesos(Model model, HttpSession session) {
        // Recupera el usuario de la sesión HTTP si existe
        Object usuario = session.getAttribute("usuarioNombre");
        if (usuario != null) {
            model.addAttribute("usuarioNombre", usuario.toString());
        }

        model.addAttribute("procesos", procesoService.obtenerTodos());
        return "procesos/lista";
    }

    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("proceso", new ProcesoJudicial());
        return "procesos/formulario";
    }

    @PostMapping("/guardar")
    public String guardarProceso(@ModelAttribute("proceso") ProcesoJudicial proceso) {
        procesoService.guardar(proceso);
        return "redirect:/procesos/lista";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable("id") Long id, Model model) {
        ProcesoJudicial proceso = procesoService.obtenerPorId(id);
        if (proceso != null) {
            model.addAttribute("proceso", proceso);
            return "procesos/formulario";
        }
        return "redirect:/procesos/lista";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarProceso(@PathVariable("id") Long id) {
        procesoService.eliminar(id);
        return "redirect:/procesos/lista";
    }
}