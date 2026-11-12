package com.unilex.app.Unilex.component;

import com.unilex.app.Unilex.model.ProcesoJudicial;
import com.unilex.app.Unilex.service.ProcesoReactivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.core.BaseSubscriber;

@Component
public class ReactivoRunner implements CommandLineRunner {

    @Autowired
    private ProcesoReactivoService reactivoService;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("\n==================================================");
        System.out.println("=== INICIO DE PRUEBAS REACTIVAS (SEMANA 15)   ===");
        System.out.println("==================================================\n");

        // 1. Demostración de Filtrado y Transformación (filter y map)
        System.out.println("--- 1. PROCESOS ACTIVOS (Uso de filter() y map()) ---");
        reactivoService.obtenerResumenProcesosActivos()
                .subscribe(System.out::println);

        // 2. Demostración de Conteo con Mono (count)
        System.out.println("\n--- 2. CONTEO DE REGISTROS ACTIVOS (Uso de Mono y count()) ---");
        reactivoService.contarProcesosActivos()
                .subscribe(total -> System.out.println("Total de procesos activos en el sistema: " + total));

        // 3. Demostración de Suscripción y Señales (onNext, onComplete, onError)
        System.out.println("\n--- 3. SUSCRIPCIÓN Y SEÑALES (onNext, onComplete, onError) ---");
        System.out.println("Nota: Un flujo en Reactor es 'lazy' y no emite nada hasta que se invoca subscribe().");
        reactivoService.obtenerTodosLosProcesos()
                .subscribe(
                        proceso -> System.out.println("[onNext] Dato recibido -> Radicado: " + proceso.getNumeroRadicado() + " [Activo: " + proceso.isActivo() + "]"),
                        error -> System.err.println("[onError] Error detectado: " + error.getMessage()),
                        () -> System.out.println("[onComplete] ¡Flujo finalizado correctamente sin errores!")
                );

        // 4. Control de demanda con BaseSubscriber (request y cancel)
        System.out.println("\n--- 4. CONTROL DE DEMANDA (BaseSubscriber, request(1), cancel) ---");
        reactivoService.obtenerTodosLosProcesos()
                .subscribe(new BaseSubscriber<ProcesoJudicial>() {
                    @Override
                    protected void hookOnSubscribe(org.reactivestreams.Subscription subscription) {
                        System.out.println("[Subscriber] Suscripción iniciada. Solicitando exactamente 1 elemento...");
                        request(1); // Control de demanda: solicitamos solo 1
                    }

                    @Override
                    protected void hookOnNext(ProcesoJudicial value) {
                        System.out.println("[Subscriber] [onNext] Procesado primer elemento: " + value.getNumeroRadicado());
                        System.out.println("[Subscriber] Cancelando la demanda con cancel(). No se recibirán más elementos...");
                        cancel(); // Cancelamos la suscripción
                    }

                    @Override
                    protected void complete() {
                        System.out.println("[Subscriber] [onComplete]");
                    }
                });

        System.out.println("\n==================================================");
        System.out.println("=== FIN DE PRUEBAS REACTIVAS EN CONSOLA       ===");
        System.out.println("==================================================\n");
    }
}