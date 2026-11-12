package com.unilex.app.Unilex.controller;

import com.unilex.app.Unilex.model.ProcesoJudicial;
import com.unilex.app.Unilex.service.ProcesoJudicialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/procesos") // Rutas en plural
public class ProcesoRestController {

    @Autowired
    private ProcesoJudicialService service;

    @GetMapping
    public ResponseEntity<List<ProcesoJudicial>> listar() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProcesoJudicial> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build()); // 404 Not Found
    }

    @PostMapping
    public ResponseEntity<ProcesoJudicial> crear(@RequestBody ProcesoJudicial proceso, UriComponentsBuilder ucb) {
        ProcesoJudicial nuevo = service.guardar(proceso);
        // Cabecera Location obligatoria para el código 201
        URI location = ucb.path("/api/procesos/{id}").buildAndExpand(nuevo.getId()).toUri();
        return ResponseEntity.created(location).body(nuevo); // 201 Created con Location
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProcesoJudicial> actualizar(@PathVariable Long id, @RequestBody ProcesoJudicial detalles) {
        return service.buscarPorId(id).map(proceso -> {
            proceso.setNumeroRadicado(detalles.getNumeroRadicado());
            proceso.setDespacho(detalles.getDespacho());
            proceso.setTipoProceso(detalles.getTipoProceso());
            return ResponseEntity.ok(service.guardar(proceso)); // 200 OK
        }).orElse(ResponseEntity.notFound().build()); // 404 Not Found
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (service.buscarPorId(id).isPresent()) {
            service.eliminar(id);
            return ResponseEntity.noContent().build(); // 204 No Content
        }
        return ResponseEntity.notFound().build(); // 404 Not Found
    }
}