package com.unilex.app.Unilex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CustomErrorController {

    // Si deseas una ruta de error personalizada accesible por URL
    @GetMapping("/mi-error")
    public String paginaErrorPersonalizada() {
        return "error";
    }
}
