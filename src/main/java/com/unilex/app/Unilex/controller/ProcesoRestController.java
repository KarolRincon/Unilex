package com.unilex.app.Unilex.controller;

import com.unilex.app.Unilex.model.ProcesoJudicial;
import com.unilex.app.Unilex.service.ProcesoJudicialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/procesos")
public class ProcesoRestController {

    @Autowired
    private ProcesoJudicialService procesoService;

    @GetMapping
    public ResponseEntity<List<ProcesoJudicial>> listarTodos() {
        List<ProcesoJudicial> procesos = procesoService.obtenerTodos();
        return ResponseEntity.ok(procesos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProcesoJudicial> obtenerPorId(@PathVariable Long id) {

        ProcesoJudicial proceso = procesoService.obtenerPorId(id);

        if (proceso != null) {
            return ResponseEntity.ok(proceso);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping
    public ResponseEntity<ProcesoJudicial> crearProceso(
            @RequestBody ProcesoJudicial proceso) {

        ProcesoJudicial nuevoProceso = procesoService.guardar(proceso);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(nuevoProceso);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProcesoJudicial> actualizarProceso(
            @PathVariable Long id,
            @RequestBody ProcesoJudicial procesoDetalles) {

        ProcesoJudicial proceso = procesoService.obtenerPorId(id);

        if (proceso != null) {

            proceso.setNumeroRadicado(
                    procesoDetalles.getNumeroRadicado());

            proceso.setDespacho(
                    procesoDetalles.getDespacho());

            proceso.setTipoProceso(
                    procesoDetalles.getTipoProceso());

            ProcesoJudicial actualizado =
                    procesoService.guardar(proceso);

            return ResponseEntity.ok(actualizado);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProceso(
            @PathVariable Long id) {

        ProcesoJudicial proceso =
                procesoService.obtenerPorId(id);

        if (proceso != null) {
            procesoService.eliminar(id);
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}