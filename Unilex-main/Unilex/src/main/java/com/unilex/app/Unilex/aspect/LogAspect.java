package com.unilex.app.Unilex.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {

    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    // Pointcut: Intercepta todos los métodos dentro del paquete service de Unilex
    @Pointcut("execution(* com.unilex.app.Unilex.service..*(..))")
    public void serviciosUnilexPointcut() {
        // Método firma utilizado para definir el Pointcut
    }

    // Advice @Before: Se ejecuta antes de ingresar a cualquier método de servicio
    @Before("serviciosUnilexPointcut()")
    public void registrarInicioMetodo(JoinPoint joinPoint) {
        String nombreMetodo = joinPoint.getSignature().getName();
        String claseOrigen = joinPoint.getTarget().getClass().getSimpleName();
        logger.info(">>> [AOP - Unilex Audit] INICIANDO ejecución del método: {}.{}()", claseOrigen, nombreMetodo);
    }

    // Advice @After: Se ejecuta inmediatamente después de finalizar el método
    @After("serviciosUnilexPointcut()")
    public void registrarFinMetodo(JoinPoint joinPoint) {
        String nombreMetodo = joinPoint.getSignature().getName();
        String claseOrigen = joinPoint.getTarget().getClass().getSimpleName();
        logger.info("<<< [AOP - Unilex Audit] FINALIZANDO ejecución del método: {}.{}()", claseOrigen, nombreMetodo);
    }
}