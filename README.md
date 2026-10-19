# Unilex - Sistema de Seguimiento Judicial

Plataforma SaaS para la gestión y monitoreo automatizado de procesos judiciales en Colombia.

## Funcionalidades de la Semana 11
- **Control de Sesiones (`HttpSession`):** Manejo de inicio de sesión persistente durante la navegación y cierre de sesión con invalidación de estado (`invalidate()`).
- **Cookies de Preferencias:** Almacenamiento local en el navegador del tema visual seleccionado (`preferenciaTema=claro/oscuro`) con vigencia temporal.
- **Manejo Personalizado de Errores HTTP:**
    - `404 Not Found`: Interfaz amigable al solicitar recursos o expedientes inexistentes.
    - `500 Internal Server Error`: Captura de fallos en el servidor con opción de retorno seguro.

---

## Conceptualización Técnica (Examen de Diferencias)

| Concepto | Ubicación | Persistencia | Caso de Uso en Unilex |
| :--- | :--- | :--- | :--- |
| **Sesión (`HttpSession`)** | Servidor | Temporal (mientras navega el usuario) | Mantener la autenticación y el nombre del abogado activo en pantalla. |
| **Cookie** | Cliente (Navegador) | Configurable (`Max-Age`) | Recordar preferencias estéticas (ej. Modo Claro/Oscuro). |
| **Base de Datos (H2/JPA)** | Servidor / Disco | Permanente | Almacenar expedientes, radicado, partes procesales y abogados. |