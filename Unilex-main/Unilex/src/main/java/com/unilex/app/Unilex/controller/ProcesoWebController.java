package com.unilex.app.Unilex.controller;

import com.unilex.app.Unilex.dto.ProcesoJudicialDTO;
import com.unilex.app.Unilex.service.ProcesoJudicialService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/procesos")
public class ProcesoWebController {

    @Autowired
    private ProcesoJudicialService procesoService;

    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {
        if (!model.containsAttribute("procesoDTO")) {
            model.addAttribute("procesoDTO", new ProcesoJudicialDTO());
        }
        return "procesos/formulario";
    }

    @PostMapping("/guardar")
    public String guardarProceso(
            @Valid @ModelAttribute("procesoDTO") ProcesoJudicialDTO procesoDTO,
            BindingResult result,
            RedirectAttributes redirectAttributes,
            Model model) {

        if (result.hasErrors()) {
            return "procesos/formulario";
        }

        procesoService.registrarProcesoDesdeDTO(procesoDTO);
        redirectAttributes.addFlashAttribute("mensajeExito", "Proceso judicial " + procesoDTO.getRadicado() + " registrado correctamente.");

        return "redirect:/procesos/lista";
    }

    @GetMapping("/lista")
    public String listarProcesos(Model model) {
        model.addAttribute("procesos", procesoService.obtenerTodos());
        return "procesos/lista";
    }
}
