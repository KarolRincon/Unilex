package com.unilex.app.Unilex.Model;

public class ProcesoJudicial {
    private Long id;
    private String radicado;
    private String juzgado;

    // Constructor
    public ProcesoJudicial(Long id, String radicado, String juzgado) {
        this.id = id;
        this.radicado = radicado;
        this.juzgado = juzgado;
    }

    // Métodos Get y Set (Requeridos para el POJO)
    public Long getId() { return id; }
    public String getRadicado() { return radicado; }
    public String getJuzgado() { return juzgado; }
}