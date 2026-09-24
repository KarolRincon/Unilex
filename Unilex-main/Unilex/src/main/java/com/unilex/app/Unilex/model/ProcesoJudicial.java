package com.unilex.app.Unilex.model;

public class ProcesoJudicial {

    private Long id;
    private String radicado;
    private String demandante;
    private String demandado;
    private String despachoJudicial;
    private String estadoActual;

    public ProcesoJudicial() {}

    public ProcesoJudicial(Long id, String radicado, String demandante, String demandado, String despachoJudicial, String estadoActual) {
        this.id = id;
        this.radicado = radicado;
        this.demandante = demandante;
        this.demandado = demandado;
        this.despachoJudicial = despachoJudicial;
        this.estadoActual = estadoActual;
    }

    public ProcesoJudicial(String radicado, String demandante, String demandado, String despachoJudicial, String estadoActual) {
        this.radicado = radicado;
        this.demandante = demandante;
        this.demandado = demandado;
        this.despachoJudicial = despachoJudicial;
        this.estadoActual = estadoActual;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getRadicado() { return radicado; }
    public void setRadicado(String radicado) { this.radicado = radicado; }

    public String getDemandante() { return demandante; }
    public void setDemandante(String demandante) { this.demandante = demandante; }

    public String getDemandado() { return demandado; }
    public void setDemandado(String demandado) { this.demandado = demandado; }

    public String getDespachoJudicial() { return despachoJudicial; }
    public void setDespachoJudicial(String despachoJudicial) { this.despachoJudicial = despachoJudicial; }

    public String getEstadoActual() { return estadoActual; }
    public void setEstadoActual(String estadoActual) { this.estadoActual = estadoActual; }
}