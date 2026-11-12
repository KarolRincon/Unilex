package com.unilex.app.Unilex.service;

import com.unilex.app.Unilex.model.ProcesoJudicial;
import com.unilex.app.Unilex.repository.ProcesoJudicialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.annotation.PostConstruct;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.BaseSubscriber;

import java.util.List;

@Service
public class ProcesoJudicialService {

    @Autowired
    private ProcesoJudicialRepository procesoRepository;

    public List<ProcesoJudicial> obtenerTodos() {
        return procesoRepository.findAll();
    }
    public ProcesoJudicial obtenerPorId(Long id) {
        return procesoRepository.findById(id).orElse(null);
    }
    public ProcesoJudicial guardar(ProcesoJudicial proceso) {
        return procesoRepository.save(proceso);
    }
    public void eliminar(Long id) {
        procesoRepository.deleteById(id);
    }

    @PostConstruct
    public void probarReactividadEnConsola() {
        System.out.println("\n==================================================");
        System.out.println("=== INICIO DE PRUEBAS REACTIVAS (SEMANA 15)   ===");
        System.out.println("==================================================\n");

        System.out.println("--- 1. PROCESOS ACTIVOS (Uso de filter() y map()) ---");
        Flux.just(
                        new ProcesoJudicial(1L, "RAD-001", "Juzgado 1", "Civil", true),
                        new ProcesoJudicial(2L, "RAD-002", "Juzgado 2", "Laboral", false)
                )
                .filter(p -> p.isActivo())
                .map(p -> "-> Radicado Activo: " + p.getNumeroRadicado())
                .subscribe(System.out::println);

        System.out.println("\n--- 2. CONTEO DE REGISTROS (Uso de Mono y count()) ---");
        Flux.just(1, 2, 3)
                .count()
                .subscribe(total -> System.out.println("Total contado con Mono: " + total));

        System.out.println("\n--- 3. SUSCRIPCIÓN Y SEÑALES (onNext, onComplete) ---");
        Flux.just("A", "B")
                .subscribe(
                        dato -> System.out.println("[onNext] Recibido: " + dato),
                        err -> System.err.println(err),
                        () -> System.out.println("[onComplete] ¡Flujo finalizado con éxito!")
                );

        System.out.println("\n==================================================");
        System.out.println("=== FIN DE PRUEBAS REACTIVAS                  ===\n");
    }

}