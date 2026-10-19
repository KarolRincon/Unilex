package com.unilex.app.Unilex.controller;

import com.unilex.app.Unilex.model.ProcesoJudicial;
import com.unilex.app.Unilex.service.ProcesoJudicialService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/procesos")
public class ProcesoWebController {

    @Autowired
    private ProcesoJudicialService procesoService;

    // Listado paginado y con búsqueda
    @GetMapping("/lista")
    public String listarProcesos(
            @RequestParam(name = "palabraClave", required = false) String palabraClave,
            @RequestParam(name = "page", defaultValue = "1") int numPagina,
            Model model) {

        int tamanoPagina = 5; // Muestra 5 registros por página
        Page<ProcesoJudicial> page = procesoService.buscarProcesosPaginados(palabraClave, numPagina, tamanoPagina);

        model.addAttribute("usuarioNombre", "Dra. Carolina Martínez");
        model.addAttribute("procesos", page.getContent());
        model.addAttribute("paginaActual", numPagina);
        model.addAttribute("totalPaginas", page.getTotalPages());
        model.addAttribute("totalElementos", page.getTotalElements());
        model.addAttribute("palabraClave", palabraClave);

        return "procesos/lista";
    }

    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("usuarioNombre", "Dra. Carolina Martínez");
        model.addAttribute("proceso", new ProcesoJudicial());
        model.addAttribute("abogados", procesoService.listarAbogados());
        return "procesos/formulario";
    }

    // Guardar con validaciones (@Valid y BindingResult) y RedirectAttributes
    @PostMapping("/guardar")
    public String guardarProceso(
            @Valid @ModelAttribute("proceso") ProcesoJudicial proceso,
            BindingResult result,
            Model model,
            RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            model.addAttribute("usuarioNombre", "Dra. Carolina Martínez");
            model.addAttribute("abogados", procesoService.listarAbogados());
            return "procesos/formulario";
        }

        procesoService.guardar(proceso);
        redirectAttributes.addFlashAttribute("mensajeExito", "¡El proceso judicial fue guardado exitosamente en Unilex!");
        return "redirect:/procesos/lista";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable("id") Long id, Model model) {
        ProcesoJudicial proceso = procesoService.obtenerPorId(id);
        if (proceso != null) {
            model.addAttribute("usuarioNombre", "Dra. Carolina Martínez");
            model.addAttribute("proceso", proceso);
            model.addAttribute("abogados", procesoService.listarAbogados());
            return "procesos/formulario";
        }
        return "redirect:/procesos/lista";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarProceso(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        procesoService.eliminar(id);
        redirectAttributes.addFlashAttribute("mensajeExito", "El proceso judicial se ha eliminado correctamente.");
        return "redirect:/procesos/lista";
    }
}