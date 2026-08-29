package com.unilex.app.Unilex.Model;

public class ProcesoJudicial {
    private Long id;
    private String radicado;
    private String juzgado;
    private String tipoProceso;
    private String estado;

    // Constructor
    public ProcesoJudicial(Long id, String radicado, String juzgado) {
        this.id = id;
        this.radicado = radicado;
        this.juzgado = juzgado;
        this.tipoProceso = tipoProceso;
        this.estado = estado;
    }

    // Métodos Get y Set (Requeridos para el POJO)
    public Long getId() { return id; }
    public String getRadicado() { return radicado; }
    public String getJuzgado() { return juzgado; }
    public String getTipoProceso() { return tipoProceso; }
    public String getEstado() { return estado; }

    public void setId(Long id) { this.id = id; }
    public void setRadicado(String radicado) { this.radicado = radicado; }
    public void setJuzgado(String juzgado) {this.juzgado = juzgado; }
    public void setTipoProceso(String tipoProceso) { this.tipoProceso = tipoProceso; }
    public void setEstado(String estado) { this.estado = estado; }
}