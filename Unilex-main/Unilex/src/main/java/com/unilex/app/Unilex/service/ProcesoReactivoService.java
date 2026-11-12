package com.unilex.app.Unilex.service;

import com.unilex.app.Unilex.model.ProcesoJudicial;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import jakarta.annotation.PostConstruct;
import reactor.core.BaseSubscriber;

import java.util.Arrays;
import java.util.List;

@Service
public class ProcesoReactivoService {

    // 1. Fuente reactiva con 4 registros de ejemplo (incluyendo uno inactivo)
    private final List<ProcesoJudicial> procesosFicticios = Arrays.asList(
            new ProcesoJudicial(1L, "RAD-2026-001", "Juzgado 1 Civil", "Ejecutivo", true),
            new ProcesoJudicial(2L, "RAD-2026-002", "Juzgado 5 Laboral", "Ordinario", true),
            new ProcesoJudicial(3L, "RAD-2026-003", "Juzgado 2 Penal", "Penal", false), // Registro inactivo
            new ProcesoJudicial(4L, "RAD-2026-004", "Juzgado 8 Administrativo", "Reparación Directa", true)
    );

    // Emite todos los registros mediante Flux
    public Flux<ProcesoJudicial> obtenerTodosLosProcesos() {
        return Flux.fromIterable(procesosFicticios);
    }

    // Operadores filter() y map() para procesar registros activos
    public Flux<String> obtenerResumenProcesosActivos() {
        return Flux.fromIterable(procesosFicticios)
                .filter(ProcesoJudicial::isActivo) // filter(): selecciona solo los activos
                .map(p -> "-> Radicado Activo: " + p.getNumeroRadicado() + " | Despacho: " + p.getDespacho()); // map(): transforma a String
    }

    // Operador count() para obtener un resultado único envuelto en un Mono<Long>
    public Mono<Long> contarProcesosActivos() {
        return Flux.fromIterable(procesosFicticios)
                .filter(ProcesoJudicial::isActivo)
                .count();
    }
    @PostConstruct
    public void ejecutarPruebasReactivasAutomaticas() {
        System.out.println("\n==================================================");
        System.out.println("=== INICIO DE PRUEBAS REACTIVAS (SEMANA 15)   ===");
        System.out.println("==================================================\n");

        System.out.println("--- 1. PROCESOS ACTIVOS (Uso de filter() y map()) ---");
        obtenerResumenProcesosActivos().subscribe(System.out::println);

        System.out.println("\n--- 2. CONTEO DE REGISTROS ACTIVOS (Uso de Mono y count()) ---");
        contarProcesosActivos().subscribe(total -> System.out.println("Total de procesos activos: " + total));

        System.out.println("\n--- 3. SUSCRIPCIÓN Y SEÑALES (onNext, onComplete) ---");
        obtenerTodosLosProcesos().subscribe(
                p -> System.out.println("[onNext] Radicado: " + p.getNumeroRadicado()),
                err -> System.err.println("[onError] " + err.getMessage()),
                () -> System.out.println("[onComplete] ¡Flujo finalizado con éxito!")
        );

        System.out.println("\n--- 4. CONTROL DE DEMANDA (BaseSubscriber) ---");
        obtenerTodosLosProcesos().subscribe(new BaseSubscriber<ProcesoJudicial>() {
            @Override
            protected void hookOnSubscribe(org.reactivestreams.Subscription s) { request(1); }
            @Override
            protected void hookOnNext(ProcesoJudicial p) {
                System.out.println("[Subscriber] Procesado el primero: " + p.getNumeroRadicado() + " -> Cancelando.");
                cancel();
            }
        });

        System.out.println("\n==================================================");
        System.out.println("=== FIN DE PRUEBAS REACTIVAS                  ===\n");
    }